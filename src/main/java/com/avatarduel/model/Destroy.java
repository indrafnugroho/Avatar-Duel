package com.avatarduel.model;

import com.avatarduel.model.Card;
import com.avatarduel.model.Character;
import com.avatarduel.model.CardBuilder;
import com.avatarduel.model.State;

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
}