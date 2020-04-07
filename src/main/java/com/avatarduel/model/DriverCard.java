
class DriverCard{
	public static void main(String[] args) {
		Card c = new CardBuilder(CardType.CHARACTER)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.AIR)
			.build();

		Card a = new CardBuilder(CardType.AURA)
			.setName("Kartu Langka")
			.setDescription("Ini Kartu Langka")
			.setElement(Element.AIR)
			.build();
	}
}