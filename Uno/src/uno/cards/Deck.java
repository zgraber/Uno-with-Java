package uno.cards;

import java.util.ArrayList;
import java.util.Collections;

import uno.resources.Constants;

public class Deck {
	private ArrayList<NumberCard> numberCards;
	private ArrayList<ActionCard> actionCards;
	private ArrayList<UNOCard> UNOCards;

	//Initializes new deck and its ArrayLists
	public Deck() {
		// TODO Auto-generated constructor stub
		numberCards = new ArrayList<NumberCard>();
		actionCards = new ArrayList<ActionCard>();
		UNOCards = new ArrayList<UNOCard>();
		
	}
	
	//Adds all 108 cards into the draw deck
	public void addCards() {
		
		for(int color : Constants.COLORS) {
			
			for(int j = 0; j < 20; j++) {
				numberCards.add(new NumberCard(color, Constants.COLOR_NAMES[color] + " " + (j/2), (j/2)));
				if (j == 0) j++;
			}
			for(String action : Constants.ACTIONS) {
				actionCards.add(new ActionCard(color, Constants.COLOR_NAMES[color] + " " + action, action));
			}
			
		}
		
		UNOCards.addAll(numberCards);
		UNOCards.addAll(actionCards);
		Collections.shuffle(UNOCards);
	}
	
	
	public UNOCard draw() {
		if(getNumberOfCards() == 0) {
			System.out.println("Deck Recreated");
			addCards();
		}
		return UNOCards.remove(0);	
	}
	
	public int getNumberOfCards() {
		return UNOCards.size();
	}
	
	public String toString() {
		String returnString = "";
		for (int i = 0; i < UNOCards.size(); i++) {
			returnString += (UNOCards.get(i).getName() + "\n");
		}
		return returnString;
	}

}
