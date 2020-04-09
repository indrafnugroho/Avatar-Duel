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

	public void summon(Character linkedCard){
		this.isSummoned = true;
		this.linkedCard = linkedCard;
	}

	public void activate(){
		// return 
		this.linkedCard.state.setPowerUp(true);
	}

	public void destroy(){
		// return 
		this.linkedCard.state.setPowerUp(false);
	}
}