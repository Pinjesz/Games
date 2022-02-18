package gry.szachy;

public class Move {
	public Square home;
	public Square goal;
	public boolean enPassant;

	Move(Square home, Square goal, boolean enPassant) {
		this.home = home;
		this.goal = goal;
		this.enPassant = enPassant;
	}

	Move(Move move) {
		this.home = new Square(move.home);
		this.goal = new Square(move.goal);
		this.enPassant = move.enPassant;
	}

	public String toString() {
		return this.home.toString() + " to " + this.goal.toString();
	}
}
