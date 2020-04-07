
class Character extends Card{
	private int attack;
	private int defense;
	private int power;

	public Character(CardBuilder builder){
		super(builder);
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.power = builder.power;
	}

	public void summon(int x, Position pos){
		this.isSummoned = true;
		this.state = new State(x, 1, pos);
	}

	public int attack(){
		return this.attack;
	}

	public int defense(){
		return this.defense;
	}

	public int power(){
		return this.power;
	}

	public void setEffect(int attack, int defense){
		this.attack += attack;
		this.defense += defense;
	}

	public State destroy(){
		return this.state;
	}

	public String toString(){
		String result = super.toString();
		result += "\nAttack : " + this.attack +
					"\nDefense : " + this.defense +
					"\nPower : " + this.power;
		return result;
	}
}