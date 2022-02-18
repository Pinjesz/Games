package gry.szachy;

/**
 * @param colour 1 - white 2 - black
 * @param kind   1 - pawn 2 - knight 3 - bishop 4 - rook 5 - queen 6 - king
 */
public class Piece {
	public int colour;
	public int kind;

	/**
	 * @param colour 1 - white 2 - black
	 * @param kind   1 - pawn 2 - knight 3 - bishop 4 - rook 5 - queen 6 - king
	 */
	Piece(int colour, int kind) {

		this.colour = colour;
		this.kind = kind;
	}

	Piece(Piece piece) {

		this.colour = piece.colour;
		this.kind = piece.kind;
	}

	public String toString() {
		String string = "";
		if (this.colour == 1) {
			string = "white";
		} else {
			string = "black";
		}
		switch (this.kind) {
			case 1:
				string += " pawn";
				break;
			case 2:
				string += " knight";
				break;
			case 3:
				string += " bishop";
				break;
			case 4:
				string += " rook";
				break;
			case 5:
				string += " queen";
				break;
			case 6:
				string += " king";
				break;
		}
		return string;
	}

	public boolean equals(Piece piece) {
		if (this.colour != piece.colour)
			return false;
		if (this.kind != piece.kind)
			return false;
		return true;
	}
}
