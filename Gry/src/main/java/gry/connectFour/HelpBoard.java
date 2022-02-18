package gry.connectFour;

public class HelpBoard {
	public Column[] board;
	public boolean noMoreMoves;
	public int result;

	public HelpBoard() {
		this.board = new Column[7];
		this.noMoreMoves = false;
		this.result = 0;

		// Empty place
		for (int c = 0; c < 7; c++) {
			this.board[c] = new Column(c);
			for (int r = 0; r < 6; r++) {
				this.board[c].places[r] = new Place(c, r, 0);
			}
		}
	}

	public void makeMove(int column, int colour) {
		int row = this.board[column].emptyPlace; // row of new place is the last empty row
		this.board[column].places[row].colour = colour;
		this.board[column].emptyPlace -= 1;

		// check if game ended
		checkForFour(column, row, colour);
	}

	public void checkForFour(int column, int row, int colour) {
		for (int cChange = -1; cChange < 2; cChange++) {
			for (int rChange = 0; rChange < 2; rChange++) {
				if (!((cChange == -1 && rChange == 0) || (cChange == 0 && rChange == 0))) {

					int kRowMin = Math.max(-3, -row);
					int kRowMax = Math.min(0, 2 - row);
					int kColumnMin = Math.max(-3, -column);
					int kColumnMax = Math.min(0, 3 - column);
					int kMin;
					int kMax;
					if (rChange == 0) { // horizontal
						kMin = kColumnMin;
						kMax = kColumnMax;
					} else if (cChange == 0) { // vertical
						kMin = kRowMin;
						kMax = kRowMax;
					} else { // diagonal
						if (cChange == -1) { // left up -> right down
							kMin = -Math.min(3, Math.min(row, 6 - column));
							kMax = Math.min(0, Math.min(2 - row, column - 3));
						} else { // cChage == 1 // right up -> left down
							kMin = Math.max(kRowMin, kColumnMin);
							kMax = Math.min(kRowMax, kColumnMax);
						}
					}
					for (int k = kMin; k <= kMax; k++) {
						boolean line = true;
						for (int i = k; i < k + 4; i++) {
							if (this.board[column + i * cChange].places[row + i * rChange].colour != colour) {
								line = false;
							}
						}
						if (line == true) {
							this.result = colour;
							return;
						}
					}
				}
			}
		}
	}
}
