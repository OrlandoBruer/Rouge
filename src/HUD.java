import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * @author Orly
 *
 */
public class HUD extends JPanel {
	
	private JLabel movementAgility, healthVitality, attackStrength;
	private final Font font = new Font("Arial", 1, 20);
	
	public HUD(int x, int y) {
		super();
		this.setBounds(x, y, Game.PANEL_WIDTH, 50);
		this.setVisible(true);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.BLUE));
	}
	
	public void populateHUD() {
		movementAgility = new JLabel(String.format("Mov: %d/%d", Game.player.getMovement(), Game.player.getAgility()));
		movementAgility.setBounds(0, 0, 100, 50);
		movementAgility.setVisible(true);
		movementAgility.setFont(font);
		movementAgility.setForeground(Color.BLUE);
		
		healthVitality = new JLabel(String.format("HP: %d/%d", Game.player.getHealth(), Game.player.getVitality()));
		healthVitality.setBounds(100, 0, 100, 50);
		healthVitality.setVisible(true);
		healthVitality.setFont(font);
		healthVitality.setForeground(Color.RED);
		
		this.add(movementAgility);
		this.add(healthVitality);
		this.getParent().repaint();
	}
	
	public void refreshHUD() {
		movementAgility.setText(String.format("Mov: %d/%d", Game.player.getMovement(), Game.player.getAgility()));
	}
}
