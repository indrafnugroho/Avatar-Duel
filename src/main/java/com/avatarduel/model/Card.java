
class Card{
	private int id;
	private String name;
	private Element element;
	private String description;
	private String imagepath;
	private boolean isSummoned;
	private boolean isSummonable;


	public Card(CardBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.element = builder.element;
		this.description = builder.description;
		this.imagepath = builder.imagepath;
		this.isSummoned = false;
		this.isSummonable = false;
	}

	public int getId(){
		return this.id;
	}

	public Element getElement(){
		return this.element;
	}

	public String getName(){
		return this.name;
	}

	public String getDescription(){
		return this.description;
	}

	public String getPath(){
		return this.imagepath;
	}

	public boolean getStatus(){
		return this.isSummoned;
	}

	public boolean getSummonable(){
		return this.isSummonable;
	}
}