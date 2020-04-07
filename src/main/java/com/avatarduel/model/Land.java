
public class Land extends Card{
	public Land(CardBuilder builder){
		super(builder);
	}

	public void activate(Status s){
		s.addStatus(super.getElement());
	}
}