package gry.szachy;

/**
 * @param row
 * @param column
 * @param piece
 */
public class Square {
	public final int row;
	public final int column;
	public Piece piece;

	/**
	 * @param row
	 * @param column
	 * @param piece
	 */
	Square(int row, int column, Piece piece) {
		this.row = row;
		this.column = column;
		this.piece = piece;
	}

	Square(Square square) {
		this.row = square.row;
		this.column = square.column;
		if (square.piece != null) {
			this.piece = new Piece(square.piece);
		} else {
			this.piece = null;
		}
	}

	public String toString() {
		String string = "";
		if (this.piece != null) {
			string = this.piece.toString();
			string += " on ";
		} else {
			string += "empty ";
		}
		switch (this.column) {
			case 0:
				string += "a";
				break;
			case 1:
				string += "b";
				break;
			case 2:
				string += "c";
				break;
			case 3:
				string += "d";
				break;
			case 4:
				string += "e";
				break;
			case 5:
				string += "f";
				break;
			case 6:
				string += "g";
				break;
			case 7:
				string += "h";
				break;
		}
		string += String.valueOf(8 - this.row);
		string += " (" + String.valueOf(this.row) + ", " + String.valueOf(this.column) + ")";

		return string;
	}

	public boolean equals(Square square) {
		if (this.column != square.column)
			return false;
		if (this.row != square.row)
			return false;
		if (this.piece != null) {
			if (!this.piece.equals(square.piece))
				return false;
		} else if (square.piece != null)
			return false;
		return true;
	}
}
