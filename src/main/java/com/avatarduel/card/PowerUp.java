package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class PowerUp extends Card {
	private Character linkedCard;
	private int power;

    /**
     * Constructor for PowerUp card
	 * @param builder, get card attribute from builder
     */
	public PowerUp(CardBuilder builder){

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
     * Get Linked Card 
     */
        public Character getLinkedCard() {
            return linkedCard;
        }

	/**
     * Activate powerUp Card
     */
	public void activate(){
		this.linkedCard.state.setPowerUp(true);
	}

	/**
     * Destroy card
     */
	public void destroy(){
		this.linkedCard.state.setPowerUp(false);
	}

	/**
     * Get Power of card
     */
	public int getPower() {
		return power;
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
