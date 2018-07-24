import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.LinkedList;

public class EnemyManager {
	
	private ArrayList<Enemy> enemyList;
	private RoomMap roomMap;
	
	public EnemyManager(RoomMap roomMap) {
		enemyList = new ArrayList<Enemy>();
		this.roomMap = roomMap;
	}
	
	public void add(Enemy enemy) {
		enemyList.add(enemy);
	}
	
	public void startOfTurn() {
		for (Enemy enemy : enemyList) {
			// resetting movement to max
			enemy.setMovement(enemy.getAgility());
			Space origin = enemy.getLocation();
			
			// TO DO: CHECK IF PLAYER IS WITHIN COMBAT RANGE
			
			// Check every space to find if the player is in vision range of the enemy
			
			boolean alreadyMoved = false;
			for (int x = 0; x < origin.getRoomMap().getWidth(); x++) {
				for (int y = 0; y < origin.getRoomMap().getHeight(); y++) {
					if (origin.getRoomMap().getSpace(x, y) == origin) {
						continue;
					}
					Space destination = origin.getRoomMap().getSpace(x, y);
					
					// if the player is there, move the enemy as far along a path to the player as possible
					
					if (roomMap.distanceBetween(origin, destination) <= enemy.getVision() && destination == roomMap.getPlayerLocation()) {
						 ArrayList<Space> path = roomMap.pathBetween(origin, destination);
						 
						 for (int i = path.size() - 1; i >= 0; i--) {
							 if (enemy.canMoveTo(path.get(i))) {
								 enemy.move(path.get(i));
							 }
						 }
						 alreadyMoved = true;
					}
					if (alreadyMoved) {
						 break;
					}
				}
				if (alreadyMoved) {
					break;
				}
			}
			
			// TO DO: SET THE ENEMY ON A DEFAULT PATH / MAKE THEM WANDER AIMLESSLY
			
		}
	}
}
