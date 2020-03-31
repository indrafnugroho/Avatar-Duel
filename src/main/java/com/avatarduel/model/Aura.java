package com.avatarduel.model;

class Aura extends Card{
	private int power;
	private int attack;
	private int defense;

	public Aura(CardBuilder builder){
		super(builder);
		this.power = builder.power;
		this.attack = builder.attack;
		this.defense = builder.defense;
	}
}