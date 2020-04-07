package com.avatarduel.model;

import com.avatarduel.model.Card;
import com.avatarduel.model.Character;
import com.avatarduel.model.CardBuilder;
import com.avatarduel.model.State;

class PowerUp extends Card {
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