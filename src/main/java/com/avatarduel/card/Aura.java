package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class Aura extends Card{
	private Character linkedCard;
	private int power;
	private int attack;
	private int defense;
	private boolean isActive;
	
	// Constructor for Aura card with builder(CardBuilder) as parameter
	public Aura(CardBuilder builder){
		super(builder);
		this.power = builder.power;
		this.attack = builder.attack;
		this.defense = builder.defense;
	}

	// Return card's attack value
    public int getAttack(){
		return this.attack;
	}

	// Return card's defense value
	public int getDefense(){
		return this.defense;
	}
    
	// Return card's power value
	public int getPower(){ return this.power;}

	// Return linkedCard from card (as Character)    
        public Character getLinkedCard() {
            return linkedCard;
        }
	
	// Method to summon Character card
	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}

	// Method to activate card
	public void activate(){
		this.linkedCard.setEffect(this.attack, this.defense);
		this.isActive = true;
	}

	// Method to destroy card
	public void destroy(){
		if(this.isActive == true){
			this.linkedCard.setEffect(-1 * this.attack, -1 * this.defense);
		} 
	}

	// Method to convert card toString
	public String toString(){
		String result = super.toString();
		result += "\nAttack : " + this.attack +
					"\nDefense : " + this.defense +
					"\nPower : " + this.power;
		return result;
	}
    
	// Return Stats as String
    public String getStatsAsString() {
        String res = "";
        if (this.attack > 0) {
            res += "+";
        }
        res += this.attack + " ATK ";
        if (this.defense > 0) {
            res += "+";
        }
        res += this.defense + " DEF ";
        res += "| POW / " + this.power;

        return res;
    }
    
	// Return Attack as String
    public String getAtkAsString() {
        String res = "ATK ";
        if (this.attack > 0) {
            res += "+";
        }
        res += this.attack;
        return res;
    }
    
	// Return Defense as String
    public String getDefAsString() {
        String res = "DEF ";
        if (this.defense > 0) {
            res += "+";
        }
        res += this.defense;
        return res;
    }
    
	// Return Power as String
    public String getPowAsString() {
        return "POW " + this.power;
    }
}
