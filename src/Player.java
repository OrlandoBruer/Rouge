import javax.swing.ImageIcon;

public class Player extends Entity {
	
	public Player(Space location) {
		super(location, 1, 3, 10, Integer.MAX_VALUE, new ImageIcon("assets/OSquare.png"), EntityType.Player);
	}
	
	public void startOfTurn() {
		this.movement = agility;
	}
}
