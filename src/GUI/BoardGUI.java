package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import board.Tile;
import game.Chess;
import game.Type;

public class BoardGUI extends JFrame{

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private Color darkColor = Color.DARK_GRAY;
	private Color lightColor = Color.LIGHT_GRAY;
	private JButton chessButton = null;
	private boolean hasSelectedPiece = false;
	private Chess chess;
	private Tile[][] chessBoard;
	private Tile startTile = null, endTile = null;


	
	public BoardGUI(){
		chess = new Chess();
		chessBoard = chess.populateBoard();
		paintBoard(chessBoard);
		
		this.setTitle("My Fabulous Chess Börd"); // Setting the title of board
//		this.setLayout(new GridLayout(8, 8)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(850, 850); // Size of the chess board
		this.setVisible(true);
	}
	
	public void paintBoard(Tile[][] chessBoard){
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		for(int row = 0; row < chessBoard.length; row++){
			for(int col = 0; col < chessBoard[row].length; col++){
				
				gbc.gridx = col;
				gbc.gridy = row;
				
				Tile tile = chessBoard[row][col];
				CustomJButton chessButton = new CustomJButton(tile);

				if((row % 2) == (col % 2)) //Determines checkered pattern
					chessButton.setBackground(lightColor);
				else
					chessButton.setBackground(darkColor);
				

				if(tile.getPiece() != null){
					if(tile.getPiece().getColor() == game.Color.WHITE){
						if(tile.getPiece().getType() == game.Type.PAWN)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whitePawn.png"));
						else if(tile.getPiece().getType() == game.Type.ROOK)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteRook.png"));
						else if(tile.getPiece().getType() == game.Type.KNIGHT)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteKnight.png"));
						else if(tile.getPiece().getType() == game.Type.BISHOP)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteBishop.png"));
						else if(tile.getPiece().getType() == game.Type.QUEEN)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteQueen.png"));
						else if(tile.getPiece().getType() == game.Type.KING)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteKing.png"));
					}else if(tile.getPiece().getColor() == game.Color.BLACK){
						if(tile.getPiece().getType() == game.Type.PAWN)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackPawn.png"));
						else if(tile.getPiece().getType() == game.Type.ROOK)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackRook.png"));
						else if(tile.getPiece().getType() == game.Type.KNIGHT)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackKnight.png"));
						else if(tile.getPiece().getType() == game.Type.BISHOP)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackBishop.png"));
						else if(tile.getPiece().getType() == game.Type.QUEEN)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackQueen.png"));
						else if(tile.getPiece().getType() == game.Type.KING)
							chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackKing.png"));
					}
				}

				MyActionListener mal = new MyActionListener();
				chessButton.addActionListener(mal);
				chessButton.setPreferredSize(new Dimension(100, 100));
				add(chessButton, gbc);
				
			}
		}
	}
	
	public static void main(String[] args) {
		new BoardGUI();
	}
	
	class MyActionListener implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("hasSelectedPiece: " + hasSelectedPiece);
			CustomJButton button = (CustomJButton)e.getSource();
			if(startTile != null)
				System.out.println("startTile: " + startTile.getY() + " " + startTile.getX());
			if(endTile != null)
				System.out.println("endTile: " + endTile.getY() + " " + endTile.getX());
			
			if(!hasSelectedPiece && button.getTile().getPiece() != null){
				startTile = button.getTile();
				hasSelectedPiece = true;
			}else if(hasSelectedPiece){
				endTile = button.getTile();
				System.out.println(endTile.getY() + " " +  endTile.getX());

			}
			
			if(startTile != null && endTile != null){
				System.out.println("Hej");
				chessBoard = chess.movePiece(startTile, endTile);
				paintBoard(chessBoard);
				hasSelectedPiece = false;
				startTile = null;
				endTile = null;
			}
			

		}
		
	}

}

