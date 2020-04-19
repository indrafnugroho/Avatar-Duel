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

	/**
     * Constructor for Card
	 * @param builder, get attribute from builder
     */

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
    
	/**
     * Get Id from card
     */
	public int getId(){
		return this.id;
	}

	/**
     * Get Element from card
     */
	public Element getElement(){
		return this.element;
	}

	/**
     * Get type from card
     */
    public CardType getType() {
            return this.type;
        }

	/**
     * Get Name from card
     */
	public String getName(){
		return this.name;
	}

	/**
     * Get Description from card
     */
	public String getDescription(){
		return this.description;
	}

	/**
     * Get path from card
     */
	public String getPath(){
		return this.imagepath;
	}

	/**
     * Get status from card
     */
	public boolean getStatus(){
		return this.isSummoned;
	}

	/**
     * Get summonable status from card
     */
	public boolean getSummonable(){
		return this.isSummonable;
	}
    
	/**
     * Set summonable status for card
	 * @param b, b as the new isSummonable status
     */
    public void setIsSummonable(boolean b) {
            isSummonable = b;
        }
    
	/**
     * Get is summoned status from card
     */
    public boolean getIsSummoned() {
            return isSummoned;
        }

	/**
     * Set Summoned status for card
	 * @param b, b as the new is summoned status
     */
    public void setIsSummoned(boolean b) {
            isSummoned = b;
        }

	/**
     * Get state from card
     */   
    public State getState() { return state;}

	/**
     * Abstract method to get power from card
     */
    public abstract int getPower();

	/**
     * Convert to string
     */
	public String toString(){
		String result = "";
		result += "Id: " + this.id + 
		"\nName: " + this.name +
		"\nElement: " + this.element +
		"\nDescription: " + this.description;

		return result;
	}

	/**
     * Abstract method to get status as string
     */
    public abstract String getStatsAsString(); 


    /**
     * Abstract method to get Attack as string
     */
    public abstract String getAtkAsString();

	/**
     * Abstract method to get Defense as string
     */
    public abstract String getDefAsString();

    /**
     * Abstract method to get Power as string
     */
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
