package com.avatarduel.model;

import com.avatarduel.model.Element;

class Status{
	private int earth;
	private int air;
	private int fire;
	private int water;

	private int maxEarth;
	private int maxWater;
	private int maxFire;
	private int maxAir;

	public Status(){
		this.earth = 0;
		this.maxEarth = 0;
		this.air = 0;
		this.maxAir = 0;
		this.water = 0;
		this.maxWater = 0;
		this.fire = 0;
		this.maxFire = 0;
	}

	public void addStatus(Element element){
		if(element == Element.FIRE){
			this.maxFire++;
			this.fire++;
		} else if(element == Element.WATER){
			this.maxWater++;
			this.water++;
		} else if(element == Element.EARTH){
			this.maxEarth++;
			this.earth++;
		} else if(element == Element.AIR){
			this.maxAir++;
			this.air++;
		}
	}

	public void useAir(int power){
		if(this.air - power >= 0){
			this.air -= power;
		}
	}

	public void useFire(int power){
		if(this.fire - power >= 0){
			this.fire -= power;
		}
	}

	public void useEarth(int power){
		if(this.earth - power >= 0){
			this.earth =- power;
		}
	}

	public void useWater(int power){
		if(this.water - power >= 0){
			if(this.water - power >= 0){
				this.water -= power;
			}
		}
	}

	public void reset(){
		this.water = this.maxWater;
		this.fire = this.maxFire;
		this.earth = this.maxEarth;
		this.air = this.maxAir;
	}

	public String toString(){
		String result = "";
		result += "Water : " + this.water + "/" + this.maxWater + 
		"\nEarth : " + this.earth + "/" + this.maxEarth + 
		"\nFire : " + this.fire + "/" + this.maxFire + 
		"\nAir : " + this.air + "/" + this.maxAir;
		return result;
	}
}