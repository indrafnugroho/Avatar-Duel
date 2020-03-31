
class DriverCard{
	public static void main(String[] args) {
		Card c = new Card.CardBuilder(CardType.CHARACTER)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.AIR)
			.build();

		Card a = new Card.CardBuilder(CardType.AURA)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.AIR)
			.build();
	}
}