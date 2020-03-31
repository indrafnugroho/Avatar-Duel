
class Card{
	private int id;
	private String name;
	private Element element;
	private String description;
	private String imagepath;

	public Card(CardBuilder builder){
		this.id = builder.id;
		this.name = builder.name;
		this.element = builder.element;
		this.description = builder.description;
		this.imagepath = builder.imagepath;
	}

	public static class CardBuilder{
		private int id;
		private CardType type;
		private String name;
		private Element element;
		private String description;
		private String imagepath;
		protected int power;
		protected int attack;
		protected int defense;
	
		public CardBuilder(CardType type){
			this.type = type;
		}

		public CardBuilder setId(int id){
			this.id = id;
			return this;
		}

		public CardBuilder setName(String name){
			this.name = name;
			return this;
		}

		public CardBuilder setElement(Element element){
			this.element = element;
			return this;
		}

		public CardBuilder setDescription(String description){
			this.description = description;
			return this;
		}

		public CardBuilder setImagePath(String imagepath){
			this.imagepath = imagepath;
			return this;
		}

		public CardBuilder setPower(int power){
			this.power = power;
			return this;
		}

		public Card build(){
			if(this.type == CardType.AURA){
				return new Aura(this);
			} else if (this.type == CardType.DESTROY){
				return new Destroy(this);
			} else if (this.type == CardType.POWERUP) {
				return new PowerUp(this);
			} else if (this.type == CardType.CHARACTER){
				return new Character(this);
			} else if (this.type == CardType.LAND){
				return new Land(this);
			} else {
				return new Card(this);
			}
		}
	}
}