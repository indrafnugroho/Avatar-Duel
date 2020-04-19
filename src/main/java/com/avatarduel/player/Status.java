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

	// Constructor for Status
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

	// Method to add Status with element as parameter
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

	// Method to return use Air status with power as parameter
	public boolean useAir(int power){
		if(this.air - power >= 0){
			this.air -= power;
                        return true;
		} else return false;
	}

	// Method to return use Fire status with power as parameter
	public boolean useFire(int power){
		if(this.fire - power >= 0){
			this.fire -= power;
                        return true;
		} else return false;
	}

	// Method to return use Earth status with power as Parameter
	public boolean useEarth(int power){
		if(this.earth - power >= 0){
			this.earth -= power;
                        return true;
		} else return false;
	}

	// Method to return use Water status with power as parameter
	public boolean useWater(int power){
            if(this.water - power >= 0){
		this.water -= power;
                return true;
            } else return false;
	}
    
	// Method to return use Energy status with power as parameter
        public boolean useEnergy(int power){
            if (this.energy - power >= 0){
		this.energy -= power;
                return true;
            } else return false;
	}

	// Method to reset status
	public void reset(){
            this.water = this.maxWater;
            this.fire = this.maxFire;
            this.earth = this.maxEarth;
            this.air = this.maxAir;
            this.energy = this.maxEnergy;
	}

	// Method to convert water toString
	public String waterToString(){
            String result = "";
            result += this.water + " / " + this.maxWater;
            return result;
	}
	
	// Method to convert earth toString
        public String earthToString() {
            String result = "";
            result += this.earth + " / " + this.maxEarth;
            return result;
        }

        // Method to convert air toString
        public String airToString() {
            String result = "";
            result += this.air + " / " + this.maxAir;
            return result;
        }

       	// Method to convert fire toString
        public String fireToString() {
            String result = "";
            result += this.fire + " / " + this.maxFire;
            return result;
        }

	  	// Method to convert energy toString
        public String energyToString() {
            String result = "";
            result += this.energy + " / " + this.maxEnergy;
            return result;
        }
}