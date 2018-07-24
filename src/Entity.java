import javax.swing.ImageIcon;

/**
 * @author Orly
 *
 */
public abstract class Entity {

	// Space object where the Entity is located
	protected Space location;

	// stats of the Entity
	// strength = attack power
	// agility = max movement per turn, movement = remaining movement
	// vitality = max health, health = remaining health
	// vision = how far the entity can see / detect the player
	protected int strength, agility, vitality, vision;
	protected int health, movement;

	// Sprite of the entity
	protected ImageIcon sprite;

	// the Entity's type
	protected EntityType type;

	/**
	 * @param location
	 * @param strength
	 * @param agility
	 * @param vitality
	 * @param vision
	 * @param sprite
	 * @param type
	 */
	public Entity(Space location, int strength, int agility, int vitality, int vision, ImageIcon sprite, EntityType type) {

		this.location = location;
		this.location.setEntity(this);
		this.strength = strength;
		this.agility = agility;
		this.movement = agility;
		this.vitality = vitality;
		this.health = vitality;
		this.sprite = sprite;
		this.type = type;
		this.vision = vision;
		
	}

	public boolean canMoveTo(Space desiredSpace) {
		if (desiredSpace == this.location || !desiredSpace.isWalkable() || !desiredSpace.isEmpty()) {
			return false;
		}
		
		int distance = desiredSpace.getRoomMap().distanceBetween(location, desiredSpace);
		if (distance <= this.movement) {
			return true;
		} else {
			return false;
		}
	}

	public void move(Space newLocation) {
		// subtracting movement from remaining movement
		this.setMovement(this.getMovement() - location.getRoomMap().distanceBetween(this.getLocation(), newLocation));
		
		// removing this entity from previous location
		this.location.setEntity(null);
		
		// setting the location of this entity to newLocation, and the entity of the new location to this
		newLocation.setEntity(this);
		this.location = newLocation;
	}

	// Getters
	public Space getLocation() {

		return location;
	}

	public EntityType getType() {

		return type;
	}

	public int getStrength() {

		return strength;
	}

	public int getAgility() {

		return agility;
	}

	public int getVitality() {

		return vitality;
	}

	public ImageIcon getSprite() {

		return sprite;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return the movement
	 */
	public int getMovement() {
		return movement;
	}
	
	/**
	 * @return the vision
	 */
	public int getVision() {
		return vision;
	}

	/**
	 * @param vision the vision to set
	 */
	public void setVision(int vision) {
		this.vision = vision;
	}

	// Setters
	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @param movement the movement to set
	 */
	public void setMovement(int movement) {
		this.movement = movement;
	}

	
	public void setLocation(Space location) {

		this.location = location;
	}

	public void setType(EntityType type) {

		this.type = type;
	}

	public void setStrength(int strength) {

		this.strength = strength;
	}

	public void setAgility(int agility) {

		this.agility = agility;
	}

	public void setVitality(int vitality) {

		this.vitality = vitality;
	}

	public void setSprite(ImageIcon sprite) {

		this.sprite = sprite;
	}
}
