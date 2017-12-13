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
import game.Chess;

public class BoardGUI extends JFrame{

	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private Color darkColor = Color.DARK_GRAY;
	private Color lightColor = Color.LIGHT_GRAY;
	private JButton chessButton = null;
	private JButton pieceToMoveButton = null;


	
	public BoardGUI(String title){
		paintBoard();
		
		this.setTitle(title); // Setting the title of board
//		this.setLayout(new GridLayout(8, 8)); // GridLayout will arrange elements in Grid Manager 8 X 8
		this.setSize(850, 850); // Size of the chess board
		this.setVisible(true);
	}
	
	public void paintBoard() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				gbc.gridx = col;
				gbc.gridy = row;
				
				JButton chessButton = new JButton();
				if((row % 2) == (col % 2)) //Determines checkered pattern
					chessButton.setBackground(lightColor);
				else
					chessButton.setBackground(darkColor);
				
				if(row == 1)
					chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackPawn.png"));
				
				if(row == 6)
					chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whitePawn.png"));
				
				if(row == 0){
					if(col == 0 || col == 7)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackRook.png"));
					
					if(col == 1 || col == 6)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackKnight.png"));
					
					if(col == 2 || col == 5)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackBishop.png"));
					
					if(col == 3)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackQueen.png"));
					
					if(col == 4)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/blackKing.png"));
				}
				
				if(row == 7){
					if(col == 0 || col == 7)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteRook.png"));
					
					if(col == 1 || col == 6)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteKnight.png"));
					
					if(col == 2 || col == 5)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteBishop.png"));
					
					if(col == 3)
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteQueen.png"));
					
					if(col == 4){
						chessButton.setIcon(new ImageIcon("F:/Projekt/ChessGame/src/GUI/img/whiteKing.png"));
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
		String title = "My Chess Board";
		Chess c = new Chess();
		BoardGUI boardGUI = new BoardGUI(title);
	}
	
	class MyActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){
			JButton button = (JButton)e.getSource();
			
			if(pieceToMoveButton == null){
				pieceToMoveButton = button;
			}else{
				button.setIcon(pieceToMoveButton.getIcon());
				pieceToMoveButton.setIcon(null);
				pieceToMoveButton = null;
			}
		}
		
	}

}

