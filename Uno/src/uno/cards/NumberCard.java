package uno.cards;

public class NumberCard extends UNOCard {
	private int number;
	
	public NumberCard(int color, String name, int number) {
		super(color, name);
		// TODO Auto-generated constructor stub
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}

}
