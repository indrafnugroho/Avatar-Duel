package com.avatarduel.model;

import com.avatarduel.model.Element;
import com.avatarduel.model.CardBuilder;

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

	public int getId(){
		return this.id;
	}

	public Element getElement(){
		return this.element;
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
}