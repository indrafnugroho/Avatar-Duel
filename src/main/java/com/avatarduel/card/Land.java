package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.CardBuilder;

import com.avatarduel.player.Status;

public class Land extends Card{
	private Status linkedStatus;

    // Constructor for Land with builder(CardBuilder) as parameter

	public Land(CardBuilder builder){
		super(builder);
	}
    
    // Return Power of card (0)
        public int getPower() {
            return 0;
        }

    // Summon card with linkedStatus as parameter
	public void summon(Status linkedStatus){
		this.isSummoned = true;
		this.linkedStatus = linkedStatus;
	}

    // Method to activate card
	public void activate(){
		this.linkedStatus.addStatus(super.getElement());
	}    
    
    // Return Stats As String
    public String getStatsAsString() {
        return "";
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
        return "";
    }
}
