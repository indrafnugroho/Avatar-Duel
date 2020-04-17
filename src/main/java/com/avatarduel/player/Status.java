package com.avatarduel.player;

import com.avatarduel.card.Element;

public class Status{
	private int earth;
	private int air;
	private int fire;
	private int water;
        private int energy;

	private int maxEarth;
	private int maxWater;
	private int maxFire;
	private int maxAir;
        private int maxEnergy;

	public Status(){
		this.earth = 0;
		this.maxEarth = 0;
		this.air = 0;
		this.maxAir = 0;
		this.water = 0;
		this.maxWater = 0;
		this.fire = 0;
		this.maxFire = 0;
                this.energy = 0;
                this.maxEnergy = 0;
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
		} else if(element == Element.ENERGY) {
                    this.maxEnergy++;
                    this.energy++;
                }
	}

	public boolean useAir(int power){
		if(this.air - power >= 0){
			this.air -= power;
                        return true;
		} else return false;
	}

	public boolean useFire(int power){
		if(this.fire - power >= 0){
			this.fire -= power;
                        return true;
		} else return false;
	}

	public boolean useEarth(int power){
		if(this.earth - power >= 0){
			this.earth -= power;
                        return true;
		} else return false;
	}

	public boolean useWater(int power){
            if(this.water - power >= 0){
		this.water -= power;
                return true;
            } else return false;
	}
        
        public boolean useEnergy(int power){
            if (this.energy - power >= 0){
		this.energy -= power;
                return true;
            } else return false;
	}

	public void reset(){
            this.water = this.maxWater;
            this.fire = this.maxFire;
            this.earth = this.maxEarth;
            this.air = this.maxAir;
            this.energy = this.maxEnergy;
	}

	public String waterToString(){
            String result = "";
            result += this.water + " / " + this.maxWater;
            return result;
	}
        
        public String earthToString() {
            String result = "";
            result += this.earth + " / " + this.maxEarth;
            return result;
        }
        
        public String airToString() {
            String result = "";
            result += this.air + " / " + this.maxAir;
            return result;
        }
        
        public String fireToString() {
            String result = "";
            result += this.fire + " / " + this.maxFire;
            return result;
        }
        
        public String energyToString() {
            String result = "";
            result += this.energy + " / " + this.maxEnergy;
            return result;
        }
}