package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.CardBuilder;

import com.avatarduel.player.Status;

public class Land extends Card{
	private Status linkedStatus;

    /**
     * Constructor for Land card
	 * @param builder, get card attribute from builder
     */
	public Land(CardBuilder builder){
		super(builder);
	}
    
    /**
     * Get Power of land card
     */
        public int getPower() {
            return 0;
        }

    /**
     * Summon land card
	 * @param linkedStatus, link card status with linkedStatus
     */
	public void summon(Status linkedStatus){
		this.isSummoned = true;
		this.linkedStatus = linkedStatus;
	}

    /**
     * Activate land card
     */
	public void activate(){
		this.linkedStatus.addStatus(super.getElement());
	}    
    
    /**
     * Get Status as String
     */
    public String getStatsAsString() {
        return "";
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
        return "";
    }
}
