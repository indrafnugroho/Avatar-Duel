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

	public CardBuilder setAttack(int attack){
		this.attack = attack;
		return this;
	}

	public CardBuilder setDefense(int defense){
		this.defense = defense;
		return this;
	}

	public Aura buildAura(){
		return new Aura(this);
	}

	public Character buildCharacter(){
		return new Character(this);
	}

	public Land buildLand(){
		return new Land(this);
	}

	public Destroy buildDestroy(){
		return new Destroy(this);
	}

	public PowerUp buildPowerUp() {return new PowerUp(this); }

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
