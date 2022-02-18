package gry.ticTacToe;

public class Game {
	public VisualBoard visualBoard;
	public HelpBoard helpBoard;
	// result : 0 - game in progress, 1 - circle won, 2 - cross won, -1 - draw

	public Game() {
		this.helpBoard = new HelpBoard();
		this.visualBoard = new VisualBoard(this.helpBoard);
	}

	public int play() {
		int sign = 1;
		while (this.helpBoard.result == 0 && !VisualBoard.noMoreMoves) {
			this.move(sign);

			// change player after every move
			sign = 3 - sign;
		}

		// if no one won and board is full -> draw
		if (this.helpBoard.result == 0 && VisualBoard.noMoreMoves) {
			this.helpBoard.result = -1;
		}

		// remove mouse listeners to indicate end of the game
		// frame acts as a picture
		for (int c = 0; c < 3; c++) {
			for (int r = 0; r < 3; r++) {
				this.visualBoard.removeMouseListeners(this.visualBoard.places[c][r]); //
			}
		}

		VisualBoard.presentResult(this.helpBoard.result);
		return this.helpBoard.result;
	}

	public void move(int sign) {
		this.visualBoard.showAvailablePlaces(sign);
		int numberOfMoves = VisualBoard.moves.size();

		// wait for a new move from visualboard
		while (VisualBoard.moves.size() == numberOfMoves && !VisualBoard.noMoreMoves) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// tell helpboard about new move
		this.helpBoard.makeMove(VisualBoard.moves.get(VisualBoard.moves.size() - 1));
	}
}
