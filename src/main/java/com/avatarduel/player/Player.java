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
	private List<Card> cardOnTable;
	private Status status;
	private int lifePoint;

	public Player(String name, List<Land> landList, List<Character> characterList, List<Aura> auraList){
            this.name = name;
            this.deck = new ArrayList<Card>(50);
            this.cardOnHand = new ArrayList<Card>(10);
            this.cardOnTable = new ArrayList<Card>(12);
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

	public List<Card> getListOfCardOnTable(){
		return this.cardOnTable;
	}

	public List<Card> getListofCardOnHand(){
		return this.cardOnTable;
	}

	public void setListOfCardOnTable(List<Card> updated){
		this.cardOnTable = updated;
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
			Card fromdeck = this.deck.remove(a);
			this.cardOnHand.add(fromdeck);
		}
	}

	public void drawCardFromDeck(){
		Random rand = new Random();
		int int_random = rand.nextInt(deck.size());
		Card fromdeck = deck.remove(int_random);
		cardOnHand.add(fromdeck);
	}

	public void initializeStatus(){
		status.reset();
	}

	public void changeCharacterPosition(Character c){
		State updated = c.getState();
		updated.rotate();
		c.setState(updated);
	}

	public void useCharacter(Character c, int x, Position pos){
		this.cardOnHand.remove(c);
		c.summon(x, pos);
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

	public void useLand(Land l){
		this.cardOnHand.remove(l);
		this.status.addStatus(l.getElement());
	}


	public void useAura(Aura a, Character c, int x){
		this.cardOnHand.remove(a);
		a.summon(x, c);
		this.cardOnTable.add(a);
	}

	public void useDestroy(Player b, Destroy d, Character c){
		int i = 0;
		boolean found = false;
		this.cardOnHand.remove(d);
		List<Card> other = b.getListOfCardOnTable();
		d.summon(c);
		State summoned = d.activate();
		while (i < other.size() && !found){
			if ((other.get(i).getState().getX() == summoned.getX()) && (other.get(i).getState().getY() == summoned.getY())){
				other.remove(i);
				found = true;
			}
		}
		b.setListOfCardOnTable(other);
	}

	public void usePowerUp(PowerUp p, Character c, int x){
		this.cardOnHand.remove(p);
		p.summon(x,c);
		p.activate();
		this.cardOnTable.add(p);
	}

	public void removeAuraFromTable(Aura a){
		int i = 0;
		boolean found = false;
		State auraState = a.destroy();
		while (i < this.cardOnTable.size() && !found){
			if ((this.cardOnTable.get(i).getState().getX() == auraState.getX()) && (this.cardOnTable.get(i).getState().getY() == auraState.getY())){
				this.cardOnTable.remove(i);
				found = true;
			}
		}
	}

	public void removePowerUpFromTable(PowerUp p){
		int i = 0;
		boolean found = false;
		State powerUpState = p.destroy();
		while (i < this.cardOnTable.size() && !found){
			if ((this.cardOnTable.get(i).getState().getX() == powerUpState.getX()) && (this.cardOnTable.get(i).getState().getY() == powerUpState.getY())){
				this.cardOnTable.remove(i);
				found = true;
			}
		}
	}


	public void attack(Player playerTwo, Character characterA, Character characterB){
		int i = 0;
		boolean found = false;

		List<Card> playerTwoListOfCardOnTable = playerTwo.getListOfCardOnTable();

		if ((characterB.getState().getPosition() == Position.ATTACK) && (characterB.attack() <= characterA.attack())){
			State destroyed = characterB.destroy();
			while (i < playerTwoListOfCardOnTable.size() && !found){
				if ((playerTwoListOfCardOnTable.get(i).getState().getX() == destroyed.getX()) && (playerTwoListOfCardOnTable.get(i).getState().getY() == destroyed.getY())){
					found = true;
					playerTwoListOfCardOnTable.remove(i);
					playerTwo.setLifePoint(playerTwo.getLifePoint() - (characterA.attack() - characterB.attack()));
				}
			}
		}

		else if ((characterB.getState().getPosition() == Position.DEFENSE) && (characterB.defense() <= characterA.defense())){
			State destroyed = characterB.destroy();
			while (i < playerTwoListOfCardOnTable.size() && !found){
				if ((playerTwoListOfCardOnTable.get(i).getState().getX() == destroyed.getX()) && (playerTwoListOfCardOnTable.get(i).getState().getY() == destroyed.getY())){
					found = true;
					playerTwoListOfCardOnTable.remove(i);
				}
			}
		}
	}


	public void attack(Player playerTwo, Character characterA){
		// dipake apabila playerTwp character nya abis
		playerTwo.setLifePoint(playerTwo.getLifePoint() - characterA.attack());
	}


}