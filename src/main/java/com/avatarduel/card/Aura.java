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
	
	/**
     * Construtor for Aura
	 * @param builder, get card attribute from builder
     */
	public Aura(CardBuilder builder){
		super(builder);
		this.power = builder.power;
		this.attack = builder.attack;
		this.defense = builder.defense;
	}

	/**
     * Get attack value
     * @return
     */
    public int getAttack(){
		return this.attack;
	}

	/**
     * Get defense value
     * @return 
     */
	public int getDefense(){
		return this.defense;
	}
    
	/**
     * Get power value
     * @return 
     */
	public int getPower(){ return this.power;}

	/**
     * Get linked card 
     * @return 
     */
        public Character getLinkedCard() {
            return linkedCard;
        }
	
	/**
     * Summon aura card
	 * @param linkedCard , card that linked to aura card
     */
	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}

	/**
     * Activate card
     */
	public void activate(){
		this.linkedCard.setEffect(this.attack, this.defense);
		this.isActive = true;
	}

	/**
     * Destroy card
     */
	public void destroy(){
		if(this.isActive == true){
			this.linkedCard.setEffect(-1 * this.attack, -1 * this.defense);
		} 
	}

	/**
     * Convert aura card to string
     * @return 
     */
	public String toString(){
		String result = super.toString();
		result += "\nAttack : " + this.attack +
					"\nDefense : " + this.defense +
					"\nPower : " + this.power;
		return result;
	}
    
	/**
     * Get status as string
     * @return 
     */
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
    
	/**
     * Get attack as string
     * @return 
     */
    public String getAtkAsString() {
        String res = "ATK ";
        if (this.attack > 0) {
            res += "+";
        }
        res += this.attack;
        return res;
    }
    
	/**
     * Get defense as string
     * @return 
     */
    public String getDefAsString() {
        String res = "DEF ";
        if (this.defense > 0) {
            res += "+";
        }
        res += this.defense;
        return res;
    }
    
	/**
     * Get power as string
     * @return 
     */
    public String getPowAsString() {
        return "POW " + this.power;
    }
}
