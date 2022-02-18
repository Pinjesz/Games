package gry.szachy;

import java.util.ArrayList;

public class SetOfMoves {
	public final Square currentPosition;
	public ArrayList<Move> availableMoves;

	SetOfMoves(Square square, HelpBoard board, int colour) {
		this.currentPosition = square;
		this.availableMoves = this.checkForAvailableMoves(board, colour);
	}

	public ArrayList<Move> checkForAvailableMoves(HelpBoard helpBoard, int colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		if (this.currentPosition.piece != null) {
			if (this.currentPosition.piece.colour == colour) {
				switch (this.currentPosition.piece.kind) {
					case 1:
						moves = checkForAvailableMovesPawn(helpBoard, colour);
						break; // pawn
					case 2:
						moves = checkForAvailableMovesKnight(helpBoard, colour);
						break; // knight
					case 3:
						moves = checkForAvailableMovesBishop(helpBoard, colour);
						break; // bishop
					case 4:
						moves = checkForAvailableMovesRook(helpBoard, colour);
						break; // rook
					case 5:
						moves = checkForAvailableMovesQueen(helpBoard, colour);
						break; // queen
					case 6:
						moves = checkForAvailableMovesKing(helpBoard, colour);
						break; // king
					default:
						moves = null;
				}
			}
		}
		return moves;
	}

