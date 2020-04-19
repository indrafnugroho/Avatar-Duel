package com.avatarduel.card;

public class State implements Cloneable {
	private Position pos; //Position
	private boolean getPowerUp;

	/**
     * Constructor for State 
	 * @param pos, position of state
     */
	public State(Position pos){
		this.pos = pos;
		this.getPowerUp = false;
	}

	/**
     * Get Position
     */
	public Position getPosition(){
		return this.pos;
	}

	/**
     * Rotate position
     */
	public void rotate(){
		if(this.pos == Position.ATTACK){
			this.pos = Position.DEFENSE;
		} else {
			this.pos = Position.ATTACK;
		}
	}

	/**
     * Set Power Up
	 * @param powerUp, set powerUp as the new attribute of state
     */
	public void setPowerUp(boolean powerUp){
		this.getPowerUp = powerUp;
	}

	/**
     * Get isPowerUp
     */
	public boolean isPowerUp(){
		return this.getPowerUp;
	}

	/**
     * Convert toString
     */
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
