package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import com.sun.glass.events.KeyEvent;

import board.Tile;
import game.Chess;
import game.Type;
import pieces.Piece;

public class BoardGUI extends JFrame{

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private Color darkColor = Color.DARK_GRAY;
	private Color lightColor = Color.LIGHT_GRAY;
	private boolean hasSelectedPiece = false;
	private boolean isWhitePlayersTurn = true; //white player always starts
	private Chess chess;
	private Tile[][] chessBoard;
	private BoardGUI boardGUI;
	private ArrayList<CustomJButton> allButtons;
	private Tile startTile = null;
	private Tile endTile = null;
	
	private JPanel gridPanel;
	private JPanel headPanel;
	
	private JTextArea statusMessage;



	
	public BoardGUI(){
		chess = new Chess();
		chessBoard = chess.populateBoard();
//		setLayout(new GridLayout(8, 8));
		allButtons = new ArrayList<>();
		
		headPanel();
		gridPanel(chessBoard);
		mainPanel();
		
//		paintBoard(chessBoard);
		
		this.setTitle("My Fabulous Chess Börd"); // Setting the title of board
//		this.setLayout(new GridLayout(8, 8)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(600,600); // Size of the chess board
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void mainPanel() {
		JComponent mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(5,5));
		mainPanel.add(headPanel, BorderLayout.NORTH);
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		
		add(mainPanel);
	}

	//adds menu and textarea above gameboard
	public void headPanel() {
		headPanel = new JPanel();
		headPanel.setLayout(new FlowLayout());
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("My items");
		
		JMenuItem newGameMenuItem = new JMenuItem("New game");
		newGameMenuItem.addActionListener(new NewGameActionListener());
		menu.add(newGameMenuItem);
		
		menuBar.add(menu);
		
		statusMessage = new JTextArea();
		statusMessage.setText("Start new game under menu!");
		statusMessage.setEditable(false);
		headPanel.add(statusMessage);
		
		headPanel.add(menuBar, 0);
	}
	
	public void gridPanel(Tile[][] chessBoard) {
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(8,8));
		
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				
				//creates a tile, adds button and actionlistener to tile
				Tile tile = chessBoard[row][col];
				CustomJButton tileButton = new CustomJButton(tile);
				TileButtonActionListener mal = new TileButtonActionListener();
				tileButton.addActionListener(mal);
				tileButton.setPreferredSize(new Dimension(100, 100));
//				tileButton.setBorder(BorderFactory.createLineBorder(Color.green));
				gridPanel.add(tileButton);
				tile.setTileButton(tileButton);
				allButtons.add(tileButton);
			
				paintCheckeredPattern(row, col, tileButton);
				
			}
		}
	}
	
	public void resetCheckeredPattern() {
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Tile tile = chessBoard[row][col];
				paintCheckeredPattern(row, col, tile.getTileButton());
			}
		}
	}
	
	public void paintCheckeredPattern(int row, int col, CustomJButton button) {
		if((row % 2) == (col % 2)) //Determines checkered pattern
			button.setBackground(lightColor);
		else
			button.setBackground(darkColor);
	}
	
	public void placeChessPieces(Tile[][] chessBoard) {
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				
				Tile tile = chessBoard[row][col];
				CustomJButton tileButton = tile.getTileButton();
				
				if(tile.getPiece() != null){
					if(tile.getPiece().getColor() == game.Color.WHITE){
						if(tile.getPiece().getType() == game.Type.PAWN)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/whitePawn.png"));
						else if(tile.getPiece().getType() == game.Type.ROOK)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/whiteRook.png"));
						else if(tile.getPiece().getType() == game.Type.KNIGHT)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/whiteKnight.png"));
						else if(tile.getPiece().getType() == game.Type.BISHOP)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/whiteBishop.png"));
						else if(tile.getPiece().getType() == game.Type.QUEEN)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/whiteQueen.png"));
						else if(tile.getPiece().getType() == game.Type.KING)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/whiteKing.png"));
					}else if(tile.getPiece().getColor() == game.Color.BLACK){
						if(tile.getPiece().getType() == game.Type.PAWN)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/blackPawn.png"));
						else if(tile.getPiece().getType() == game.Type.ROOK)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/blackRook.png"));
						else if(tile.getPiece().getType() == game.Type.KNIGHT)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/blackKnight.png"));
						else if(tile.getPiece().getType() == game.Type.BISHOP)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/blackBishop.png"));
						else if(tile.getPiece().getType() == game.Type.QUEEN)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/blackQueen.png"));
						else if(tile.getPiece().getType() == game.Type.KING)
							tileButton.setIcon(new ImageIcon("D:/Projekt/ChessGame/src/GUI/img/blackKing.png"));
					}
				}else{
					tileButton.setIcon(null);
				}
			}
		}
	}
	
	public void tick() {
		if(isWhitePlayersTurn) {
			statusMessage.setText("White player's turn");
		}else {
			statusMessage.setText("Black player's turn");
		}
	}
	

	
	public void changePlayerTurn() {
		if(isWhitePlayersTurn)
			isWhitePlayersTurn = false;
		else
			isWhitePlayersTurn = true;
	}
	
	public boolean playerSelectedAlliedPiece(CustomJButton button) {
		return isWhitePlayersTurn && button.getTile().getPiece() != null && button.getTile().getPiece().getColor() == game.Color.WHITE ||
				!isWhitePlayersTurn && button.getTile().getPiece() != null && button.getTile().getPiece().getColor() == game.Color.BLACK;
	}
	
	public static void main(String[] args) {
		new BoardGUI();
	}
	
	class NewGameActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			placeChessPieces(chessBoard);
			tick();

		}
		
	}
	
	
	
	class TileButtonActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){
			
			CustomJButton button = (CustomJButton)e.getSource();
			
			
			if(!hasSelectedPiece && playerSelectedAlliedPiece(button)){
				tick();
				startTile = button.getTile();
				startTile.setBackground(java.awt.Color.blue);
				chess.findAllValidMoves(startTile);
				hasSelectedPiece = true;
			}else if(hasSelectedPiece) {
				endTile = button.getTile();
				if(chess.isValidMove(startTile, endTile, chessBoard)) {
					chess.movePiece(startTile, endTile);
//					chessBoard = chess.getChessBoard();
					placeChessPieces(chessBoard);
					hasSelectedPiece = false;
//					chess.resetTileBorders();
					resetCheckeredPattern();
					changePlayerTurn(); //changes it to other players turn
					tick();
				}else {
					hasSelectedPiece = false;
//					chess.resetTileBorders();
					resetCheckeredPattern();
					statusMessage.setText("Illegal move");
				}	

			}else {
				statusMessage.setText("Wrong colored piece or empty tile selected");
			}
		}	
	}
}

