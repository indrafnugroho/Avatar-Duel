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

	// Constructor for Character with builder(CardBuilder) as parameter
	public Character(CardBuilder builder){
		super(builder);
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.power = builder.power;
        this.hasAttackedForThisTurn = false;
	}

	// Method to summon character with pos(Position) as parameter
	public void summon(Position pos){
		this.isSummoned = true;
		this.state = new State(pos);
        this.hasAttackedForThisTurn = true;
	}

	// Return attack value from card
	public int getAttack(){
		return this.attack;
	}

	// Return defense value from card
	public int getDefense(){
		return this.defense;
	}

	// Return power value from card
	public int getPower(){
		return this.power;
	}
	
	// Return hasAttackedForThisTurn from card
    public boolean getHasAttacked() {
        return this.hasAttackedForThisTurn;
    }

	// Set card hasAttack status
    public void setHasAttacked(boolean b) {
        this.hasAttackedForThisTurn = b;
    }

	// Return card's state
	public State getState(){
		return this.state;
	}

	// Set card's state with current as parameter
	public void setState(State current){
		this.state = current;
	}

	// Set card's effect with attack and defense as parameter
	public void setEffect(int attack, int defense){
		this.attack += attack;
		this.defense += defense;
	}
	
	// Remove card effect with attack and defense as parameter
    public void removeEffect(int attack, int defense){
		this.attack -= attack;
		this.defense -= defense;
	}

	// Convert character card toString
	public String toString(){
		String result = super.toString();
		result += "\nAttack : " + this.attack +
					"\nDefense : " + this.defense +
					"\nPower : " + this.power;
		return result;
	}
    
	// Method to get Stats as String
    public String getStatsAsString() {
        return "ATK / " + this.attack + 
            " | DEF / " + this.defense + 
            " | POW / " + this.power;
    }
    
	// Method to get Attack as String
    public String getAtkAsString() {
        return "ATK " + this.attack;
    }
    
	// Method to get Defense as String
    public String getDefAsString() {
        return "DEF " + this.defense;
    }
    
	// Method to get Power as String
    public String getPowAsString() {
        return "POW " + this.power;
    }

}
