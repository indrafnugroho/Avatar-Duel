
class Card{
	private int id;
	private String name;
	private Element element;
	private String description;
	private String imagepath;
	private boolean isSummoned;
	private boolean isSummonable;

	public Card(){
		this.id = 0;
		this.name = "New Card";
		this.description = "This is new card";
		this.element = Element.AIR;
		this.imagepath = "";
		this.isSummoned = false;
		this.isSummonable = false;
	}

	public Card(int id, String name, Element element, String description, String imagepath){
		this.id = id;
		this.name = name;
		this.description = description;
		this.element = element;
		this.imagepath = imagepath;
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