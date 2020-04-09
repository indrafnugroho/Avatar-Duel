package com.avatarduel.card;
import com.avatarduel.card.Position;

public class State{
	private int x;
	private int y;
	private Position pos; //Position
	private boolean getPowerUp;

	public State(int x, int y, Position pos){
		this.x = x;
		this.y = y;
		this.pos = pos;
		this.getPowerUp = false;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
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
		result += "X : " + this.x + 
					"\nY : " + this.y +
					"\nPosition : " + (this.pos == Position.ATTACK? "attack" : "defense")  + "\n";
		return result;
	}
}