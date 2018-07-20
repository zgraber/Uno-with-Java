package uno.cards;

public class ActionCard extends UNOCard {
	private String action;
	public ActionCard(int color, String name, String action) {
		super(color, name);
		this.action = action;
		// TODO Auto-generated constructor stub
	}
	
	public String getAction() {
		return action;
	}
}
