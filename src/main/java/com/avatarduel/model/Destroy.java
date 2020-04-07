
class Destroy extends Card{
	private Character linkedCard;


	public Destroy(CardBuilder builder){
		super(builder);
	}

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.linkedCard = linkedCard;
	}

	public State activate(){
		return this.linkedCard.destroy();
	}
}