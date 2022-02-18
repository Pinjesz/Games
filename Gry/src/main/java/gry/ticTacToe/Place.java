package gry.ticTacToe;

public class Place {
	public int column;
	public int row;
	public int sign;

	/**
	 * 
	 * @param column
	 * @param row
	 * @param sign   0 - empty 1 - circle 2 - cross
	 */
	public Place(int column, int row, int sign) {
		this.row = row;
		this.column = column;
		this.sign = sign;
	}

	public Place(Place place) {
		this.row = place.row;
		this.column = place.column;
		this.sign = place.sign;
	}

	public String toString() {
		return String.valueOf(this.sign) + " on " + String.valueOf(this.column) + " " + String.valueOf(this.row);
	}
}
