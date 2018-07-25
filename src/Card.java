import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Orly
 *
 */
public abstract class Card extends JPanel {
	
	public static final int CARD_WIDTH = 200;
	public static final int CARD_HEIGHT = 300;
	public boolean isInHand;
	
	private CardType cardType;
	private ImageIcon sprite;
	private JLabel cardBack;
	private Integer handPosition;

	public Card(CardType cardType, ImageIcon sprite) {
		super();
		this.setVisible(false);
		this.setLayout(null);
		Dimension dim = new Dimension(CARD_WIDTH, CARD_HEIGHT);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
		
		cardBack = new JLabel(sprite);
		cardBack.setBounds(0, 0, CARD_WIDTH, CARD_HEIGHT);
		cardBack.setVisible(true);
		this.add(cardBack);
		
		this.cardType = cardType;
		this.sprite = sprite;
		this.handPosition = null;
	}
	
	public void renderCard(int x, int y) {
		this.setBounds(x, y, CARD_WIDTH, CARD_HEIGHT);
		this.setVisible(true);
		this.repaint();
	}
	
	/**
	 * @return the handPosition
	 */
	public Integer getHandPosition() {
		return handPosition;
	}

	/**
	 * @param handPosition the handPosition to set
	 */
	public void setHandPosition(Integer handPosition) {
		this.handPosition = handPosition;
	}
}
