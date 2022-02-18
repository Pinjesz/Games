package gry.szachy;

import java.lang.Math;

public class HelpBoard {
	public Square[][] board;
	public Square enPassant;
	public Square whiteKingPosition;
	public Square blackKingPosition;
	public boolean whiteShortCastleAvailable;
	public boolean whiteLongCastleAvailable;
	public boolean blackShortCastleAvailable;
	public boolean blackLongCastleAvailable;

	public int fiftyMovesRule;

	HelpBoard() {
		this.board = new Square[8][8];

		// Empty squares
		for (int r = 2; r < 6; r++) {
			for (int c = 0; c < 8; c++) {
				this.board[r][c] = new Square(r, c, null);
			}
		}
		// Black pieces
		this.board[0][0] = new Square(0, 0, new Piece(2, 4));
		this.board[0][1] = new Square(0, 1, new Piece(2, 2));
		this.board[0][2] = new Square(0, 2, new Piece(2, 3));
		this.board[0][3] = new Square(0, 3, new Piece(2, 5));
		this.board[0][4] = new Square(0, 4, new Piece(2, 6));
		this.board[0][5] = new Square(0, 5, new Piece(2, 3));
		this.board[0][6] = new Square(0, 6, new Piece(2, 2));
		this.board[0][7] = new Square(0, 7, new Piece(2, 4));

		// White pieces
		this.board[7][0] = new Square(7, 0, new Piece(1, 4));
		this.board[7][1] = new Square(7, 1, new Piece(1, 2));
		this.board[7][2] = new Square(7, 2, new Piece(1, 3));
		this.board[7][3] = new Square(7, 3, new Piece(1, 5));
		this.board[7][4] = new Square(7, 4, new Piece(1, 6));
		this.board[7][5] = new Square(7, 5, new Piece(1, 3));
		this.board[7][6] = new Square(7, 6, new Piece(1, 2));
		this.board[7][7] = new Square(7, 7, new Piece(1, 4));

		// Black pawns
		for (int c = 0; c < 8; c++) {
			this.board[1][c] = new Square(1, c, new Piece(2, 1));
		}

		// White pawns
		for (int c = 0; c < 8; c++) {
			this.board[6][c] = new Square(6, c, new Piece(1, 1));
		}

		this.enPassant = null;
		this.whiteKingPosition = new Square(7, 4, new Piece(1, 6));
		this.blackKingPosition = new Square(0, 4, new Piece(2, 6));
		this.whiteShortCastleAvailable = true;
		this.whiteLongCastleAvailable = true;
		this.blackShortCastleAvailable = true;
		this.blackLongCastleAvailable = true;

		this.fiftyMovesRule = 0;
	}

	HelpBoard(HelpBoard helpBoard) {
		this.board = new Square[8][8];
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				this.board[r][c] = new Square(helpBoard.board[r][c]);
			}
		}
		if (helpBoard.enPassant != null) {
			this.enPassant = new Square(helpBoard.enPassant);
		}
		this.whiteKingPosition = new Square(helpBoard.whiteKingPosition);
		this.blackKingPosition = new Square(helpBoard.blackKingPosition);
		this.whiteShortCastleAvailable = helpBoard.whiteShortCastleAvailable;
		this.whiteLongCastleAvailable = helpBoard.whiteLongCastleAvailable;
		this.blackShortCastleAvailable = helpBoard.blackShortCastleAvailable;
		this.blackLongCastleAvailable = helpBoard.blackLongCastleAvailable;
	}

	SetOfMoves[][] getAvailableMoves(int colour) {
		SetOfMoves[][] moves = new SetOfMoves[8][8];
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (this.board[r][c].piece != null) {
					moves[r][c] = new SetOfMoves(this.board[r][c], this, colour);
				}
			}
		}
		return moves;
	}

	void makeMove(Move move, int colour) {
		// fifty move rule
		this.fiftyMovesRule += 1;
		if (move.goal.piece != null || move.home.piece.kind == 1) {
			this.fiftyMovesRule = 0;
		}

		// king move
		if (move.home.piece.kind == 6) {
			if (move.home.piece.colour == 1)
				this.whiteKingPosition = new Square(move.goal);
			else
				this.blackKingPosition = new Square(move.goal);

			// castle rights no more - king moved
			if (colour == 1) {
				this.whiteLongCastleAvailable = false;
				this.whiteShortCastleAvailable = false;
			} else {
				this.blackLongCastleAvailable = false;
				this.blackShortCastleAvailable = false;
			}

			// castle is happening - teleport proper rook
			if (Math.abs(move.goal.column - move.home.column) == 2) {
				int rookColumn = 3;
				int previousRookColumn = 0;
				if (move.goal.column == 6) {
					rookColumn = 5;
					previousRookColumn = 7;
				}
				this.board[move.goal.row][rookColumn].piece = new Piece(colour, 4);
				this.board[move.goal.row][previousRookColumn].piece = null;
			}
		}

		// check if rook is doing fine on its home square
		if ((move.home.column == 0 && move.home.row == 7) || (move.goal.column == 0 && move.goal.row == 7)) {
			this.whiteLongCastleAvailable = false;
		}
		if ((move.home.column == 7 && move.home.row == 7) || (move.goal.column == 7 && move.goal.row == 7)) {
			this.whiteShortCastleAvailable = false;
		}
		if ((move.home.column == 0 && move.home.row == 7) || (move.goal.column == 0 && move.goal.row == 7)) {
			this.blackLongCastleAvailable = false;
		}
		if ((move.home.column == 7 && move.home.row == 7) || (move.goal.column == 7 && move.goal.row == 7)) {
			this.blackShortCastleAvailable = false;
		}

		// make changes in helpBoard
		this.board[move.goal.row][move.goal.column].piece = move.home.piece;
		this.board[move.home.row][move.home.column].piece = null;
		if (move.enPassant) {
			this.board[move.home.row][move.goal.column].piece = null;
		}

		// check if opponent has available en passant
		this.enPassant = null;
		if (move.goal.piece.kind == 1) {
			if (Math.abs(move.goal.row - move.home.row) == 2) {
				this.enPassant = new Square((move.home.row + move.goal.row) / 2, move.home.column, null);
			}
		}
	}
}
