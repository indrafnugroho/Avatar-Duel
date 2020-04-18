package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class Destroy extends Card{
	private Character linkedCard;
	private int power;

        
	public Destroy(CardBuilder builder){

		super(builder);
		this.power = builder.power;
	}

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.state = new State(Position.ATTACK);
		this.linkedCard = linkedCard;
	}
        
	public int getPower(){ return this.power;}

	public Character activate(){
		return this.linkedCard;
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
