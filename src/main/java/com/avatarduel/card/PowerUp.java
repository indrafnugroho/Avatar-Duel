package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class PowerUp extends Card {
	private Character linkedCard;
	private int power;

	public PowerUp(CardBuilder builder){

		super(builder);
		this.power = builder.power;
	}

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}
        
        public Character getLinkedCard() {
            return linkedCard;
        }

	public void activate(){
		this.linkedCard.state.setPowerUp(true);
	}

	public void destroy(){
		this.linkedCard.state.setPowerUp(false);
	}
                
	public int getPower() {
		return power;
	}

	public String getStatsAsString() {
        return "POW / " + power;
    }
    
    public String getAtkAsString() {
        return "";
    }
    
    public String getDefAsString() {
        return "";
    }
    
    public String getPowAsString() {
        return "POW " + power;
    }
}
