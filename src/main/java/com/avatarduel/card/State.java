package com.avatarduel.card;

public class State{
	private Position pos; //Position
	private boolean getPowerUp;

	public State(Position pos){
		this.pos = pos;
		this.getPowerUp = false;
	}

	public Position getPosition(){
		return this.pos;
	}

	public void rotate(){
		if(this.pos == Position.ATTACK){
			this.pos = Position.DEFENSE;
		} else {
			this.pos = Position.ATTACK;
		}
	}

	public void setPowerUp(boolean powerUp){
		this.getPowerUp = powerUp;
	}

	public boolean isPowerUp(){
		return this.getPowerUp;
	}

	public String toString(){
		String result = "";
		result += "\nPosition : " + (this.pos == Position.ATTACK? "attack" : "defense")  + "\n";
		return result;
	}
}