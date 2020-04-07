import java.util.List;

class Player{
	private String name;
	private List<Card> deck;
	private List<Card> cardOnHand;
	private Status status;
	private List<Card> cardOnTable;
	private int lifePoint;

	public Player(){
		this.name = "Player";
	}
}