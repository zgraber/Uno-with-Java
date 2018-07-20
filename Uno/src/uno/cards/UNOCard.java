package uno.cards;

public abstract class UNOCard {
	public static final int GREEN = 0;
	public static final int BLUE = 1;
	public static final int RED = 2;
	public static final int YELLOW = 3;
	
	private String name;
	private int color;
	
	public UNOCard(int color, String name) {
		this.color = color;
		this.name = name;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getColor() {
		return this.color;
	}
}
