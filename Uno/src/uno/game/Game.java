package uno.game;
import java.util.ArrayList;

import uno.cards.ActionCard;
import uno.cards.Deck;
import uno.cards.NumberCard;
import uno.cards.UNOCard;
import uno.player.Player;
import uno.resources.Constants;

public class Game {
	public static final int FORWARD = 1;
	public static final int BACKWARD = -1; 
	
	private ArrayList<Player> players;
	private Deck gameDeck;
	private UNOCard lastCard;
	private int gameDirection;
	private int currentPlayerNum;

	//Constructor that instantiates the deck and player list
	public Game() {
		// TODO Auto-generated constructor stub
		players = new ArrayList<Player>();
		gameDeck = new Deck();
	}
	
	//The Public method that starts the game. Should be called from main method
	public void startGame() {
		this.initGame();
		this.gameLoop();
	}
	
	//Initializes the game. Makes new players, makes new random deck, deals player hands, and draws first card
	private void initGame() {
		//Scanner scan = new Scanner(System.in);
		gameDeck.addCards();
		gameDirection = FORWARD;
		currentPlayerNum = 0;
		int numPlayers = 0;
		
		System.out.println("Welcome To UNO!!!\n");
		System.out.print("How many people are playing(2-10)?: ");
		while(!Constants.SCAN.hasNextInt())
			Constants.SCAN.next();
		
		numPlayers = Constants.SCAN.nextInt();
			
		while(numPlayers > 10 || numPlayers < 2) {
			System.out.println("Invalid Input! Input a number between 2 and 10:");
			Constants.SCAN.nextLine();
			
			while(!Constants.SCAN.hasNextInt())
				Constants.SCAN.next();
			
			numPlayers = Constants.SCAN.nextInt();
		}
		Constants.SCAN.nextLine();
		for(int i = 1; i <= numPlayers; i++) {
			System.out.print("Player " + i + " Name: ");
			
			String name = Constants.SCAN.nextLine();
			Player player = new Player(name);
			player.makeHand(gameDeck);
			players.add(player);
			
		}
		lastCard = gameDeck.draw();
	}
	
	
	//Runs the main game loop cycle till someone wins
	private void gameLoop() {
		while (!isGameCompleted()) {
			takeTurn();
			moveToNextPlayer();
		}
		Player winPlayer = getWinningPlayer();
		System.out.println(winPlayer.getPlayerName() + " wins!");
	}
	
	//Runs all the logic for taking the current player's turn
	private void takeTurn() {
		UNOCard playerCard;
		int cardNum = -1;
		Player curPlayer = players.get(currentPlayerNum);
		
		System.out.println("\nLast Card Played: \n" + lastCard.getName());
		
		System.out.println("Player " + curPlayer.getPlayerName() + "'s turn");
		curPlayer.printHand();
		
		if(!anyValidCards(curPlayer)) {
			System.out.println("No Cards can be played! Draw until a card can be played!");
			drawUntilValid(curPlayer);
			curPlayer.printHand();
		}
		
		System.out.print("What number card to play: ");
		if (Constants.SCAN.hasNextInt()) {
				cardNum = Constants.SCAN.nextInt();
		}
		playerCard = curPlayer.getCardByNum(cardNum);
		while(!isValidCard(playerCard)) {
			System.out.println("Invalid Card, choose again: ");
			if (Constants.SCAN.hasNextInt()) {
				cardNum = Constants.SCAN.nextInt();
			}
			playerCard = curPlayer.getCardByNum(cardNum);
		} 
		
		curPlayer.removeCardFromHand(cardNum);
		
		if (curPlayer.getHandSize() == 1) 
			System.out.println("\n"+ curPlayer.getPlayerName() + " calls UNO!");
		
		if(playerCard instanceof ActionCard) processActionCard((ActionCard)playerCard);
		lastCard = playerCard;
		
	}
	
	private void drawUntilValid(Player player) {
		while(!anyValidCards(player)) {
			player.drawCard(gameDeck);
		}
		
	}

	//Increments currentPlayerNum such that it loops around at the end or beginning of the list of players
	private void moveToNextPlayer() {
		currentPlayerNum += gameDirection;
		if(currentPlayerNum > players.size()-1) {
			currentPlayerNum = 0;
		} else if (currentPlayerNum < 0){
			currentPlayerNum = players.size() - 1;
		}
	}
	
	//Returns a boolean for whether the game is completed or not
	private boolean isGameCompleted() {
		boolean returnBool = false;
		for(Player p : players) {
			if (p.getHandSize() == 0) {
				returnBool = true;
			}
		}
		
		return returnBool;
	}
	
	//Returns winning player, which there should be if this method is called
	private Player getWinningPlayer() {
		for(Player p : players) {
			if(p.getHandSize() == 0) {
				return p;
			}
		}
		return null;
	}
	
	//Determines if the card a player is playing can be played
	private boolean isValidCard(UNOCard card) {
		if (card.getColor() == lastCard.getColor()) {
			return true;
		}
		
		if(card instanceof NumberCard && lastCard instanceof NumberCard) {
			if ( ((NumberCard)card).getNumber() == ((NumberCard)lastCard).getNumber()) 
				return true;
		}
		
		if(card instanceof ActionCard && lastCard instanceof ActionCard) {
			if ( ((ActionCard)card).getAction().equals(((ActionCard)lastCard).getAction())) 
				return true;
		}
		
		return false;
	}
	
	private int getNextPlayer() {
		int nextPlayer = currentPlayerNum + gameDirection;
		if(nextPlayer > players.size()-1) {
			return 0;
		} else if (nextPlayer < 0){
			return players.size() - 1;
		} else {
			return nextPlayer;
		}
	}
	
	private void processActionCard(ActionCard card) {
		switch(card.getAction()) {
			case "skip":
				System.out.println(players.get(currentPlayerNum).getPlayerName() + " skipped!");
				moveToNextPlayer();
				break;
			case "reverse":
				gameDirection *= -1;
				System.out.println("Game Direction Reversed!");
				break;
			case "draw 2":
				System.out.println(players.get(getNextPlayer()).getPlayerName() + " draws 2 cards and skips");
				players.get(getNextPlayer()).drawCard(gameDeck);
				players.get(getNextPlayer()).drawCard(gameDeck);
				moveToNextPlayer();
				break;
			default:
				System.out.println("No action found");
			
		}
	}
	
	private boolean anyValidCards(Player player) {
		boolean bool = false;
		for(int i = 0; i < player.getHandSize(); i++) {
			if(isValidCard(player.getCardByNum(i))) {
				bool = true;
			}
		}
		
		return bool;	
	}
	
	

}
