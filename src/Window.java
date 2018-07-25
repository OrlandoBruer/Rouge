import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Window {
	
	private RoomMap testingRoom;
	private Player player;
	private JPanel gamePanel;
	private HUD hud;
	private Hand hand;
	public static final int widthBuffer = 200;
	public static final int heightBuffer = 220;
	
	public Window(int width, int height, String title) {
		
		// "window" is a JFrame which is the entire game window. Its only children in the component hierarchy are "gamePanel", "hand", and "hud".
		JFrame window = new JFrame(title);
		window.setLayout(null);
		
		Dimension dim = new Dimension(width + widthBuffer, height + heightBuffer);
		window.getContentPane().setPreferredSize(dim);
		window.getContentPane().setMaximumSize(dim);
		window.getContentPane().setMinimumSize(dim);
		window.setPreferredSize(dim);
		window.setMaximumSize(dim);
		window.setMinimumSize(dim);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// "gamePanel" is a JPanel which represents all the visible spectrum of the game.
		// All visible components must be attached to gamePanel
		gamePanel = new JPanel();
		
		gamePanel.setBackground(Color.BLACK);
		gamePanel.setLayout(null);
		gamePanel.setBounds(widthBuffer / 2, (heightBuffer / 2) - 25, width, height);
		gamePanel.setVisible(true);
		gamePanel.setBorder(new LineBorder(Color.BLACK));
		
		window.getContentPane().add(gamePanel);
	
		// HUD will be attached to the Window directly rather than the gamePanel
		hud = new HUD(100, 25);
		hand = new Hand(10);
		
		window.getContentPane().add(hud);
		window.getContentPane().add(hand);
		
		window.getContentPane().repaint();
		
	}
	
	public JPanel getGamePanel() {
		return gamePanel;
	}
	
	public HUD getHUD() {
		return hud;
	}
	
	public Hand getHand() {
		return hand;
	}

	/**
	 * @return the widthBuffer
	 */
	public int getWidthBuffer() {
		return widthBuffer;
	}

	/**
	 * @return the heightBuffer
	 */
	public int getHeightBuffer() {
		return heightBuffer;
	}
	
}
