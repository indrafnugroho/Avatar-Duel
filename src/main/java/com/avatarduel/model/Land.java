
public class Land extends Card{
	private Status linkedStatus;

	public Land(CardBuilder builder){
		super(builder);
	}

	public void summon(Status linkedStatus){
		this.isSummoned = true;
		this.linkedStatus = linkedStatus;
	}

	public void activate(){
		this.linkedStatus.addStatus(super.getElement());
	}
}