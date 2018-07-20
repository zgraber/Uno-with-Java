package uno.player;

import uno.cards.Deck;
import uno.cards.UNOCard;

public class Player {
	private String playerName;
	private PlayerHand playerHand;
	
	public Player(String name) {
		this.playerName = name;
		playerHand = new PlayerHand();
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void makeHand(Deck deck) {
		playerHand.populateHand(deck);
	}
	
	public void drawCard(Deck deck) {
		playerHand.addNewCard(deck);
	}
	
	public int getHandSize() {
		return playerHand.getSize();
	}
	
	public PlayerHand getPlayerHand() {
		return playerHand;
	}
	
	public void printHand() {
		System.out.println(playerHand);
	}
	
	public UNOCard getCardByNum(int num) {
		return playerHand.getCard(num);
	}
	
	public void removeCardFromHand(int num) {
		playerHand.removeCard(num);
	}

}
