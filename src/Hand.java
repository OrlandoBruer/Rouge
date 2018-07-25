import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private static final int HAND_WIDTH = Game.PANEL_WIDTH, HAND_HEIGHT = 300;
	private CardHandler cardHandler;
	
	public Hand(int maxSize) {
		super();
		int x = (Window.widthBuffer / 2);
		int y = (((Window.heightBuffer / 2) - 25) + Game.PANEL_HEIGHT) - 150;
		this.setBounds(x, y, HAND_WIDTH, HAND_HEIGHT);
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.GREEN));
		this.setVisible(true);
		
		this.maxSize = maxSize;
		size = 0;
		cards = new ArrayList<Card>(maxSize);
		cardHandler = new CardHandler();
	}
	
	
	public boolean addCard(Card card) {
		if (card == null || size == maxSize) {
			return false;
		}
		
		card.isInHand = true;
		card.setHandPosition(new Integer(cards.size()));
		
		cards.add(card);
		this.add(card);
		card.addMouseListener(cardHandler);
		
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
			card.renderCard((i * gap) - (Card.CARD_WIDTH / 2), 150);
			i++;
		}
		
		this.repaint();
	}


	/**
	 * @return an Array of Cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}


	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}


	/**
	 * @return the size
	 */
	public int getHandSize() {
		return size;
	}


	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	private class CardHandler implements MouseListener {

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseEntered(MouseEvent e) {

			Card card = (Card)e.getSource();
			card.renderCard(card.getX(), card.getY() - 150);
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseExited(MouseEvent e) {
			
			Card card = (Card)e.getSource();
			Game.gamePanel.repaint();
			card.renderCard(card.getX(), card.getY() + 150);
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
