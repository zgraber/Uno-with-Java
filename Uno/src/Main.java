import uno.game.Game;
import uno.resources.Constants;

public class Main {

	public static void main(String[] args) {
		boolean newGame = false;
		do {
			Game game = new Game();
			game.startGame();
			System.out.print("New Game(Y/N)?: ");
			String ans = Constants.SCAN.nextLine();
			if(ans.toLowerCase().charAt(0) == 'y') newGame = true;
			
		}while (newGame);
		System.out.println("Bye!");
		Constants.SCAN.close();
	}

}
