package GUI;
import javax.swing.JButton;
import board.Tile;

public class CustomJButton extends JButton{

	private Tile tile;
	
	public CustomJButton(Tile tile) {
		this.tile = tile;
		// TODO Auto-generated constructor stub
	}
	
	public Tile getTile(){
		return tile;
	}

}