	public ArrayList<Move> checkForAvailableMovesPawn(HelpBoard helpBoard, int colour) {
		int c = this.currentPosition.column;
		int r = this.currentPosition.row;
		Move move;
		ArrayList<Move> moves = new ArrayList<Move>();
		if (helpBoard.board[r - 3 + 2 * colour][c].piece == null) {
			move = new Move(this.currentPosition, helpBoard.board[r - 3 + 2 * colour][c], false);
			if (this.simulateMove(helpBoard, colour, new Move(move))) { // normal move
				moves.add(move);
			}
			if (r == 11 - 5 * colour && helpBoard.board[5 - colour][c].piece == null) {
				move = new Move(this.currentPosition, helpBoard.board[5 - colour][c], false);
				if (this.simulateMove(helpBoard, colour, new Move(move))) { // two squares at first move
					moves.add(move);
				}
			}
		}
		if (c + 1 < 8) {
			if (helpBoard.board[r - 3 + 2 * colour][c + 1].piece != null) {
				if (helpBoard.board[r - 3 + 2 * colour][c + 1].piece.colour == 3 - colour) {
					move = new Move(this.currentPosition, helpBoard.board[r - 3 + 2 * colour][c + 1], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) { // capture 1
						moves.add(move);
					}
				}
			}
		}
		if (c - 1 >= 0) {
			if (helpBoard.board[r - 3 + 2 * colour][c - 1].piece != null) {
				if (helpBoard.board[r - 3 + 2 * colour][c - 1].piece.colour == 3 - colour) {
					move = new Move(this.currentPosition, helpBoard.board[r - 3 + 2 * colour][c - 1], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) { // capture 2
						moves.add(move);
					}
				}
			}
		}
		// en passant
		if (helpBoard.enPassant != null) {
			if (colour == 1 && r == 3) {
				if (c + 1 == helpBoard.enPassant.column) {
					move = new Move(this.currentPosition, helpBoard.board[2][c + 1], true);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
				if (c - 1 == helpBoard.enPassant.column) {
					move = new Move(this.currentPosition, helpBoard.board[2][c - 1], true);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			} else if (colour == 2 && r == 4) {
				if (c + 1 == helpBoard.enPassant.column) {
					move = new Move(this.currentPosition, helpBoard.board[5][c + 1], true);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
				if (c - 1 == helpBoard.enPassant.column) {
					move = new Move(this.currentPosition, helpBoard.board[5][c - 1], true);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
		}
		return moves;
	}

	public ArrayList<Move> checkForAvailableMovesKnight(HelpBoard helpBoard, int colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		int c = this.currentPosition.column;
		int r = this.currentPosition.row;
		if (r + 1 < 8) {
			if (c + 2 < 8) {
				boolean opositeColour = false;
				if (helpBoard.board[r + 1][c + 2].piece != null) {
					if (helpBoard.board[r + 1][c + 2].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r + 1][c + 2].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r + 1][c + 2], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
			if (c - 2 >= 0) {
				boolean opositeColour = false;
				if (helpBoard.board[r + 1][c - 2].piece != null) {
					if (helpBoard.board[r + 1][c - 2].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r + 1][c - 2].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r + 1][c - 2], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
		}
		if (r - 1 >= 0) {
			if (c + 2 < 8) {
				boolean opositeColour = false;
				if (helpBoard.board[r - 1][c + 2].piece != null) {
					if (helpBoard.board[r - 1][c + 2].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r - 1][c + 2].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r - 1][c + 2], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
			if (c - 2 >= 0) {
				boolean opositeColour = false;
				if (helpBoard.board[r - 1][c - 2].piece != null) {
					if (helpBoard.board[r - 1][c - 2].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r - 1][c - 2].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r - 1][c - 2], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
		}
		if (r + 2 < 8) {
			if (c + 1 < 8) {
				boolean opositeColour = false;
				if (helpBoard.board[r + 2][c + 1].piece != null) {
					if (helpBoard.board[r + 2][c + 1].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r + 2][c + 1].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r + 2][c + 1], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
			if (c - 1 >= 0) {
				boolean opositeColour = false;
				if (helpBoard.board[r + 2][c - 1].piece != null) {
					if (helpBoard.board[r + 2][c - 1].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r + 2][c - 1].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r + 2][c - 1], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
		}
		if (r - 2 >= 0) {
			if (c + 1 < 8) {
				boolean opositeColour = false;
				if (helpBoard.board[r - 2][c + 1].piece != null) {
					if (helpBoard.board[r - 2][c + 1].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r - 2][c + 1].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r - 2][c + 1], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
			if (c - 1 >= 0) {
				boolean opositeColour = false;
				if (helpBoard.board[r - 2][c - 1].piece != null) {
					if (helpBoard.board[r - 2][c - 1].piece.colour == 3 - colour) {
						opositeColour = true;
					}
				}
				if (opositeColour || helpBoard.board[r - 2][c - 1].piece == null) {
					move = new Move(this.currentPosition, helpBoard.board[r - 2][c - 1], false);
					if (this.simulateMove(helpBoard, colour, new Move(move))) {
						moves.add(move);
					}
				}
			}
		}
		return moves;
	}

	public ArrayList<Move> checkForAvailableMovesBishop(HelpBoard helpBoard, int colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		int c = this.currentPosition.column;
		int r = this.currentPosition.row;
		boolean blocked;
		boolean opositeColour;
		for (int i = -1; i < 2; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				blocked = false;
				opositeColour = false;
				for (int k = 1; k < 8; k++) {
					if (!blocked) {
						if (r + k * i >= 0 && r + k * i < 8 && c + k * j >= 0 && c + k * j < 8) {
							if (helpBoard.board[r + k * i][c + k * j].piece != null) {
								blocked = true;
								if (helpBoard.board[r + k * i][c + k * j].piece.colour == 3 - colour) {
									opositeColour = true;
								}
							}
							if (opositeColour || helpBoard.board[r + k * i][c + k * j].piece == null) {
								move = new Move(this.currentPosition, helpBoard.board[r + k * i][c + k * j], false);
								if (this.simulateMove(helpBoard, colour, new Move(move))) {
									moves.add(move);
								}
							}
						}
					}
				}
			}
		}
		return moves;
	}

	public ArrayList<Move> checkForAvailableMovesRook(HelpBoard helpBoard, int colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		int c = this.currentPosition.column;
		int r = this.currentPosition.row;
		boolean blocked;
		boolean opositeColour;
		for (int i = -1; i < 2; i += 2) {
			blocked = false;
			opositeColour = false;
			for (int k = 1; k < 8; k++) {
				if (!blocked) {
					if (r + k * i >= 0 && r + k * i < 8) {
						if (helpBoard.board[r + k * i][c].piece != null) {
							blocked = true;
							if (helpBoard.board[r + k * i][c].piece.colour == 3 - colour) {
								opositeColour = true;
							}
						}
						if (opositeColour || helpBoard.board[r + k * i][c].piece == null) {
							move = new Move(this.currentPosition, helpBoard.board[r + k * i][c], false);
							if (this.simulateMove(helpBoard, colour, new Move(move))) {
								moves.add(move);
							}
						}
					}
				}
			}
		}
		for (int j = -1; j < 2; j += 2) {
			blocked = false;
			opositeColour = false;
			for (int k = 1; k < 8; k++) {
				if (!blocked) {
					if (c + k * j >= 0 && c + k * j < 8) {
						if (helpBoard.board[r][c + k * j].piece != null) {
							blocked = true;
							if (helpBoard.board[r][c + k * j].piece.colour == 3 - colour) {
								opositeColour = true;
							}
						}
						if (opositeColour || helpBoard.board[r][c + k * j].piece == null) {
							move = new Move(this.currentPosition, helpBoard.board[r][c + k * j], false);
							if (this.simulateMove(helpBoard, colour, new Move(move))) {
								moves.add(move);
							}
						}
					}
				}
			}
		}
		return moves;
	}

	public ArrayList<Move> checkForAvailableMovesQueen(HelpBoard helpBoard, int colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Move> movesBishop = checkForAvailableMovesBishop(helpBoard, colour);
		ArrayList<Move> movesRook = checkForAvailableMovesRook(helpBoard, colour);
		moves.addAll(movesBishop);
		moves.addAll(movesRook);
		return moves;
	}

	public ArrayList<Move> checkForAvailableMovesKing(HelpBoard helpBoard, int colour) {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		int c = this.currentPosition.column;
		int r = this.currentPosition.row;
		boolean opositeColour;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				opositeColour = false;
				if (r + i >= 0 && r + i < 8 && c + j >= 0 && c + j < 8 && (i != 0 || j != 0)) {
					if (helpBoard.board[r + i][c + j].piece != null) {
						if (helpBoard.board[r + i][c + j].piece.colour == 3 - colour) {
							opositeColour = true;
						}
					}
					if (opositeColour || helpBoard.board[r + i][c + j].piece == null) {
						move = new Move(this.currentPosition, helpBoard.board[r + i][c + j], false);
						if (this.simulateMove(helpBoard, colour, new Move(move))) {
							moves.add(move);
						}
					}
				}
			}
		}
		// castle
		if (this.simulateCastle(helpBoard, colour, "short")) {
			move = new Move(this.currentPosition, helpBoard.board[r][c + 2], false);
			moves.add(move);
		}
		if (this.simulateCastle(helpBoard, colour, "long")) {
			move = new Move(this.currentPosition, helpBoard.board[r][c - 2], false);
			moves.add(move);
		}
		return moves;
	}

	/**
	 * check if castle is legal
	 * 
	 * @param helpBoard
	 * @param colour
	 * @param side
	 * @return {@code true} if move is legal, {@code false} otherwise
	 */
	public boolean simulateCastle(HelpBoard helpBoard, int colour, String side) {
		int step;
		int row;
		Move move;
		if (colour == 1) {
			row = 7;
			if (side.equals("long")) {
				step = -1;
				if (!helpBoard.whiteLongCastleAvailable)
					return false;
			} else {
				step = 1;
				if (!helpBoard.whiteShortCastleAvailable)
					return false;
			}
		} else {
			row = 0;
			if (side.equals("long")) {
				step = -1;
				if (!helpBoard.blackLongCastleAvailable)
					return false;
			} else {
				step = 1;
				if (!helpBoard.blackShortCastleAvailable)
					return false;
			}
		}
		if (checkForCheck(helpBoard, colour))
			return false; // now in check
		move = new Move(this.currentPosition, helpBoard.board[row][4 + step], false);
		if (!this.simulateMove(helpBoard, colour, new Move(move)))
			return false; // first square attacked
		move = new Move(this.currentPosition, helpBoard.board[row][4 + 2 * step], false);
		if (!this.simulateMove(helpBoard, colour, new Move(move)))
			return false; // second square attacked
		if (helpBoard.board[row][4 + 2 * step].piece != null)
			return false; // second square empty
		if (step == -1 && helpBoard.board[row][4 + 3 * step].piece != null)
			return false; // third square empty for long castle
		return true;
	}

	/**
	 * check if move is legal
	 * 
	 * @param helpBoard
	 * @param colour
	 * @param move
	 * @return {@code true} if move is legal, {@code false} otherwise
	 */
	public boolean simulateMove(HelpBoard helpBoard, int colour, Move move) {
		HelpBoard simulatedBoard = new HelpBoard(helpBoard);
		simulatedBoard.board[move.goal.row][move.goal.column].piece = move.home.piece;
		simulatedBoard.board[move.home.row][move.home.column].piece = null;
		move.goal.piece = move.home.piece;
		if (move.home.piece.kind == 6) {
			if (move.home.piece.colour == 1)
				simulatedBoard.whiteKingPosition = move.goal;
			else
				simulatedBoard.blackKingPosition = move.goal;
		}
		if (move.enPassant) {
			simulatedBoard.board[move.home.row][move.goal.column].piece = null;
		}
		return !checkForCheck(simulatedBoard, colour);
	}

	/**
	 * check if king is in check
	 * 
	 * @param helpBoard
	 * @param colour
	 * @return {@code true} if is in check, {@code false} otherwise
	 */
	public static boolean checkForCheck(HelpBoard helpBoard, int colour) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (helpBoard.board[r][c].piece != null) {
					if (helpBoard.board[r][c].piece.colour == 3 - colour) {
						boolean check = checkForAttack(helpBoard, colour, new Square(helpBoard.board[r][c]));
						if (check) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean checkForAttack(HelpBoard helpBoard, int colour, Square square) {
		Square kingPosition;
		if (colour == 1) {
			kingPosition = helpBoard.whiteKingPosition;
		} else {
			kingPosition = helpBoard.blackKingPosition;
		}
		boolean check = false;
		switch (square.piece.kind) {
			case 1:
				check = checkForAttackPawn(helpBoard, colour, new Square(square), new Square(kingPosition));
				break; // pawn
			case 2:
				check = checkForAttackKnight(helpBoard, colour, new Square(square), new Square(kingPosition));
				break; // knight
			case 3:
				check = checkForAttackBishop(helpBoard, colour, new Square(square), new Square(kingPosition));
				break; // bishop
			case 4:
				check = checkForAttackRook(helpBoard, colour, new Square(square), new Square(kingPosition));
				break; // rook
			case 5:
				check = checkForAttackQueen(helpBoard, colour, new Square(square), new Square(kingPosition));
				break; // queen
			case 6:
				check = checkForAttackKing(helpBoard, colour, new Square(square), new Square(kingPosition));
				break; // king
		}
		return check;
	}

	public static boolean checkForAttackPawn(HelpBoard helpBoard, int colour, Square square, Square kingPosition) {
		if ((square.row + 3 - 2 * colour == kingPosition.row) && (square.column + 1 == kingPosition.column)) {
			return true;
		}
		if ((square.row + 3 - 2 * colour == kingPosition.row) && (square.column - 1 == kingPosition.column)) {
			return true;
		}
		return false;
	}

	public static boolean checkForAttackKnight(HelpBoard helpBoard, int colour, Square square, Square kingPosition) {
		if ((square.row + 1 == kingPosition.row) && (square.column + 2 == kingPosition.column)) {
			return true;
		}
		if ((square.row - 1 == kingPosition.row) && (square.column + 2 == kingPosition.column)) {
			return true;
		}
		if ((square.row + 1 == kingPosition.row) && (square.column - 2 == kingPosition.column)) {
			return true;
		}
		if ((square.row - 1 == kingPosition.row) && (square.column - 2 == kingPosition.column)) {
			return true;
		}
		if ((square.row + 2 == kingPosition.row) && (square.column + 1 == kingPosition.column)) {
			return true;
		}
		if ((square.row - 2 == kingPosition.row) && (square.column + 1 == kingPosition.column)) {
			return true;
		}
		if ((square.row + 2 == kingPosition.row) && (square.column - 1 == kingPosition.column)) {
			return true;
		}
		if ((square.row - 2 == kingPosition.row) && (square.column - 1 == kingPosition.column)) {
			return true;
		}
		return false;
	}

	public static boolean checkForAttackBishop(HelpBoard helpBoard, int colour, Square square, Square kingPosition) {
		boolean blocked;
		for (int i = -1; i < 2; i += 2) {
			for (int j = -1; j < 2; j += 2) {
				blocked = false;
				for (int k = 1; k < 8; k++) {
					if (!blocked) {
						if (square.row + k * i >= 0 && square.row + k * i < 8 && square.column + k * j >= 0
								&& square.column + k * j < 8) {
							if (helpBoard.board[square.row + k * i][square.column + k * j].piece != null) {
								blocked = true;
								if ((square.row + k * i == kingPosition.row)
										&& (square.column + k * j == kingPosition.column)) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean checkForAttackRook(HelpBoard helpBoard, int colour, Square square, Square kingPosition) {
		boolean blocked;
		for (int i = -1; i < 2; i += 2) {
			blocked = false;
			for (int k = 1; k < 8; k++) {
				if (!blocked) {
					if (square.row + k * i >= 0 && square.row + k * i < 8) {
						if (helpBoard.board[square.row + k * i][square.column].piece != null) {
							blocked = true;
							if ((square.row + k * i == kingPosition.row) && (square.column == kingPosition.column)) {
								return true;
							}
						}
					}
				}
			}
		}
		for (int j = -1; j < 2; j += 2) {
			blocked = false;
			for (int k = 1; k < 8; k++) {
				if (!blocked) {
					if (square.column + k * j >= 0 && square.column + k * j < 8) {
						if (helpBoard.board[square.row][square.column + k * j].piece != null) {
							blocked = true;
							if ((square.row == kingPosition.row) && (square.column + k * j == kingPosition.column)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean checkForAttackQueen(HelpBoard helpBoard, int colour, Square square, Square kingPosition) {
		boolean checkBishop = checkForAttackBishop(helpBoard, colour, new Square(square), new Square(kingPosition));
		boolean checkRook = checkForAttackRook(helpBoard, colour, new Square(square), new Square(kingPosition));
		return checkBishop || checkRook;
	}

	public static boolean checkForAttackKing(HelpBoard helpBoard, int colour, Square square, Square kingPosition) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if ((square.row + i == kingPosition.row) && (square.column + j == kingPosition.column)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param square
	 * @return {@code boolean[0]} -> is in available moves {@code boolean[1]} -> is
	 *         en passant
	 */
	public boolean[] isInAvailableMoves(Square square) {
		boolean[] result = { false, false };
		for (Move move : this.availableMoves) {
			if (square.equals(move.goal)) {
				result[0] = true;
				result[1] = move.enPassant;
				return result;
			}
		}
		return result;
	}

	public String toString() {
		return this.currentPosition.toString() + " can move to " + this.availableMoves.toString();
	}
}
