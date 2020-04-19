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

	/**
     * Constructor for CardBuilder
	 * @param type, Card type that want to be build
     */
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
	
	/**
     * Set id card
	 * @param id, id that want to be set
     */
	public CardBuilder setId(int id){
		this.id = id;
		return this;
	}

	/**
     * Set card name
	 * @param name, name that want to be set
     */
	public CardBuilder setName(String name){
		this.name = name;
		return this;
	}

	/**
     * Set card element
	 * @param element, element that want to be set
     */
	public CardBuilder setElement(Element element){
		this.element = element;
		return this;
	}

	/**
     * Set card description
	 * @param description, description that want to be set
     */
	public CardBuilder setDescription(String description){
		this.description = description;
		return this;
	}

	/**
     * Set card image path
	 * @param imagepath, imagepath that want to be set
     */
	public CardBuilder setImagePath(String imagepath){
		this.imagepath = imagepath;
		return this;
	}

	/**
     * Set card power
	 * @param power, power that want to be set
     */
	public CardBuilder setPower(int power){
		this.power = power;
		return this;
	}

	/**
     * Set card attack
	 * @param attack, attack that want to be set
     */
	public CardBuilder setAttack(int attack){
		this.attack = attack;
		return this;
	}

	/**
     * Set card defense
	 * @param defense, defense that want to be set
     */
	public CardBuilder setDefense(int defense){
		this.defense = defense;
		return this;
	}

	/**
     * Build aura card
     */
	public Aura buildAura(){
		return new Aura(this);
	}

	/**
     * Build character card
     */
	public Character buildCharacter(){
		return new Character(this);
	}

	/**
     * Build land card
     */
	public Land buildLand(){
		return new Land(this);
	}

	/**
     * Build destroy card
     */
	public Destroy buildDestroy(){
		return new Destroy(this);
	}

	/**
     * Build power up card
     */
	public PowerUp buildPowerUp() {return new PowerUp(this); }

	/**
     * Build card
     */
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
