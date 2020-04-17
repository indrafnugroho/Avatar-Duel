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
	private List<Aura> auraOnTable;
	private List<PowerUp> powerUpOnTable;
	private List<Destroy> destroyOnTable;
	private Status status;
	private int lifePoint;

	public Player(String name, List<Land> landList, List<Character> characterList, List<Aura> auraList){
		this.name = name;
		this.deck = new ArrayList<Card>(50);
		this.cardOnHand = new ArrayList<Card>(10);
		this.characterOnTable = new ArrayList<Character>(6);
		this.auraOnTable = new ArrayList<Aura>();
		this.powerUpOnTable = new ArrayList<PowerUp>();
		this.destroyOnTable = new ArrayList<Destroy>();
		this.status = new Status();
		this.lifePoint = 80;

		initializeDeck(landList, characterList, auraList);
		initializeCardOnHand();

		// TESTING
//		for (int i = 0; i < 7; i++) {
//			System.out.println(cardOnHand.get(i).toString());
//			System.out.println("");
//		}
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

	public List<Aura> getListOfAuraOnTable(){ return this.auraOnTable; }

	public List<Destroy> getListOfDestroyOnTable(){ return this.destroyOnTable; }

	public List<PowerUp> getListOfPowerUpOnTable(){ return this.powerUpOnTable; }

	public void setListOfCharacterOnTable(List<Character> updated){
		this.characterOnTable = updated;
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
		for (int i = 0; i < 12; i++) {
			int a = rand.nextInt(deck.size());
			Card fromDeck = this.deck.remove(a);
			this.cardOnHand.add(fromDeck);
		}
	}

	public void drawCardFromDeck(){
		Random rand = new Random();
		int int_random = rand.nextInt(deck.size());
		Card fromDeck = deck.remove(int_random);
		cardOnHand.add(fromDeck);
	}

	public void initializeStatus(){
		status.reset();
	}

	public void putCharacterOnTable(Character c, int x, Position pos){
		this.cardOnHand.remove(c);
		c.summon(x, pos);
		if (c.getElement() == Element.AIR){
			this.status.useAir(c.getPower());
		}
		else if (c.getElement() == Element.WATER){
			this.status.useWater(c.getPower());
		}
		else if (c.getElement() == Element.EARTH){
			this.status.useEarth(c.getPower());
		}
		else if (c.getElement() == Element.FIRE){
			this.status.useFire(c.getPower());
		}
		this.characterOnTable.add(c);
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

	public void putAuraOnTable(Aura a, Character c, int x){
		this.cardOnHand.remove(a);
		if (a.getElement() == Element.AIR){
			this.status.useAir(a.getPower());
		}
		else if (c.getElement() == Element.WATER){
			this.status.useWater(a.getPower());
		}
		else if (c.getElement() == Element.EARTH){
			this.status.useEarth(a.getPower());
		}
		else if (c.getElement() == Element.FIRE){
			this.status.useFire(a.getPower());
		}
		a.summon(x, c);
		this.auraOnTable.add(a);
	}

	public void useAura(Aura a){
		a.activate();
	}

	public void removeAuraFromTable(Aura a){
		int i = 0;
		boolean found = false;
		State auraState = a.destroy();
		while (i < this.auraOnTable.size() && !found){
			State state = this.auraOnTable.get(i).getState();
			if ((state.getX() == auraState.getX()) && (state.getY() == auraState.getY())){
				this.auraOnTable.remove(i);
				found = true;
			}
		}
	}

	public void putDestroyOnTable(Destroy d, Character c, int x){
		this.cardOnHand.remove(d);
		if (d.getElement() == Element.AIR){
			this.status.useAir(d.getPower());
		}
		else if (c.getElement() == Element.WATER){
			this.status.useWater(d.getPower());
		}
		else if (c.getElement() == Element.EARTH){
			this.status.useEarth(d.getPower());
		}
		else if (c.getElement() == Element.FIRE){
			this.status.useFire(d.getPower());
		}
		d.summon(x, c);
		this.destroyOnTable.add(d);
	}

	public void useDestroy(Player b, Destroy d, Character c){
		int i = 0;
		boolean found = false;
		this.cardOnHand.remove(d);
		List<Character> characterList = b.getListOfCharacterOnTable();
		State summoned = d.activate();
		while (i < characterList.size() && !found){
			State state = characterList.get(i).getState();
			if ((state.getX() == summoned.getX()) && (state.getY() == summoned.getY())){
				characterList.remove(i);
				found = true;
			}
		}
		this.destroyOnTable.remove(d);
		b.setListOfCharacterOnTable(characterList);
	}

	public void putPowerUpOnTable(PowerUp p, Character c, int x){
		this.cardOnHand.remove(p);
		if (p.getElement() == Element.AIR){
			this.status.useAir(p.getPower());
		}
		else if (c.getElement() == Element.WATER){
			this.status.useWater(p.getPower());
		}
		else if (c.getElement() == Element.EARTH){
			this.status.useEarth(p.getPower());
		}
		else if (c.getElement() == Element.FIRE){
			this.status.useFire(p.getPower());
		}
		p.summon(x, c);
		this.powerUpOnTable.add(p);
	}

	public void usePowerUp(PowerUp p){
		p.activate();
	}

	public void removePowerUpFromTable(PowerUp p){
		int i = 0;
		boolean found = false;
		State powerUpState = p.destroy();
		while (i < this.powerUpOnTable.size() && !found){
			State state =this.powerUpOnTable.get(i).getState();
			if ((state.getX() == powerUpState.getX()) && (state.getY() == powerUpState.getY())){
				this.powerUpOnTable.remove(i);
				found = true;
			}
		}
	}


	public void attack(Player playerTwo, Character characterA, Character characterB){
		int i = 0;
		boolean found = false;

		List<Character> playerTwoListOfCardOnTable = playerTwo.getListOfCharacterOnTable();

		if ((characterB.getState().getPosition() == Position.ATTACK) && (characterB.getAttack() <= characterA.getAttack())){
			State destroyed = characterB.destroy();
			while (i < playerTwoListOfCardOnTable.size() && !found){
				State state = playerTwoListOfCardOnTable.get(i).getState();
				if ((state.getX() == destroyed.getX()) && (state.getY() == destroyed.getY())){
					found = true;
					playerTwoListOfCardOnTable.remove(i);
					playerTwo.setLifePoint(playerTwo.getLifePoint() - (characterA.getAttack() - characterB.getAttack()));
				}
			}
		}

		else if ((characterB.getState().getPosition() == Position.DEFENSE) && (characterB.getDefense() <= characterA.getDefense())){
			State destroyed = characterB.destroy();
			while (i < playerTwoListOfCardOnTable.size() && !found){
				State state = playerTwoListOfCardOnTable.get(i).getState();
				if ((state.getX() == destroyed.getX()) && (state.getY() == destroyed.getY())){
					found = true;
					playerTwoListOfCardOnTable.remove(i);
				}
			}
		}

		playerTwo.setListOfCharacterOnTable(playerTwoListOfCardOnTable);
	}


	public void attack(Player playerTwo, Character characterA){
		// dipake apabila playerTwp character nya abis
		playerTwo.setLifePoint(playerTwo.getLifePoint() - characterA.getAttack());
	}


}