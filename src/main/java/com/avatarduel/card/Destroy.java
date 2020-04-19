package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class Destroy extends Card{
	private Character linkedCard;
	private int power;

	/**
     * Constructor for Destroy card
	 * @param builder, get card attribute from builder
     */
	public Destroy(CardBuilder builder){

		super(builder);
		this.power = builder.power;
	}

	/**
     * Summon destroy card
	 * @param linkedCard, link card with linkedCard
     */
	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}
    
	/**
     * Get Power from card
     */
	public int getPower(){ return this.power;}

	/**
     * Activate card
     */
	public Character activate(){
		return this.linkedCard;
	}
    
	/**
     * Get Status as String
     */
    public String getStatsAsString() {
        return "POW / " + power;
    }
    
	/**
     * Get Attack as String
     */
    public String getAtkAsString() {
        return "";
    }
    
	/**
     * Get Defense as String
     */
    public String getDefAsString() {
        return "";
    }
    
	/**
     * Get Power as String
     */
    public String getPowAsString() {
        return "POW " + power;
    }
}
