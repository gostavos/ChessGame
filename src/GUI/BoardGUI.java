package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import com.sun.glass.events.KeyEvent;

import board.Tile;
import game.Chess;
import game.Type;
import pieces.*;

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
	
	private File savedFile;



	
	public BoardGUI(){
		chess = new Chess();
		chessBoard = chess.initiateChessBoard();
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
		
		JMenuItem saveGameMenuItem = new JMenuItem("Save game");
		saveGameMenuItem.addActionListener(new SaveGameActionListener());
		menu.add(saveGameMenuItem);
		
		JMenuItem loadGameMenuItem = new JMenuItem("Load game");
		loadGameMenuItem.addActionListener(new LoadGameActionListener());
		menu.add(loadGameMenuItem);
		
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
		placeChessPieces(chessBoard);
		resetCheckeredPattern();

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
			chessBoard = chess.placePiecesOnStartingPositions();
			tick();
		}
		
	}
	
	class SaveGameActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			int answer = jfc.showSaveDialog(BoardGUI.this);
			if(answer == JFileChooser.APPROVE_OPTION) {
				savedFile = jfc.getSelectedFile();
				
				try {
					FileWriter outFile = new FileWriter(savedFile);
					PrintWriter out = new PrintWriter(outFile);
					
					if(isWhitePlayersTurn) {
						out.println("WHITES TURN");
					}else {
						out.println("BLACKS TURN");
					}
					
					for(int row = 0; row < 8; row++){
						for(int col = 0; col < 8; col++){
							if(chessBoard[row][col].getPiece() != null) {
								out.print(chessBoard[row][col].getPiece().toString() + ",");
							}else {
								out.print("EMPTY TILE,");
							}
						}
						out.println();
					}
					out.close();
					outFile.close();
				
				}catch(FileNotFoundException fe) {
					
				}catch(IOException ie) {
					
				}
			}
		}
	}
	
	class LoadGameActionListener implements ActionListener{
		
		@SuppressWarnings("resource")
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: remove any pieces on board
			
			JFileChooser jfc = new JFileChooser();
			int answer = jfc.showOpenDialog(BoardGUI.this);
			if(answer == JFileChooser.APPROVE_OPTION) {
				savedFile = jfc.getSelectedFile();
				try {
					
					FileReader in = new FileReader(savedFile);
					BufferedReader br = new BufferedReader(in);
					String rowString = br.readLine();
					
					if(rowString.equals("WHITES TURN")){
						isWhitePlayersTurn = true;
					}else if(rowString.equals("BLACKS TURN")) {
						isWhitePlayersTurn = false;
					}
					
					for(int row = 0; row < 8; row++){
						rowString = br.readLine();
						String[] wholeRow = rowString.split("\\,");
						for(int col = 0; col < 8; col++) {
							Tile tile = chessBoard[row][col];
							String tileString = wholeRow[col];
							addLoadedDataForTile(tile, tileString);
						}
					}
					
					in.close();
					br.close();
					
					tick();
					
				}catch(FileNotFoundException fe) {
					statusMessage.setText("Cannot load game from this file: " + fe.getMessage());
				}catch(IOException ie) {
					statusMessage.setText("Cannot load game from this file: " + ie.getMessage());
				}catch(IndexOutOfBoundsException io) {
					statusMessage.setText("Cannot load game from this file: " + io.getMessage());
				}catch(NullPointerException ne) {
					statusMessage.setText("Cannot load game from this file: " + ne.getMessage());
				}
			}
		}
		
		public void addLoadedDataForTile(Tile tile, String loadedData) {
			
			Piece piece = null;
		
			if(loadedData.contains("WHITE PAWN")) {
				piece = new Pawn(game.Color.WHITE, game.Type.PAWN);
			}else if(loadedData.contains("BLACK PAWN")) {
				 piece = new Pawn(game.Color.BLACK, game.Type.PAWN);
				
			}else if(loadedData.contains("WHITE ROOK")){
				 piece = new Rook(game.Color.WHITE, game.Type.ROOK);

			}else if(loadedData.contains("BLACK ROOK")) {
				 piece = new Rook(game.Color.BLACK, game.Type.ROOK);
				
			}else if(loadedData.contains("WHITE KING")) {
				 piece = new King(game.Color.WHITE, game.Type.KING);
			}else if(loadedData.contains("BLACK KING")) {
				 piece = new King(game.Color.BLACK, game.Type.KING);
			}
			
			else if(loadedData.contains("WHITE KNIGHT")){
				 piece = new Knight(game.Color.WHITE, game.Type.KNIGHT);
			}else if(loadedData.contains("BLACK KNIGHT")) {
				 piece = new Knight(game.Color.BLACK, game.Type.KNIGHT);
			}
			
			else if(loadedData.contains("WHITE BISHOP")) {
				 piece = new Bishop(game.Color.WHITE, game.Type.BISHOP);
			}else if(loadedData.contains("BLACK BISHOP")) {
				 piece = new Bishop(game.Color.BLACK, game.Type.BISHOP);
			}
			
			else if(loadedData.contains("WHITE QUEEN")) {
				 piece = new Queen(game.Color.WHITE, game.Type.QUEEN);
			}else if(loadedData.contains("BLACK QUEEN")) {
				 piece = new Queen(game.Color.BLACK, game.Type.QUEEN);
			}
			
			if(piece != null) {
				if(loadedData.contains("false")) {
					piece.setFirstMove(false);
				}
				tile.setPiece(piece);
			}
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
					hasSelectedPiece = false;
					changePlayerTurn(); 
					tick();
				}else {
					hasSelectedPiece = false;
					resetCheckeredPattern();
					statusMessage.setText("Illegal move");
				}	

			}else {
				statusMessage.setText("Wrong colored piece or empty tile selected");
			}
		}	
	}
}

