package uno.player;

import java.util.ArrayList;

import uno.cards.Deck;
import uno.cards.UNOCard;

class PlayerHand {
	private ArrayList<UNOCard> playerHand;
	protected PlayerHand() {
		playerHand = new ArrayList<UNOCard>();
	}
	
	protected void populateHand(Deck deck) {
		int startingHandSize = 7;
		for(int i = 0; i < startingHandSize; i++) 
			playerHand.add(deck.draw());
	}
	
	
	
	protected void addNewCard(Deck deck) {
		playerHand.add(deck.draw());
	}
	
	protected int getSize() {
		return playerHand.size();
	}
	
	protected UNOCard getCard(int index) {
		return playerHand.get(index);
	}
	
	public String toString() {
		String returnString = "";
		for(int i = 0; i < playerHand.size(); i++) {
			returnString += (i + ": " + playerHand.get(i).getName() + "\n");
		}
		return returnString;
	}

	protected void removeCard(int index) {
		playerHand.remove(index);
		
	}
	
	
}
