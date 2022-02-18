package gry.szachy;

public class Game {
	public VisualBoard visualBoard;
	public HelpBoard helpBoard;
	public int result = 0; // 0 - game in progress, 1 - white won, 2 - black won, -1 - draw

	public Game() {
		this.helpBoard = new HelpBoard();
		this.visualBoard = new VisualBoard(this.helpBoard);
	}

	public int play() {
		int colour = 1;
		while (this.result == 0 && this.helpBoard.fiftyMovesRule < 100 && !this.visualBoard.noMoreMoves) {
			this.move(colour);

			// change player after every move
			colour = 3 - colour;
		}

		if (this.helpBoard.fiftyMovesRule == 100) { // draw by fifty move rule
			result = -1;
		} else if (this.visualBoard.noMoreMoves) {
			if (!SetOfMoves.checkForCheck(helpBoard, 3 - colour)) { // stale mate
				result = -1;
			} else { // check mate
				result = colour;
			}
		}

		VisualBoard.presentResult(result);
		return result;
	}

	public void move(int colour) {
		this.visualBoard.showAvailableToMovePieces(colour);
		int numberOfMoves = VisualBoard.moves.size();

		// wait for a new move from visualboard
		while (VisualBoard.moves.size() == numberOfMoves && !visualBoard.noMoreMoves) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// tell helpboard about new move
		if (!visualBoard.noMoreMoves) {
			this.helpBoard.makeMove(VisualBoard.moves.get(numberOfMoves), colour);
		}
	}
}
