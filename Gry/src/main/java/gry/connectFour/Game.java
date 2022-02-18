package gry.connectFour;

public class Game {
	public VisualBoard visualBoard;
	public HelpBoard helpBoard;
	// result : 0 - game in progress, 1 - yellow won, 2 - red won, -1 - draw

	public Game() {
		this.helpBoard = new HelpBoard();
		this.visualBoard = new VisualBoard(this.helpBoard);
	}

	public int play() {
		int colour = 1;
		while (this.helpBoard.result == 0 && !VisualBoard.noMoreMoves) {
			this.move(colour);

			// change player after every move
			colour = 3 - colour;
		}

		// if no one won and board is full -> draw
		if (this.helpBoard.result == 0 && VisualBoard.noMoreMoves) {
			this.helpBoard.result = -1;
		}

		// remove mouse listeners to indicate end of the game
		// set proper colors
		// frame acts as a picture
		for (int c = 0; c < 7; c++) {
			this.visualBoard.removeMouseListeners(this.visualBoard.columns[c]);

			this.visualBoard.columns[c].backColor = VisualBoard.black;
			this.visualBoard.columns[c].activeColor = VisualBoard.gray;
			this.visualBoard.columns[c].topColor = VisualBoard.black;
			this.visualBoard.columns[c].repaint();
		}
		VisualBoard.presentResult(this.helpBoard.result);
		return this.helpBoard.result;
	}

	// wait for a new move from visualboard
	public void move(int colour) {
		this.visualBoard.showAvailableColumns(colour);
		int numberOfMoves = VisualBoard.moves.size();
		while (VisualBoard.moves.size() == numberOfMoves && !VisualBoard.noMoreMoves) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// tell helpboard about new move
		this.helpBoard.makeMove(VisualBoard.moves.get(VisualBoard.moves.size() - 1), colour);
	}
}
