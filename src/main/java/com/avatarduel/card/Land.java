package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.CardBuilder;

import com.avatarduel.player.Status;

public class Land extends Card{
	private Status linkedStatus;

	public Land(CardBuilder builder){
		super(builder);
	}

	public void summon(Status linkedStatus){
		this.isSummoned = true;
		this.linkedStatus = linkedStatus;
	}

	public void activate(){
		this.linkedStatus.addStatus(super.getElement());
	}

    public String getStatsAsString() {
        return "";
    }
    
    public String getAtkAsString() {
        return "";
    }
    
    public String getDefAsString() {
        return "";
    }
    
    public String getPowAsString() {
        return "";
    }
}
