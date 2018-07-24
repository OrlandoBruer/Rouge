import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 */

/**
 * @author Orly
 *
 */
public class TestCard extends Card {

	private JLabel topText;
	private JLabel bottomImage;
	
	/**
	 * @param cardType
	 * @param sprite
	 */
	public TestCard() {
		super(CardType.Test, new ImageIcon("assets/testCard.png"));
		
		topText = new JLabel("Greetings, Stranger.");
		topText.setBounds(31, 15, 150, 50);
		topText.setVisible(true);
		topText.setForeground(Color.WHITE);
		this.add(topText);
		
		bottomImage = new JLabel(new ImageIcon("assets/blankSquare.png"));
		bottomImage.setBounds(120, 220, 50, 50);
		bottomImage.setVisible(true);
		this.add(bottomImage);
	}

}
