package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.Position;
import com.avatarduel.card.State;

public class Character extends Card{
	private int attack;
	private int defense;
	private int power;
    private boolean hasAttackedForThisTurn;

	/**
     * Constructor for character card
	 * @param builder, get card attribute from builder
     */
	public Character(CardBuilder builder){
		super(builder);
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.power = builder.power;
        this.hasAttackedForThisTurn = false;
	}

	/**
     * Summon card 
	 * @param pos, position of card when summoned either ATTACK or DEFENSE
     */
	public void summon(Position pos){
		this.isSummoned = true;
		this.state = new State(pos);
        this.hasAttackedForThisTurn = true;
	}

	/**
     * Get attack value from card
     */
	public int getAttack(){
		return this.attack;
	}

	/**
     * Get defense value from card
     */
	public int getDefense(){
		return this.defense;
	}

	/**
     * Get power value from card
     */
	public int getPower(){
		return this.power;
	}
	
	/**
     * Get hasAttack value from card
     */
    public boolean getHasAttacked() {
        return this.hasAttackedForThisTurn;
    }

	/**
     * Set hasAttack for card
	 * @param b, status that want to be set
     */
    public void setHasAttacked(boolean b) {
        this.hasAttackedForThisTurn = b;
    }

	/**
     * Get state
     */
	public State getState(){
		return this.state;
	}

	/**
     * Set state of card
	 * @param current, state that want to be set 
     */
	public void setState(State current){
		this.state = current;
	}

	/**
     * Set effect of card
	 * @param attack, attack that want to be added
	 * @param defense, defense that want to be added
     */
	public void setEffect(int attack, int defense){
		this.attack += attack;
		this.defense += defense;
	}
	
	/**
     * Remove effect of card
	 * @param attack, attack that want to be reduced
	 * @param defense, defense that want to be reduced
     */
    public void removeEffect(int attack, int defense){
		this.attack -= attack;
		this.defense -= defense;
	}

	/**
     * Convert to String
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
     */
    public String getStatsAsString() {
        return "ATK / " + this.attack + 
            " | DEF / " + this.defense + 
            " | POW / " + this.power;
    }
    
	/**
     * Get attack as string
     */
    public String getAtkAsString() {
        return "ATK " + this.attack;
    }
    
	/**
     * Get defense as string
     */
    public String getDefAsString() {
        return "DEF " + this.defense;
    }
    
	/**
     * Get power as string
     */
    public String getPowAsString() {
        return "POW " + this.power;
    }

}
