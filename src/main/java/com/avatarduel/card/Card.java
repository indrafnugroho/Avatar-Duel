package com.avatarduel.card;

import com.avatarduel.card.Element;
import com.avatarduel.card.CardBuilder;

public abstract class Card implements Cloneable {
	protected int id;
	protected CardType type;
	protected String name;
	protected Element element;
	protected String description;
	protected String imagepath;
	protected boolean isSummoned;
	protected boolean isSummonable;
	protected State state;

	// Constructor for card with CardBuilder as parameter

	public Card(CardBuilder builder){
		this.id = builder.id;
		this.type = builder.type;
		this.name = builder.name;
		this.element = builder.element;
		this.description = builder.description;
		this.imagepath = builder.imagepath;
		this.isSummoned = false;
		this.isSummonable = false;
	}
    
	// Return ID from card
	public int getId(){
		return this.id;
	}

	// Return Element from card
	public Element getElement(){
		return this.element;
	}

	// Return type from card as CardType
    public CardType getType() {
            return this.type;
        }

	// Return card's name
	public String getName(){
		return this.name;
	}

	// Return card's Description
	public String getDescription(){
		return this.description;
	}

	// Return path for card
	public String getPath(){
		return this.imagepath;
	}

	// Return card's status (summoned or not summoned)
	public boolean getStatus(){
		return this.isSummoned;
	}

	// Return card's Summonable 
	public boolean getSummonable(){
		return this.isSummonable;
	}
    
	// Set Summonable status with parameter b
    public void setIsSummonable(boolean b) {
            isSummonable = b;
        }
    
	// Return summoned status of card
    public boolean getIsSummoned() {
            return isSummoned;
        }

	// Set Summoned status with parameter b
    public void setIsSummoned(boolean b) {
            isSummoned = b;
        }

	// Return state of card	    
    public State getState() { return state;}

	// Abstract method to getPower of the card   
    public abstract int getPower();

	// Method to convert card toString
	public String toString(){
		String result = "";
		result += "Id: " + this.id + 
		"\nName: " + this.name +
		"\nElement: " + this.element +
		"\nDescription: " + this.description;

		return result;
	}

	// Abstract method to getStatsAsString
    public abstract String getStatsAsString(); 


    // Abstract method to getAtkAsString
    public abstract String getAtkAsString();

	// Abstract method to getDefAsString
    public abstract String getDefAsString();

    // Abstract method to getPowAsString
    public abstract String getPowAsString();

    @Override
    public Object clone() throws CloneNotSupportedException {
        Card c = (Card) super.clone();
        if (this.state != null) {
            c.state = (State) this.state.clone();
        }
        return c;
    }

}
