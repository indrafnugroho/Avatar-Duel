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

	public Status getStatus() {
		return this.status;
	}

	public String getName() {
		return this.name;
	}

	public List<Card> getDeck() {
		return this.deck;
	}

	public int getLifePoint() {
		return lifePoint;
	}
	public void setLifePoint(int hp){ this.lifePoint = hp; }

	public List<Character> getListOfCharacterOnTable(){
		return this.characterOnTable;
	}

	public List<Card> getListOfSkillOnTable() { return this.skillOnTable;}

	public void setListOfCharacterOnTable(List<Character> updated){
		this.characterOnTable = updated;
	}

	public List<Card> getListOfCardOnHand() {
		return this.cardOnHand;
	}

	public void setListOfCardOnHand(List<Card> updated){
		this.cardOnHand = updated;
	}

	public void initializeDeck(List<Land> landList, List<Character> characterList, List<Aura> auraList){
		// INISIALISASI KARTU UNTUK DECK
		// KOMPOSISI KARTU 2 : 2 : 1 = LAND : CHARACTER : SKILL
		List<Element> elements = new ArrayList<Element>(4);
		elements.add(Element.FIRE);
		elements.add(Element.WATER);
		elements.add(Element.AIR);
		elements.add(Element.EARTH);

		Random rand = new Random();
		for (int i = 0; i < 20; i++){
			int a = rand.nextInt(landList.size());
			int b = rand.nextInt(characterList.size());
			Land land_deck = landList.get(a);
			com.avatarduel.card.Character char_deck = characterList.get(b);
			this.deck.add(land_deck);
			this.deck.add(char_deck);
		}

		for (int i = 0; i < 7; i++){
			int a = rand.nextInt(4);

			Aura aura_deck = auraList.get(a);
			this.deck.add(aura_deck);
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

	public void initializeCardOnHand(){
		// INSIALISASI KARTU DI TANGAN, AMBIL 7 DARI DECK
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			int a = rand.nextInt(deck.size());
			Card fromDeck = this.deck.remove(a);
			this.cardOnHand.add(fromDeck);
		}
	}

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

	public void initializeStatus(){
		status.reset();
	}

    public void initializeTurn() {
        for (Character c: this.characterOnTable) {
            if (c != null) {
                c.setHasAttacked(false);
            }
        }
    }

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

	public void changeCharacterPosition(Character c){
		State updated = c.getState();
		updated.rotate();
		c.setState(updated);
	}

	public void useLand(Land l){
		this.cardOnHand.remove(l);
		this.status.addStatus(l.getElement());
	}

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

	public void useSkill(Player playerTwo, Card a){
		boolean found = false;
		List<Character> characterList = playerTwo.getListOfCharacterOnTable();
		if (a.getType() == CardType.DESTROY){
			Destroy destroy = (Destroy) a;
			Character c = destroy.activate();
			characterList.remove(c);
                        this.cardOnHand.remove(destroy);
		}
	}

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
        
        public void removeCharacterFromTable(Player p, Character c) {
            for (int j = 0; j < p.getListOfSkillOnTable().size(); j ++){
                Card a = p.getListOfSkillOnTable().get(j);
                if (a.getType() == CardType.AURA) {
                    Character linkedCharacter = ((Aura) a).getLinkedCard();
                    if (linkedCharacter.equals(c)) removeSkillFromTable(p,a);
                }
                else if (a.getType() == CardType.POWERUP) {
                    Character linkedCharacter2 = ((PowerUp) a).getLinkedCard();
                    if (linkedCharacter2.equals(c)) removeSkillFromTable(p,a);
                }
            }
            p.getListOfCharacterOnTable().remove(c);
        }
        
	public void attack(Player playerTwo, Character characterA, Character characterB){
        characterA.setHasAttacked(true);
		List<Character> playerTwoListOfCharacterOnTableOnTable = playerTwo.getListOfCharacterOnTable();
		List<Card> playerTwoListOfSkillOnTable = playerTwo.getListOfSkillOnTable();

		if ((characterB.getState().getPosition() == Position.ATTACK) && (characterB.getAttack() < characterA.getAttack())){
			removeCharacterFromTable(playerTwo, characterB);
			playerTwo.setLifePoint(playerTwo.getLifePoint() - (characterA.getAttack() - characterB.getAttack()));
		}
		else if ((characterB.getState().getPosition() == Position.DEFENSE) && (characterB.getDefense() < characterA.getDefense())){
			removeCharacterFromTable(playerTwo, characterB);
		}	
	}

	public void attack(Player playerTwo, Character characterA){
		// dipake apabila playerTwp character nya abis
        characterA.setHasAttacked(true);
		playerTwo.setLifePoint(playerTwo.getLifePoint() - characterA.getAttack());
	}
}
