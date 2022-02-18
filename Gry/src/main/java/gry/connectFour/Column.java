package gry.connectFour;

public class Column {
	public Place[] places;
	public int emptyPlace; // last empty row
	public int c;

	public Column(int c) {
		this.places = new Place[6];
		this.emptyPlace = 5;
	}
}
