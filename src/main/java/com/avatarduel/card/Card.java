package com.avatarduel.card;

import com.avatarduel.card.Element;
import com.avatarduel.card.CardBuilder;

public class Card{
	protected int id;
	protected CardType type;
	protected String name;
	protected Element element;
	protected String description;
	protected String imagepath;
	protected boolean isSummoned;
	protected boolean isSummonable;
	protected State state;

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
        
        public void setState(String s) {
            if (s.equals("rotate")) {
                this.state.rotate();
            } else {
                
            }
        }
        
	public int getId(){
		return this.id;
	}

	public Element getElement(){
		return this.element;
	}

        public CardType getType() {
            return this.type;
        }

	public String getName(){
		return this.name;
	}

	public String getDescription(){
		return this.description;
	}

	public String getPath(){
		return this.imagepath;
	}

	public boolean getStatus(){
		return this.isSummoned;
	}

	public boolean getSummonable(){
		return this.isSummonable;
	}

	public String toString(){
		String result = "";
		result += "Id: " + this.id + 
		"\nName: " + this.name +
		"\nElement: " + this.element +
		"\nDescription: " + this.description;

		return result;
	}

    public State getState() {
        return this.state;
    }
}