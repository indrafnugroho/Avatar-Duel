package com.avatarduel.model;

public enum CardType{
	CHARACTER,
	LAND,
	SKILL {
		AURA,
		DESTROY,
		POWERUP
	}
}