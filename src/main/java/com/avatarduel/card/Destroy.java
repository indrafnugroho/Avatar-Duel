package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class Destroy extends Card{
	private Character linkedCard;
	private int power;

	// Constructor for Destroy with builder(CardBuilder) as parameter
	public Destroy(CardBuilder builder){

		super(builder);
		this.power = builder.power;
	}

	// Method to summon card
	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}
    
	// Return power of card
	public int getPower(){ return this.power;}

	// Return linkedCard
	public Character activate(){
		return this.linkedCard;
	}
    
	// Return Stats as String
    public String getStatsAsString() {
        return "POW / " + power;
    }
    
	// Return Attack as String
    public String getAtkAsString() {
        return "";
    }
    
	// Return Defense as String
    public String getDefAsString() {
        return "";
    }
    
	// Return Power as String
    public String getPowAsString() {
        return "POW " + power;
    }
}
