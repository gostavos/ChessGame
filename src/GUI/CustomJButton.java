package GUI;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import board.Tile;

public class CustomJButton extends JButton{

	private Tile tile;
	
	public CustomJButton(Tile tile) {
		this.tile = tile;
		this.setBorder(BorderFactory.createLineBorder(java.awt.Color.black));
		// TODO Auto-generated constructor stub
	}
	
	public Tile getTile(){
		return tile;
	}

}
