package com.avatarduel.card;

public class State implements Cloneable {
	private Position pos; //Position
	private boolean getPowerUp;

	// Constructor for State with pos(Position) as parameter
	public State(Position pos){
		this.pos = pos;
		this.getPowerUp = false;
	}

	// Return position
	public Position getPosition(){
		return this.pos;
	}

	// Method to Rotate card's position
	public void rotate(){
		if(this.pos == Position.ATTACK){
			this.pos = Position.DEFENSE;
		} else {
			this.pos = Position.ATTACK;
		}
	}

	// Method to SetPowerUp of card
	public void setPowerUp(boolean powerUp){
		this.getPowerUp = powerUp;
	}

	// Return card's getPowerUp status
	public boolean isPowerUp(){
		return this.getPowerUp;
	}

	// Convert toString
	public String toString(){
		String result = "";
		result += "\nPosition : " + (this.pos == Position.ATTACK? "attack" : "defense")  + "\n";
		return result;
	}

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
