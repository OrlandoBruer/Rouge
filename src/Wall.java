import javax.swing.ImageIcon;

/**
 * 
 */

/**
 * @author Orly
 *
 */
public class Wall extends Space {
	
	public Wall(int pixelX, int pixelY, int gridX, int gridY, int adjListIndex, RoomMap roomMap) {
		
		super(pixelX, pixelY, gridX, gridY, adjListIndex, new ImageIcon("assets/stoneWall.png"), roomMap);
		this.setWalkable(false);
	}
}
