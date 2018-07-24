import javax.swing.ImageIcon;

/**
 * 
 */

/**
 * @author Orly
 *
 */
public class Enemy extends Entity {
	
	public Enemy(Space location) {
		super(location, 1, 2, 5, 3, new ImageIcon("assets/XSquare.png"), EntityType.Enemy);
		
	}
	
}
