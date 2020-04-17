package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class Destroy extends Card{
	private Character linkedCard;


	public Destroy(CardBuilder builder){
		super(builder);
	}

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.linkedCard = linkedCard;
	}

	public State activate(){
		return this.linkedCard.destroy();
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
