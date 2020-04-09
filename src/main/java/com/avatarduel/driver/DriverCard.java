package com.avatarduel.driver;

import com.avatarduel.player.Status;

import com.avatarduel.card.*;

class DriverCard{
	public static void main(String[] args) {
		Status s = new Status();

		com.avatarduel.card.Character c = new CardBuilder(CardType.CHARACTER)
			.setId(1)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.WATER)
			.setAttack(3)
			.setDefense(5)
			.setPower(2)
			.buildCharacter();

		Aura a = new CardBuilder(CardType.AURA)
			.setId(2)
			.setAttack(-2)
			.setDefense(5)
			.setPower(1)
			.setName("Kartu Legend")
			.setDescription("Ini Kartu Legend")
			.setElement(Element.AIR)
			.buildAura();

		Destroy d = new CardBuilder(CardType.DESTROY)
			.setId(3)
			.setName("Destoyer")
			.setDescription("Ini buat ngancurin musuh")
			.setElement(Element.WATER)
			.buildDestroy();

		Land water = new CardBuilder(CardType.LAND)
			.setId(3)
			.setName("Land Water")
			.setDescription("Ini land water")
			.setElement(Element.WATER)
			.buildLand();

		c.summon(3, Position.DEFENSE);
		a.summon(2, c);
		d.summon(c);
		water.summon(s);
		
		System.out.println("===== Test Kartu ====");
		System.out.println(c);
		System.out.println(a);
		System.out.println(d);

		a.activate();
		System.out.println("===== Kartu setelah aura active =====");
		System.out.println(c);

		System.out.println("===== Kartu aura ancur =====");
		System.out.println(a.destroy());
		System.out.println(c);

		System.out.println("===== Kartu destroy active =====");
		System.out.println(d.activate());

		System.out.println("===== Sebelum land active =====");
		System.out.println(s);

		System.out.println("===== Setelah land active =====");
		water.activate();
		System.out.println(s);

	}
}