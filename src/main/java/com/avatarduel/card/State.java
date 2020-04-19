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
     * @return position of the card in this state, either Position.ATTACK or Position.DEFENSE
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
     * @return true if the card in this state is powered up 
     */
	public boolean isPowerUp(){
		return this.getPowerUp;
	}

	/**
     * Convert toString
     * @return String representation of this card's state.
     */
	public String toString(){
		String result = "";
		result += "\nPosition : " + (this.pos == Position.ATTACK? "attack" : "defense")  + "\n";
		return result;
	}

    /**
     * Clone this object
     * @return the clone of this object
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
