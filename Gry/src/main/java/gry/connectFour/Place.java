package gry.connectFour;

public class Place {
	public int column;
	public int row;
	public int colour;

	/**
	 * 
	 * @param column
	 * @param row
	 * @param colour 0 - empty 1 - yellow 2 - red
	 */
	public Place(int column, int row, int colour) {
		this.row = row;
		this.column = column;
		this.colour = colour;
	}

	public Place(Place place) {
		this.row = place.row;
		this.column = place.column;
		this.colour = place.colour;
	}
}
