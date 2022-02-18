package gry.ticTacToe;

public class HelpBoard {
	public Place[][] board;
	public boolean noMoreMoves;
	public int result;

	HelpBoard() {
		this.board = new Place[3][3];
		this.noMoreMoves = false;
		this.result = 0;

		// Empty place
		for (int c = 0; c < 3; c++) {
			for (int r = 0; r < 3; r++) {
				this.board[c][r] = new Place(c, r, 0);
			}
		}
	}

	public void makeMove(Place place) {
		this.board[place.column][place.row].sign = place.sign;

		// check if game ended
		checkForThree(place);
	}

	public void checkForThree(Place place) {
		if (place.column == 1 && place.row == 1) { // center
			if (this.board[0][0].sign == place.sign && this.board[2][2].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[0][1].sign == place.sign && this.board[2][1].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[0][2].sign == place.sign && this.board[2][0].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[1][2].sign == place.sign && this.board[1][0].sign == place.sign) {
				this.result = place.sign;
				return;
			}
		} else if ((place.column + place.row) % 2 == 0) { // cornerns
			if (this.board[place.column][1].sign == place.sign
					&& this.board[place.column][2 - place.row].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[1][place.row].sign == place.sign
					&& this.board[2 - place.column][place.row].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[1][1].sign == place.sign && this.board[2 - place.column][2 - place.row].sign == place.sign) {
				this.result = place.sign;
				return;
			}
		} else if (place.column == 1) { // edges in column one
			if (this.board[place.column][1].sign == place.sign
					&& this.board[place.column][2 - place.row].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[0][place.row].sign == place.sign && this.board[2][place.row].sign == place.sign) {
				this.result = place.sign;
				return;
			}
		} else { // place.row == 1 //edges in row one
			if (this.board[1][place.row].sign == place.sign
					&& this.board[2 - place.column][place.row].sign == place.sign) {
				this.result = place.sign;
				return;
			}
			if (this.board[place.column][0].sign == place.sign && this.board[place.column][2].sign == place.sign) {
				this.result = place.sign;
				return;
			}
		}
	}
}
