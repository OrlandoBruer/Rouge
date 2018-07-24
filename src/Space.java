import javax.swing.ImageIcon; 
import javax.swing.JButton;

public class Space extends JButton {

	protected static final int WIDTH = 50, HEIGHT = 50;
	protected int gridX, gridY;
	protected int adjListIndex;
	protected boolean isWalkable;
	protected final ImageIcon defaultSprite;
	protected ImageIcon currentSprite;
	protected Entity entity;
	protected RoomMap roomMap;

	/**
	 * @param pixelX
	 * @param pixelY
	 * @param gridX
	 * @param gridY
	 * @param adjListIndex
	 * @param defaultSprite
	 */
	public Space(int pixelX, int pixelY, int gridX, int gridY, int adjListIndex, ImageIcon defaultSprite, RoomMap roomMap) {

		super("");
		this.setBounds(pixelX, pixelY, WIDTH, HEIGHT);
		this.defaultSprite = defaultSprite;
		this.currentSprite = defaultSprite;
		this.setIcon(defaultSprite);
		this.setVisible(true);
		this.adjListIndex = adjListIndex;
		this.roomMap = roomMap;

//		this.setText(new Integer(adjListIndex).toString());
		
		this.gridX = gridX;
		this.gridY = gridY;
		this.isWalkable = true;
		this.entity = null;
	}

	/**
	 * @return the roomMap
	 */
	public void setSprite(ImageIcon sprite) {
		if (sprite == null) {
			this.setIcon(defaultSprite);
			return;
		}
		
		this.setIcon(sprite);
		this.currentSprite = sprite;
	}
	
	public RoomMap getRoomMap() {
		return roomMap;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;

		// if argument is null, set Icon to default
		if (entity == null) {
			this.setIcon(defaultSprite);
			return;
		}

		// else, set it to the new Entity's icon
		this.setIcon(entity.getSprite());
	}

	public boolean isEmpty() {
		if (entity == null) {
			return true;
		} else {
			return false;
		}
	}

	public int getGridX() {
		return gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public int getAdjListIndex() {
		return adjListIndex;
	}

	public boolean isPlayerLocation() {
		if (isEmpty()) {
			return false;
		}
		if (getEntity().getType() == EntityType.Player) {
			return true;
		} else
			return false;
	}

	public boolean isWalkable() {
		return isWalkable;
	}
	
	public void setWalkable(boolean isWalkable) {
		this.isWalkable = isWalkable;
	}
}
