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

	/**
     * Constructor for Status
     */
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

	/**
     * Add status
	 * @param element, add status to element card on the field
     */
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

	/**
	 * Use Air power
	 * @param power, how many power that need to be used
	 * @return boolean, to tell if the power is successfully used
	 */
	public boolean useAir(int power){
		if(this.air - power >= 0){
			this.air -= power;
                        return true;
		} else return false;
	}

	/**
     * Use Fire power
	 * @param power , how many power that need to be used
	 * @return boolean, to tell if the power is successfully used
     */
	public boolean useFire(int power){
		if(this.fire - power >= 0){
			this.fire -= power;
                        return true;
		} else return false;
	}

	/**
     * Use Earth power
	 * @param power , how many power that need to be used
	 * @return boolean, to tell if the power is successfully used
     */
	public boolean useEarth(int power){
		if(this.earth - power >= 0){
			this.earth -= power;
                        return true;
		} else return false;
	}

	/**
     * Use Water power
	 * @param power , how many power that need to be used
	 * @return boolean, to tell if the power is successfully used
     */
	public boolean useWater(int power){
            if(this.water - power >= 0){
		this.water -= power;
                return true;
            } else return false;
	}
    
	/**
     * Use Energy power
	 * @param power , how many power that need to be used
	 * @return boolean, to tell if the power is successfully used
     */
        public boolean useEnergy(int power){
            if (this.energy - power >= 0){
		this.energy -= power;
                return true;
            } else return false;
	}

	/**
     * Reset all status
     */
	public void reset(){
            this.water = this.maxWater;
            this.fire = this.maxFire;
            this.earth = this.maxEarth;
            this.air = this.maxAir;
            this.energy = this.maxEnergy;
	}

	/**
	 * Convert water to string
	 * @return a string of description how much the water is now and maximum amount of water
	 */
	public String waterToString(){
            String result = "";
            result += this.water + " / " + this.maxWater;
            return result;
	}
	
	/**
     * Convert earth to string
	 * @return a string of description how much the earth is now and maximum amount of earth
     */
        public String earthToString() {
            String result = "";
            result += this.earth + " / " + this.maxEarth;
            return result;
        }

    /**
     * Convert air to string
	 * @return a string of description how much the air is now and maximum amount of air
     */
        public String airToString() {
            String result = "";
            result += this.air + " / " + this.maxAir;
            return result;
        }

	/**
	 * Convert fire to string
	 * @return a string of description how much the water is now and maximum amount of water
	 */
		public String fireToString() {
            String result = "";
            result += this.fire + " / " + this.maxFire;
            return result;
        }

	/**
	 * Convert energy to string
	 * @return a string of description how much the water is now and maximum amount of water
	 */
	public String energyToString() {
            String result = "";
            result += this.energy + " / " + this.maxEnergy;
            return result;
        }
}