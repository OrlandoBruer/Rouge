import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * 
 */

/**
 * @author Orly
 *
 */
public class Hand extends JPanel {
	
	private final Font font = new Font("Arial", 1, 20);
	private ArrayList<Card> cards;
	private int maxSize;
	private int size;
	private static final int HAND_WIDTH = Game.PANEL_WIDTH, HAND_HEIGHT = 150;
	
	public Hand(int maxSize) {
		super();
		int x = (Window.widthBuffer / 2);
		int y = ((Window.heightBuffer / 2) - 25) + Game.PANEL_HEIGHT;
		this.setBounds(x, y, HAND_WIDTH, HAND_HEIGHT);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.GREEN));
		this.setVisible(true);
		
		this.maxSize = maxSize;
		size = 0;
		cards = new ArrayList<Card>(maxSize);
	}
	
	
	public boolean addCard(Card card) {
		if (card == null || size == maxSize) {
			return false;
		}
		
		cards.add(card);
		this.add(card);
		size++;
		renderCards();
		return true;
	}
	
	private void renderCards() {
		if (cards.size() == 0) {
			return;
		}
		int gap = HAND_WIDTH / (size + 1);
		int i = 1;
		for (Card card : cards) {
			card.renderCard((i * gap) - (Card.CARD_WIDTH / 2), 0);
			i++;
		}
		
		this.repaint();
	}
}
