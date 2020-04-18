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

	public Aura(CardBuilder builder){
		super(builder);
		this.power = builder.power;
		this.attack = builder.attack;
		this.defense = builder.defense;
	}
        
        public int getAttack(){
		return this.attack;
	}

	public int getDefense(){
		return this.defense;
	}
        
	public int getPower(){ return this.power;}
        
        public Character getLinkedCard() {
            return linkedCard;
        }

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}

	public void activate(){
		this.linkedCard.setEffect(this.attack, this.defense);
		this.isActive = true;
	}

	public void destroy(){
		if(this.isActive == true){
			this.linkedCard.setEffect(-1 * this.attack, -1 * this.defense);
		} 
	}

	public String toString(){
		String result = super.toString();
		result += "\nAttack : " + this.attack +
					"\nDefense : " + this.defense +
					"\nPower : " + this.power;
		return result;
	}
    
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
    
    public String getAtkAsString() {
        String res = "ATK ";
        if (this.attack > 0) {
            res += "+";
        }
        res += this.attack;
        return res;
    }
    
    public String getDefAsString() {
        String res = "DEF ";
        if (this.defense > 0) {
            res += "+";
        }
        res += this.defense;
        return res;
    }
    
    public String getPowAsString() {
        return "POW " + this.power;
    }
}
