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

	public void summon(int x, Character linkedCard){
		this.isSummoned = true;
		this.state = new State(x,2, Position.ATTACK);
		this.linkedCard = linkedCard;
	}

	public int getPower(){ return this.power;}

	public State activate(){
		return this.linkedCard.destroy();
	}
    
    public String getStatsAsString() {
        return "";
    }
}
