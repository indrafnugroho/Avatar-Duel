
class Character extends Card{
	private int attack;
	private int defense;
	private int power;
	private CardType type;

	public Character(CardBuilder builder){
		super(builder);
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.power = builder.power;
	}
}