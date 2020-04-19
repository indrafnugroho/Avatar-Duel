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

	public Character(CardBuilder builder){
		super(builder);
		this.attack = builder.attack;
		this.defense = builder.defense;
		this.power = builder.power;
        this.hasAttackedForThisTurn = false;
	}

	public void summon(Position pos){
		this.isSummoned = true;
		this.state = new State(pos);
        this.hasAttackedForThisTurn = true;
	}

	public int getAttack(){
		return this.attack;
	}

	public int getDefense(){
		return this.defense;
	}

	public int getPower(){
		return this.power;
	}

    public boolean getHasAttacked() {
        return this.hasAttackedForThisTurn;
    }

    public void setHasAttacked(boolean b) {
        this.hasAttackedForThisTurn = b;
    }

	public State getState(){
		return this.state;
	}

	public void setState(State current){
		this.state = current;
	}

	public void setEffect(int attack, int defense){
		this.attack += attack;
		this.defense += defense;
	}

	public String toString(){
		String result = super.toString();
		result += "\nAttack : " + this.attack +
					"\nDefense : " + this.defense +
					"\nPower : " + this.power;
		return result;
	}
    
    public String getStatsAsString() {
        return "ATK / " + this.attack + 
            " | DEF / " + this.defense + 
            " | POW / " + this.power;
    }
    
    public String getAtkAsString() {
        return "ATK " + this.attack;
    }
    
    public String getDefAsString() {
        return "DEF " + this.defense;
    }
    
    public String getPowAsString() {
        return "POW " + this.power;
    }

}
