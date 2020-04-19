package com.avatarduel.player;

import com.avatarduel.card.*;
import com.avatarduel.card.Character;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Player{
	private String name;
	private List<Card> deck;
	private List<Card> cardOnHand;
	private List<Character> characterOnTable;
	private List<Card> skillOnTable;
	private Status status;
	private int lifePoint;

	/**
     * Constructor for Player
     * @param name, user defined player name
     * @param landList, user defined land list of player
	 * @param characterList, user defined character list of player
	 * @param auraList, user defined aura list of player
     */
	public Player(String name, List<Land> landList, List<Character> characterList, List<Aura> auraList){
		this.name = name;
		this.deck = new ArrayList<Card>(50);
		this.cardOnHand = new ArrayList<Card>(10);
		this.characterOnTable = new ArrayList<Character>(6);
		this.skillOnTable = new ArrayList<Card>(6);
		this.status = new Status();
		this.lifePoint = 80;

		initializeDeck(landList, characterList, auraList);
		initializeCardOnHand();
	}

	/**
	 * Get status of player
	 * @return Status of player
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * Get name of player
	 * @return String of the player's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get deck of player
	 * @return a List of Card inside player's deck
	 */
	public List<Card> getDeck() {
		return this.deck;
	}

	/**
	 * Get life point of player
	 * @return value of player's life point
	 */
	public int getLifePoint() {
		return lifePoint;
	}

	/**
     * Set player life point
     * @param hp, user defined HP to be set as player life point
     */
	public void setLifePoint(int hp){ this.lifePoint = hp; }

	/**
	 * Get list of character on table of player
	 * @return a List of Character that Player has put on table
	 */
	public List<Character> getListOfCharacterOnTable(){
		return this.characterOnTable;
	}

	/**
	 * Get list of skill on table of player
	 * @return a List of Card consists of Skill Cards that player has put on table
	 */
	public List<Card> getListOfSkillOnTable() { return this.skillOnTable;}

	/**
     * Set list of Character on table of player
     */
	public void setListOfCharacterOnTable(List<Character> updated){
		this.characterOnTable = updated;
	}

	/**
	 * Get list of card on hand
	 * @return a List of Card that player has on hand
	 */
	public List<Card> getListOfCardOnHand() {
		return this.cardOnHand;
	}

	/**
     * Set list of Card on Hand of player
	 * @param updated, list of card that want to be the updated one
     */
	public void setListOfCardOnHand(List<Card> updated){
		this.cardOnHand = updated;
	}

	/**
     * Initialize player's deck
	 * @param landList, user-defined list of Land
	 * @param characterList, user-defined character list
	 * @param auraList, user-defined aura List
     */
	public void initializeDeck(List<Land> landList, List<Character> characterList, List<Aura> auraList){
		// INISIALISASI KARTU UNTUK DECK
		// KOMPOSISI KARTU 2 : 2 : 1 = LAND : CHARACTER : SKILL
		List<Element> elements = new ArrayList<Element>(5);
		elements.add(Element.FIRE);
		elements.add(Element.WATER);
		elements.add(Element.AIR);
		elements.add(Element.EARTH);
                elements.add(Element.ENERGY);

		Random rand = new Random();
		for (int i = 0; i < 20; i++){
			int a = rand.nextInt(landList.size());
			int b = rand.nextInt(characterList.size());
			Land landDeck = landList.get(a);
			com.avatarduel.card.Character charDeck = characterList.get(b);
            try {
			    this.deck.add((Card) landDeck.clone());
			    this.deck.add((Card) charDeck.clone());
            } catch(Exception e) {
                System.out.println("E"+ e);
            }
		}

		for (int i = 0; i < 7; i++){
			int a = rand.nextInt(4);

			Aura auraDeck = auraList.get(a);
            try {
			    this.deck.add((Card) auraDeck.clone());
            } catch(Exception e) {
                System.out.println(e);
            }
		}

		int rand1 = rand.nextInt(elements.size());
		int rand2 = rand.nextInt(elements.size());
		int rand3 = rand.nextInt(elements.size());
		Element rand_element1 = elements.get(rand1);
		Element rand_element2 = elements.get(rand2);
		Element rand_element3 = elements.get(rand3);

		Destroy d = new CardBuilder(CardType.DESTROY)
				.setElement(rand_element1)
				.setPower(rand.nextInt(10))
				.buildDestroy();
		PowerUp p1 = new CardBuilder(CardType.POWERUP)
				.setElement(rand_element2)
				.setPower(rand.nextInt(10))
				.setDescription("Use this card to weaken the opposite player! Even when the opposite player's character is on defense. ")
				.buildPowerUp();
		PowerUp p2 = new CardBuilder(CardType.POWERUP)
				.setElement(rand_element3)
				.setPower(rand.nextInt(10))
				.setDescription("Use this card to weaken the opposite player! Even when the opposite player's character is on defense. ")
				.buildPowerUp();
		this.deck.add(d);
		this.deck.add(p1);
		this.deck.add(p2);
	}

	/**
     * Initialize card on hand
     */
	public void initializeCardOnHand(){
		// INSIALISASI KARTU DI TANGAN, AMBIL 7 DARI DECK
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			int a = rand.nextInt(deck.size());
			Card fromDeck = this.deck.remove(a);
			this.cardOnHand.add(fromDeck);
		}
	}

	/**
     * Draw card from deck
     */
	public void drawCardFromDeck(){
            Random rand = new Random();
            if (cardOnHand.size() < 10) {
                int int_random = rand.nextInt(deck.size());
		Card fromDeck = deck.remove(int_random);
		cardOnHand.add(fromDeck);
            } else {
                int nRandom = rand.nextInt(cardOnHand.size());
                cardOnHand.remove(nRandom);
                nRandom = rand.nextInt(deck.size());
                Card fromDeck = deck.remove(nRandom);
		cardOnHand.add(fromDeck);
            }
	}

	/**
     * Initialize status 
     */
	public void initializeStatus(){
		status.reset();
	}

	/**
     * Initialize turn of player
     */
    public void initializeTurn() {
        for (Character c: this.characterOnTable) {
            if (c != null) {
                c.setHasAttacked(false);
            }
        }
    }

	/**
	 * Put character on table
	 * @param c, card that want to put
	 * @param pos, position of card c when it is summoned
	 * @return boolean, will be true if character is successfully put on table
	 */
	public boolean putCharacterOnTable(Character c, Position pos){
            boolean success = false;
            if (characterOnTable.size() < 6) {
                switch (c.getElement()) {
                case AIR:
                    success = this.status.useAir(c.getPower());
                    break;
                case WATER:
                    success = this.status.useWater(c.getPower());
                    break;
                case EARTH:
                    success = this.status.useEarth(c.getPower());
                    break;
                case FIRE:
                    success = this.status.useFire(c.getPower());
                    break;
                case ENERGY:
                    success = this.status.useEnergy(c.getPower());
                    break;
                }
		if (success) {
                    this.characterOnTable.add(c);
                    this.cardOnHand.remove(c);
                    c.summon(pos);
                }
            } return success;
	}

	/**
     * Change character position
	 * @param c, card that want position to be changed
     */
	public void changeCharacterPosition(Character c){
		State updated = c.getState();
		updated.rotate();
		c.setState(updated);
	}

	/**
     * Use land 
	 * @param l, land card that want to be used
     */
	public void useLand(Land l){
		this.cardOnHand.remove(l);
		this.status.addStatus(l.getElement());
	}

	/**
     * Put skill on table
	 * @param a, skill card that want to be used
	 * @param c, character card that want skill to be added
	 * @return success, to tell that skill is successfully put on table
     */
	public boolean putSkillOnTable(Card a, Character c){
            boolean success = false;
            if (skillOnTable.size() < 6) {
		switch (a.getType()) {
                case AURA:
                    Aura aura = (Aura) a;
                    switch (aura.getElement()) {
                        case AIR:
                            success = this.status.useAir(aura.getPower());
                            break;
                        case WATER:
                            success = this.status.useWater(aura.getPower());
                            break;
                        case EARTH:
                            success = this.status.useEarth(aura.getPower());
                            break;
                        case FIRE:
                            success = this.status.useFire(aura.getPower());
                            break;
                        case ENERGY:
                            success = this.status.useEnergy(aura.getPower());
                    }
                    if (success) aura.summon(c);
                    break;
                case POWERUP:
                    PowerUp powerUp = (PowerUp) a;
                    switch (powerUp.getElement()) {
                        case AIR:
                            success = this.status.useAir(powerUp.getPower());
                            break;
                        case WATER:
                            success = this.status.useWater(powerUp.getPower());
                            break;
                        case EARTH:
                            success = this.status.useEarth(powerUp.getPower());
                            break;
                        case FIRE:
                            success = this.status.useFire(powerUp.getPower());
                            break;
                        case ENERGY:
                            success = this.status.useEnergy(powerUp.getPower());
                            break;
                    }
                    if (success) powerUp.summon(c);
                    break;
                case DESTROY:
                    Destroy destroy = (Destroy) a;
                    switch (destroy.getElement()) {
                        case AIR:
                            success = this.status.useAir(destroy.getPower());
                            break;
                        case WATER:
                            success = this.status.useWater(destroy.getPower());
                            break;
                        case EARTH:
                            success = this.status.useEarth(destroy.getPower());
                            break;
                        case FIRE:
                            success = this.status.useFire(destroy.getPower());
                            break;
                        case ENERGY:
                            success = this.status.useEnergy(destroy.getPower());
                            break;
                    }
                    if (success) destroy.summon(c);
                    break;
                }
		if (success) {
                    if (a.getType() != CardType.DESTROY) this.skillOnTable.add(a);
                    cardOnHand.remove(a);
                }
            } return success;
	}

	/**
     * Use card skill
	 * @param a, card that want skill to be used
     */
	public void useSkill(Card a){
		if (a.getType() == CardType.POWERUP){
			PowerUp powerUp = (PowerUp) a;
			powerUp.activate();
		}
		else if (a.getType() == CardType.AURA){
			Aura aura = (Aura) a;
			aura.activate();
		}
	}

	/**
     * Use skill
	 * @param playerTwo, player that card skill want to be used
	 * @param a, skill card that want to be used
     */
	public void useSkill(Player playerTwo, Card a){
		boolean found = false;
		List<Character> characterList = playerTwo.getListOfCharacterOnTable();
		if (a.getType() == CardType.DESTROY){
			Destroy destroy = (Destroy) a;
			Character c = destroy.activate();
			//characterList.remove(c);
            //this.cardOnHand.remove(destroy);
            this.removeCharacterFromTable(playerTwo, c);
		}
	}

	/**
     * Remove skill from table
	 * @param p, player that card skill want to be removed
	 * @param a, skill card that want to be removed
     */
	public void removeSkillFromTable(Player p, Card a){
		if (a.getType() == CardType.POWERUP){
            ((PowerUp) a).destroy();
			p.getListOfSkillOnTable().remove((PowerUp) a);
		}
		else if (a.getType() == CardType.AURA){
			((Aura) a).destroy();
			p.getListOfSkillOnTable().remove((Aura) a);
		}
	}
    
	/**
     * Remove character from table
	 * @param p, player that character want to be removed from table
	 * @param c, character card that want to be removed
     */
    public void removeCharacterFromTable(Player p, Character c) {
        List<Card> skillToRemove = new ArrayList<>();
        for (Card a: p.getListOfSkillOnTable()){
            if (a.getType() == CardType.AURA) {
                Character linkedCharacter = ((Aura) a).getLinkedCard();
                if (linkedCharacter == c) skillToRemove.add(a);
            }
            else if (a.getType() == CardType.POWERUP) {
                Character linkedCharacter2 = ((PowerUp) a).getLinkedCard();
                if (linkedCharacter2 == c) skillToRemove.add(a);
            }
        }
        for (Card a: skillToRemove) {
            removeSkillFromTable(p, a);
        }
        p.getListOfCharacterOnTable().remove(c);
    }
    
	/**
     * Attack
	 * @param playerTwo, player that want to be attacked
	 * @param characterA, character used to attack
	 * @param characterB, character that wish to be the target
     */
	public void attack(Player playerTwo, Character characterA, Character characterB){
        characterA.setHasAttacked(true);
		List<Character> playerTwoListOfCharacterOnTableOnTable = playerTwo.getListOfCharacterOnTable();
		List<Card> playerTwoListOfSkillOnTable = playerTwo.getListOfSkillOnTable();

		if ((characterB.getState().getPosition() == Position.ATTACK) && (characterB.getAttack() < characterA.getAttack())){
			removeCharacterFromTable(playerTwo, characterB);
			playerTwo.setLifePoint(playerTwo.getLifePoint() - (characterA.getAttack() - characterB.getAttack()));
		}
		else if ((characterB.getState().getPosition() == Position.DEFENSE) && (characterB.getDefense() < characterA.getAttack())){
			removeCharacterFromTable(playerTwo, characterB);
            if (characterA.getState().isPowerUp()) {
			playerTwo.setLifePoint(playerTwo.getLifePoint() - (characterA.getAttack() - characterB.getDefense())); 
            }
		}	
	}

	/**
     * Attack when enemy doesnt have any character card left
	 * @param playerTwo, player that want to be attacked
	 * @param characterA, character used to attack
     */
	public void attack(Player playerTwo, Character characterA){
		// dipake apabila playerTwp character nya abis
        characterA.setHasAttacked(true);
		playerTwo.setLifePoint(playerTwo.getLifePoint() - characterA.getAttack());
	}
}
