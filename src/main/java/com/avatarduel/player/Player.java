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

	// Constructor for Player 	
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

	// Return status of player
	public Status getStatus() {
		return this.status;
	}

	// Return name of player
	public String getName() {
		return this.name;
	}

	// Return player's deck
	public List<Card> getDeck() {
		return this.deck;
	}

	// Return player's LifePoint
	public int getLifePoint() {
		return lifePoint;
	}

	// Set Player's HP
	public void setLifePoint(int hp){ this.lifePoint = hp; }

	// Return Player's character on table
	public List<Character> getListOfCharacterOnTable(){
		return this.characterOnTable;
	}

	// Return Player's skill on Table
	public List<Card> getListOfSkillOnTable() { return this.skillOnTable;}

	// Set List of Character On Table with updated as parameter
	public void setListOfCharacterOnTable(List<Character> updated){
		this.characterOnTable = updated;
	}

	// Return cardOnHand of player
	public List<Card> getListOfCardOnHand() {
		return this.cardOnHand;
	}

	// Set List of Card on Hand with updated as parameter
	public void setListOfCardOnHand(List<Card> updated){
		this.cardOnHand = updated;
	}

	// Initialize Player's deck
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
				.buildPowerUp();
		PowerUp p2 = new CardBuilder(CardType.POWERUP)
				.setElement(rand_element3)
				.setPower(rand.nextInt(10))
				.buildPowerUp();
		this.deck.add(d);
		this.deck.add(p1);
		this.deck.add(p2);
	}

	// Initialize Player's Card On Hand
	public void initializeCardOnHand(){
		// INSIALISASI KARTU DI TANGAN, AMBIL 7 DARI DECK
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			int a = rand.nextInt(deck.size());
			Card fromDeck = this.deck.remove(a);
			this.cardOnHand.add(fromDeck);
		}
	}

	// Method to Draw Card fron Deck
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

	// Method to initialize Status
	public void initializeStatus(){
		status.reset();
	}

	// Method to initialize Turn
    public void initializeTurn() {
        for (Character c: this.characterOnTable) {
            if (c != null) {
                c.setHasAttacked(false);
            }
        }
    }

	// Method to Put Character on Table with character c and position pos as parameter
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

	// Method to Change character position with character c as parameter
	public void changeCharacterPosition(Character c){
		State updated = c.getState();
		updated.rotate();
		c.setState(updated);
	}

	// Method to use Land with land l as parameter
	public void useLand(Land l){
		this.cardOnHand.remove(l);
		this.status.addStatus(l.getElement());
	}

	// Method to putSkillOnTable
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

	// Method to useSkill with one parameter
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

	// Method to UseSkill with two parameter
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

	// Method to remove skill from table
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
    
	// Method to remove character from table
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
    
	// Method to attack enemy with playertwo as target,characterA as player character and characterB as enemy's character
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

	// Method to attack playerTwo as enemy when enemy doesn't have any card left on the field
	public void attack(Player playerTwo, Character characterA){
		// dipake apabila playerTwp character nya abis
        characterA.setHasAttacked(true);
		playerTwo.setLifePoint(playerTwo.getLifePoint() - characterA.getAttack());
	}
}
