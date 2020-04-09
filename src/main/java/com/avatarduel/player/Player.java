package com.avatarduel.player;

import com.avatarduel.card.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Player{
	private String name;
	private List<Card> deck;
	private List<Card> cardOnHand;
	private List<Card> cardOnTable;
	private Status status;
	private int lifePoint;

	public Player(List<Land> landList, List<com.avatarduel.card.Character> characterList, List<Aura> auraList){
		this.name = "Player";
		this.deck = new ArrayList<Card>(50);
		this.cardOnHand = new ArrayList<Card>(10);
		this.cardOnTable = new ArrayList<Card>(16);
		this.lifePoint = 80;

		initializeDeck(landList, characterList, auraList);
		intializeCardOnHand();

		// TESTING
//		for (int i = 0; i < 7; i++) {
//			System.out.println(cardOnHand.get(i).toString());
//			System.out.println("");
//		}

	}

	public void initializeDeck(List<Land> landList, List<com.avatarduel.card.Character> characterList, List<Aura> auraList){
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

	public void intializeCardOnHand(){
		// INSIALISASI KARTU DI TANGAN, AMBIL 7 DARI DECK
		Random rand = new Random();
		for (int i = 0; i < 7; i++) {
			int a = rand.nextInt(deck.size());
			Card fromdeck = this.deck.remove(a);
			this.cardOnHand.add(fromdeck);
		}
	}

	public void getCardFromDeck(){
		Random rand = new Random();
		int int_random = rand.nextInt(deck.size());
		Card fromdeck = deck.remove(int_random);
//		System.out.println(fromdeck.toString());
		cardOnHand.add(fromdeck);
	}

	public void intializeStatus(){
		status.reset();
	}

	public void putCharacterOnTable(com.avatarduel.card.Character c, int x, int y, Position pos){
		this.cardOnHand.remove(c);
		State current = new State(x, y, pos);
		c.setState(current);
		if (c.getElement() == Element.AIR){
			this.status.useAir(c.power());
		}
		else if (c.getElement() == Element.WATER){
			this.status.useWater(c.power());
		}
		else if (c.getElement() == Element.EARTH){
			this.status.useEarth(c.power());
		}
		else if (c.getElement() == Element.FIRE){
			this.status.useFire(c.power());
		}
		this.cardOnTable.add(c);
	}

	public void changeCharacterPosition(com.avatarduel.card.Character c){
		c.setState("rotate");
	}

	public void changeCharacterPosition(com.avatarduel.card.Character c, int x, int y){
		c.setState(new State(x, y, c.getState().getPosition()));
	}

	public void putLandOnTable(Land l){
		this.cardOnHand.remove(l);
		status.addStatus(l.getElement());
	}


//	public void putAuraOnTable(Aura a, Character c, int x){
//		this.cardOnHand.remove(a);
//		a.summon(x, c);
//		this.cardOnTable.add(a);
//	}
//
//	public void putDestroyOnTable(Aura a, Character c, int x){
//		this.cardOnHand.remove(a);
//		a.summon(x, c);
//		this.cardOnTable.add(a);
//	}

//	public void removeSkillFromTable(Aura a){
//		//
//	}
}