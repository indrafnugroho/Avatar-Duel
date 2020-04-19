package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class PowerUp extends Card {
	private Character linkedCard;
	private int power;

    // Constructor for PowerUp with builder(CardBuilder) as parameter
	public PowerUp(CardBuilder builder){

		super(builder);
		this.power = builder.power;
	}

    // Summon card with linkedCard as parameter

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}
    
	// Return linkedCard of this card
        public Character getLinkedCard() {
            return linkedCard;
        }

	// Method to Activate card
	public void activate(){
		this.linkedCard.state.setPowerUp(true);
	}

	// Method to Destroy card
	public void destroy(){
		this.linkedCard.state.setPowerUp(false);
	}

	// Return power of card	    
	public int getPower() {
		return power;
	}

	// Return Stats As String
	public String getStatsAsString() {
        return "POW / " + power;
    }

    // Return Attack As String
    public String getAtkAsString() {
        return "";
    }

    // Return Defense As String
    public String getDefAsString() {
        return "";
    }

    // Return Power As String
    public String getPowAsString() {
        return "POW " + power;
    }
}
