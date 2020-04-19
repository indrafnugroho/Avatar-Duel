package com.avatarduel.card;

import com.avatarduel.card.Element;
import com.avatarduel.card.Land;
import com.avatarduel.card.CardType;

public class CardBuilder{
	public int id;
	public CardType type;
	public String name;
	public Element element;
	public String description;
	public String imagepath;
	public int power;
	public int attack;
	public int defense;

	// Constructor for CardBuilder with type(CardType) as parameter
	public CardBuilder(CardType type){
		this.id = 0;
		this.name = "Card";
		this.element = Element.AIR;
		this.description = "This is dummy card";
		this.imagepath = "/";
		this.type = type;
		this.power = 1;
		this.attack = 1;
		this.defense = 1;
	}
	
	// Set card Id with id as parameter
	public CardBuilder setId(int id){
		this.id = id;
		return this;
	}

	// Set card name with name as parameter
	public CardBuilder setName(String name){
		this.name = name;
		return this;
	}

	// Set card element with element as parameter
	public CardBuilder setElement(Element element){
		this.element = element;
		return this;
	}

	// Set card description with description as parameter
	public CardBuilder setDescription(String description){
		this.description = description;
		return this;
	}

	// Set card ImagePath with imagepath as parameter
	public CardBuilder setImagePath(String imagepath){
		this.imagepath = imagepath;
		return this;
	}

	// Set card power with power as parameter
	public CardBuilder setPower(int power){
		this.power = power;
		return this;
	}

	// Set card attack with attack as parameter
	public CardBuilder setAttack(int attack){
		this.attack = attack;
		return this;
	}

	// Set card defense with defense as parameter
	public CardBuilder setDefense(int defense){
		this.defense = defense;
		return this;
	}

	// Method to build Aura from card
	public Aura buildAura(){
		return new Aura(this);
	}

	// Method to build Character from card
	public Character buildCharacter(){
		return new Character(this);
	}

	// Method to build Land from card
	public Land buildLand(){
		return new Land(this);
	}

	// Method to build Destroy from card
	public Destroy buildDestroy(){
		return new Destroy(this);
	}

	// Method to build PowerUp from card
	public PowerUp buildPowerUp() {return new PowerUp(this); }

	// Method to build card
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
			return null;
		}
	}
}
