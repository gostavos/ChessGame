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

import board.Tile;
import game.Chess;
import game.Type;
import pieces.Piece;

public class BoardGUI extends JFrame{

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private Color darkColor = Color.DARK_GRAY;
	private Color lightColor = Color.LIGHT_GRAY;
	private boolean hasSelectedPiece = false;
	private Chess chess;
	private Tile[][] chessBoard;
	private BoardGUI boardGUI;
	private ArrayList<CustomJButton> allButtons;
	private Tile startTile = null;
	private Tile endTile = null;


	
	public BoardGUI(){
		chess = new Chess();
		chessBoard = chess.populateBoard();
		setLayout(new GridLayout(8, 8));
		allButtons = new ArrayList<>();
		paintBoard(chessBoard);
		
		this.setTitle("My Fabulous Chess Börd"); // Setting the title of board
//		this.setLayout(new GridLayout(8, 8)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(850, 850); // Size of the chess board
		this.setVisible(true);
	}
	
	public void paintBoard(Tile[][] chessBoard){
		

//		GridBagConstraints gbc = new GridBagConstraints();
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				
//				gbc.gridx = col;
//				gbc.gridy = row;
				
				Tile tile = chessBoard[row][col];
				CustomJButton tileButton = tile.getTileButton();
				boolean isnull = (tileButton == null);
				System.out.println(tile.getY() + " " + tile.getX() + " tileButton = null: " + isnull);
				if(tileButton == null || allButtons.size() < 63){
					tileButton = new CustomJButton(tile);
					MyActionListener mal = new MyActionListener();
					tileButton.addActionListener(mal);
					tileButton.setPreferredSize(new Dimension(100, 100));
					add(tileButton);
					tile.setTileButton(tileButton);
					allButtons.add(tileButton);
				}else{
					tileButton = tile.getTileButton();
				}


				if((row % 2) == (col % 2)) //Determines checkered pattern
					tileButton.setBackground(lightColor);
				else
					tileButton.setBackground(darkColor);
				

				if(tile.getPiece() != null){
					if(tile.getPiece().getColor() == game.Color.WHITE){
						if(tile.getPiece().getType() == game.Type.PAWN)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whitePawn.png"));
						else if(tile.getPiece().getType() == game.Type.ROOK)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteRook.png"));
						else if(tile.getPiece().getType() == game.Type.KNIGHT)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteKnight.png"));
						else if(tile.getPiece().getType() == game.Type.BISHOP)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteBishop.png"));
						else if(tile.getPiece().getType() == game.Type.QUEEN)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteQueen.png"));
						else if(tile.getPiece().getType() == game.Type.KING)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteKing.png"));
					}else if(tile.getPiece().getColor() == game.Color.BLACK){
						if(tile.getPiece().getType() == game.Type.PAWN)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackPawn.png"));
						else if(tile.getPiece().getType() == game.Type.ROOK)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackRook.png"));
						else if(tile.getPiece().getType() == game.Type.KNIGHT)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackKnight.png"));
						else if(tile.getPiece().getType() == game.Type.BISHOP)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackBishop.png"));
						else if(tile.getPiece().getType() == game.Type.QUEEN)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackQueen.png"));
						else if(tile.getPiece().getType() == game.Type.KING)
							tileButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackKing.png"));
					}
				}else{
					tileButton.setIcon(null);
				}

			}
		}
	}
	
	public static void main(String[] args) {
		new BoardGUI();
	}
	
	class MyActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){

			CustomJButton button = (CustomJButton)e.getSource();


			
			if(!hasSelectedPiece && button.getTile().getPiece() != null){

				startTile = button.getTile();
				hasSelectedPiece = true;
			}else if(hasSelectedPiece){
				endTile = button.getTile();
				chessBoard = chess.movePiece(startTile, endTile);
				paintBoard(chessBoard);
				hasSelectedPiece = false;
			}
			
			

		}
		
	}

}

