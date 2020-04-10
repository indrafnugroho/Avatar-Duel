package com.avatarduel.card;

import com.avatarduel.card.Card;
import com.avatarduel.card.Character;
import com.avatarduel.card.CardBuilder;
import com.avatarduel.card.State;

public class PowerUp extends Card {
	private Character linkedCard;

	public PowerUp(CardBuilder builder){
		super(builder);
	}

	public void summon(int x, Character linkedCard){
		this.isSummoned = true;
		this.state = new State(x,2, Position.ATTACK);
		this.linkedCard = linkedCard;
	}

	public void activate(){
		this.linkedCard.state.setPowerUp(true);
	}

	public State destroy(){
		this.linkedCard.state.setPowerUp(false);
		return this.state;
	}
}