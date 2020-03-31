public class CardBuilder{
	public int id;
	public String name;
	public Element element;
	public String description;
	public String imagepath;
	public CardType type;
	public int power;
	public int attack;
	public int defense;

	public static void setId(int id){
		this.id = id;
	}

	public static void setName(String name){
		this.name = name;
	}

	public static void setElement(Element element){
		this.element = element;
	}

	public static void setDescription(String description){
		this.description = description;
	}

	public static void setImagePath(String imagepath){
		this.imagepath = imagepath;
	}

	public static void setCardType(CardType type){
		this.type = type;
	}

	public static void setPower(int power){
		if(this.type == CardType.SKILL) || (this.type == CardType.CHARACTER){
			this.power = power;
		} else {
			this.power = 0;
			throw this.type;
		}
	}

	public static void setAttack(int attack){
		if(this.type == CardType.SKILL.AURA) || (this.type == CardType.CHARACTER){
			this.attack = attack;
		} else {
			this.attack = 0;
			throw this.type;
		}
	}

	public static void setDefense(int defense){
		if(this.type == CardType.SKILL.AURA) || (this.type == CardType.CHARACTER){
			this.defense = defense;
		} else {
			this.defense = 0;
			throw this.type;
		}
	}

	public static Card build(){
		if(this.type == CardType.CHARACTER){
			return new Character(this);
		} else if (this.type == CardType.LAND){
			return new Land(this);			
		} else if(this.type == CardType.SKILL.AURA){
			return new Aura(this);
		} else if(this.type == CardType.SKILL.DESTROY){
			return new Destroy(this);
		} else if(this.type == CardType.SKILL.POWERUP){
			return new PowerUp(this);
		} else {
			throw this.type;
			return null;
		}
	}
}