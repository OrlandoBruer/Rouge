import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;


public class Game {

	public static final int PANEL_WIDTH = 960, PANEL_HEIGHT = 720;
	public static RoomMap currentRoom;
	public static JPanel gamePanel;
	public static Player player;
	public static boolean isPlayerTurn;
	public static Window window;
	public static Hand hand;
	
	// test comment
	
	public static void main(String[] args) throws InterruptedException {
		
		window = new Window(PANEL_WIDTH, PANEL_HEIGHT, "Rouge");
		gamePanel = window.getGamePanel();
		hand = window.getHand();
		
		for (int i = 0; i < 10; i++) {
			hand.addCard(new TestCard());
		}
		
		try {
			currentRoom = new RoomMap(18, 14, gamePanel, new File("rooms/testRoom.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		player = new Player(currentRoom.getSpace(0, 0));
		currentRoom.getSpace(0, 0).setEntity(player);
		gamePanel.repaint();
		
		window.getHUD().populateHUD();
		
		
		// MAIN GAME LOOP
		isPlayerTurn = true;
		while (true) {
			playerTurn();
			
			while (isPlayerTurn) {
				window.getHUD().refreshHUD();
				currentRoom.showMoveableSpaces();
				Thread.sleep(10);
				if (player.getMovement() <= 0) {
					isPlayerTurn = !isPlayerTurn;
				}
			}
			Thread.sleep(10);
			enemyTurn();
			isPlayerTurn = !isPlayerTurn;
		}
	}
	
	public static void enemyTurn() {
		currentRoom.enemyManager.startOfTurn();
	}
	
	public static void playerTurn() {
		player.startOfTurn();
	}
}
