package gry.szachy;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;

public class VisualBoard {
	public JFrame frame;
	public JPanel squares;
	public JButton[][] buttons;
	public JPanel promotionPanel;
	public HelpBoard helpBoard;

	public static ImageIcon bishopBD;
	public static ImageIcon bishopBL;
	public static ImageIcon bishopWD;
	public static ImageIcon bishopWL;

	public static ImageIcon kingBD;
	public static ImageIcon kingBL;
	public static ImageIcon kingWD;
	public static ImageIcon kingWL;

	public static ImageIcon knightBD;
	public static ImageIcon knightBL;
	public static ImageIcon knightWD;
	public static ImageIcon knightWL;

	public static ImageIcon pawnBD;
	public static ImageIcon pawnBL;
	public static ImageIcon pawnWD;
	public static ImageIcon pawnWL;

	public static ImageIcon queenBD;
	public static ImageIcon queenBL;
	public static ImageIcon queenWD;
	public static ImageIcon queenWL;

	public static ImageIcon rookBD;
	public static ImageIcon rookBL;
	public static ImageIcon rookWD;
	public static ImageIcon rookWL;

	public static ImageIcon lightSquare;
	public static ImageIcon darkSquare;

	public static ArrayList<Move> moves = new ArrayList<Move>();
	public boolean noMoreMoves;

	VisualBoard(HelpBoard helpBoard) {

		// images
		VisualBoard.bishopBD = new ImageIcon(this.getClass().getResource("pieces/bishopBD.png"));
		VisualBoard.bishopBL = new ImageIcon(this.getClass().getResource("pieces/bishopBL.png"));
		VisualBoard.bishopWD = new ImageIcon(this.getClass().getResource("pieces/bishopWD.png"));
		VisualBoard.bishopWL = new ImageIcon(this.getClass().getResource("pieces/bishopWL.png"));

		VisualBoard.kingBD = new ImageIcon(this.getClass().getResource("pieces/kingBD.png"));
		VisualBoard.kingBL = new ImageIcon(this.getClass().getResource("pieces/kingBL.png"));
		VisualBoard.kingWD = new ImageIcon(this.getClass().getResource("pieces/kingWD.png"));
		VisualBoard.kingWL = new ImageIcon(this.getClass().getResource("pieces/kingWL.png"));

		VisualBoard.knightBD = new ImageIcon(this.getClass().getResource("pieces/knightBD.png"));
		VisualBoard.knightBL = new ImageIcon(this.getClass().getResource("pieces/knightBL.png"));
		VisualBoard.knightWD = new ImageIcon(this.getClass().getResource("pieces/knightWD.png"));
		VisualBoard.knightWL = new ImageIcon(this.getClass().getResource("pieces/knightWL.png"));

		VisualBoard.pawnBD = new ImageIcon(this.getClass().getResource("pieces/pawnBD.png"));
		VisualBoard.pawnBL = new ImageIcon(this.getClass().getResource("pieces/pawnBL.png"));
		VisualBoard.pawnWD = new ImageIcon(this.getClass().getResource("pieces/pawnWD.png"));
		VisualBoard.pawnWL = new ImageIcon(this.getClass().getResource("pieces/pawnWL.png"));

		VisualBoard.queenBD = new ImageIcon(this.getClass().getResource("pieces/queenBD.png"));
		VisualBoard.queenBL = new ImageIcon(this.getClass().getResource("pieces/queenBL.png"));
		VisualBoard.queenWD = new ImageIcon(this.getClass().getResource("pieces/queenWD.png"));
		VisualBoard.queenWL = new ImageIcon(this.getClass().getResource("pieces/queenWL.png"));

		VisualBoard.rookBD = new ImageIcon(this.getClass().getResource("pieces/rookBD.png"));
		VisualBoard.rookBL = new ImageIcon(this.getClass().getResource("pieces/rookBL.png"));
		VisualBoard.rookWD = new ImageIcon(this.getClass().getResource("pieces/rookWD.png"));
		VisualBoard.rookWL = new ImageIcon(this.getClass().getResource("pieces/rookWL.png"));

		VisualBoard.lightSquare = new ImageIcon(this.getClass().getResource("pieces/light.png"));
		VisualBoard.darkSquare = new ImageIcon(this.getClass().getResource("pieces/dark.png"));

		this.helpBoard = helpBoard;

		this.frame = new JFrame();
		this.frame.setSize(800, 830);
		this.frame.setResizable(false);
		this.frame.setTitle("Szachy");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setIconImage(VisualBoard.kingWD.getImage());

		// grid
		this.squares = new JPanel();
		this.squares.setLayout(new GridLayout(8, 8, 2, 2));
		this.frame.add(this.squares);

		this.buttons = new JButton[8][8];

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				this.buttons[r][c] = new JButton();
				this.squares.add(this.buttons[r][c]);
			}
		}
		this.frame.setVisible(true);
	}

	/**
	 * set button images based on {@link HelpBoard}
	 */
	public void show() {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				boolean squareColour = ((r + c) % 2 == 0); // true - light square, false - dark square
				if (this.helpBoard.board[r][c].piece == null) {
					// empty squares
					if (squareColour) {
						this.buttons[r][c].setIcon(VisualBoard.lightSquare);
						this.buttons[r][c].setDisabledIcon(VisualBoard.lightSquare);
					} else {
						this.buttons[r][c].setIcon(VisualBoard.darkSquare);
						this.buttons[r][c].setDisabledIcon(VisualBoard.darkSquare);
					}
				} else if (this.helpBoard.board[r][c].piece.colour == 1) {
					// white pieces
					switch (this.helpBoard.board[r][c].piece.kind) {
						case 1:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.pawnWL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.pawnWL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.pawnWD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.pawnWD);
							}
							break; // pawn
						case 2:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.knightWL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.knightWL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.knightWD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.knightWD);
							}
							break; // knight
						case 3:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.bishopWL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.bishopWL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.bishopWD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.bishopWD);
							}
							break; // bishop
						case 4:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.rookWL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.rookWL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.rookWD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.rookWD);
							}
							break; // rook
						case 5:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.queenWL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.queenWL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.queenWD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.queenWD);
							}
							break; // queen
						case 6:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.kingWL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.kingWL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.kingWD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.kingWD);
							}
							break; // king
					}
				} else {
					// black pieces
					switch (this.helpBoard.board[r][c].piece.kind) {
						case 1:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.pawnBL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.pawnBL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.pawnBD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.pawnBD);
							}
							break; // pawn
						case 2:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.knightBL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.knightBL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.knightBD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.knightBD);
							}
							break; // knight
						case 3:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.bishopBL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.bishopBL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.bishopBD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.bishopBD);
							}
							break; // bishop
						case 4:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.rookBL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.rookBL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.rookBD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.rookBD);
							}
							break; // rook
						case 5:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.queenBL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.queenBL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.queenBD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.queenBD);
							}
							break; // queen
						case 6:
							if (squareColour) {
								this.buttons[r][c].setIcon(VisualBoard.kingBL);
								this.buttons[r][c].setDisabledIcon(VisualBoard.kingBL);
							} else {
								this.buttons[r][c].setIcon(VisualBoard.kingBD);
								this.buttons[r][c].setDisabledIcon(VisualBoard.kingBD);
							}
							break; // king
					}
				}
			}
		}
	}

	/**
	 * set action listeners to buttons with pieces of current colour and disables
	 * those which cannot move
	 * 
	 * @param colour -> current player
	 */
	public void showAvailableToMovePieces(int colour) {
		this.show();
		boolean availableMove = false;
		SetOfMoves[][] moves = this.helpBoard.getAvailableMoves(colour);
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				this.buttons[r][c].setEnabled(false);
				this.buttons[r][c].setBorder(UIManager.getBorder("Button.border"));
				this.removeActionListeners(this.buttons[r][c]);
				if (this.helpBoard.board[r][c].piece != null) {
					if (this.helpBoard.board[r][c].piece.colour == colour) {
						if (moves[r][c].availableMoves.size() != 0) {
							availableMove = true;
							this.buttons[r][c].setEnabled(true);
							this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.green, 3));
							final int row = r;
							final int column = c;
							this.buttons[r][c].addActionListener(e -> {
								this.showAvailableNewPositions(moves[row][column], colour);
							});
						}
					}
				}
			}
		}
		if (!availableMove) {
			this.noMoreMoves = true;
		}
	}

	public void showAvailableNewPositions(SetOfMoves moves, int colour) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				this.buttons[r][c].setEnabled(false);
				this.buttons[r][c].setBorder(UIManager.getBorder("Button.border"));
				this.removeActionListeners(this.buttons[r][c]);
				boolean[] availableAndEnPassant = moves.isInAvailableMoves(this.helpBoard.board[r][c]);
				if (availableAndEnPassant[0]) {
					this.buttons[r][c].setEnabled(true);
					this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.green, 3));
					final int row = r;
					final int column = c;
					if (moves.currentPosition.piece.kind == 1 && ((colour == 1 && r == 0) || (colour == 2 && r == 7))) {
						this.buttons[r][c].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JDialog jframe = new JDialog(frame, "Promotion", true);

								jframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
								jframe.setSize(450, 230);

								JPanel panel = new JPanel();
								panel.setLayout(new BorderLayout());

								JPanel pieces = new JPanel();
								pieces.setLayout(new GridLayout(1, 1, 20, 20));

								JButton promoteToKnight = new JButton();
								JButton promoteToBishop = new JButton();
								JButton promoteToRook = new JButton();
								JButton promoteToQueen = new JButton();

								if (colour == 1) {
									promoteToKnight.setIcon(VisualBoard.knightWD);
									promoteToBishop.setIcon(VisualBoard.bishopWD);
									promoteToRook.setIcon(VisualBoard.rookWD);
									promoteToQueen.setIcon(VisualBoard.queenWD);
								} else {
									promoteToKnight.setIcon(VisualBoard.knightBL);
									promoteToBishop.setIcon(VisualBoard.bishopBL);
									promoteToRook.setIcon(VisualBoard.rookBL);
									promoteToQueen.setIcon(VisualBoard.queenBL);
								}

								promoteToKnight.addActionListener(ee -> {
									VisualBoard.makeMove(helpBoard,
											new Move(
													new Square(moves.currentPosition.row, moves.currentPosition.column,
															new Piece(colour, 2)),
													helpBoard.board[row][column], false));
									jframe.dispose();
								});

								promoteToBishop.addActionListener(ee -> {
									VisualBoard.makeMove(helpBoard,
											new Move(
													new Square(moves.currentPosition.row, moves.currentPosition.column,
															new Piece(colour, 3)),
													helpBoard.board[row][column], false));
									jframe.dispose();
								});

								promoteToRook.addActionListener(ee -> {
									VisualBoard.makeMove(helpBoard,
											new Move(
													new Square(moves.currentPosition.row, moves.currentPosition.column,
															new Piece(colour, 4)),
													helpBoard.board[row][column], false));
									jframe.dispose();
								});

								promoteToQueen.addActionListener(ee -> {
									VisualBoard.makeMove(helpBoard,
											new Move(
													new Square(moves.currentPosition.row, moves.currentPosition.column,
															new Piece(colour, 5)),
													helpBoard.board[row][column], false));
									jframe.dispose();
								});

								pieces.add(promoteToKnight);
								pieces.add(promoteToBishop);
								pieces.add(promoteToRook);
								pieces.add(promoteToQueen);

								JLabel promotionLabel = new JLabel("Promote to");
								promotionLabel.setPreferredSize(new DimensionUIResource(100, 100));
								promotionLabel.setFont(new Font(null, Font.BOLD, 50));
								promotionLabel.setVerticalAlignment(JLabel.BOTTOM);
								promotionLabel.setHorizontalAlignment(JLabel.CENTER);

								panel.add(pieces);
								panel.add(promotionLabel, BorderLayout.NORTH);

								jframe.add(panel);
								jframe.setVisible(true);
							}
						}); // promotion
					} else {
						this.buttons[r][c].addActionListener(e -> {
							VisualBoard.makeMove(this.helpBoard, new Move(moves.currentPosition,
									this.helpBoard.board[row][column], availableAndEnPassant[1]));
						});
					}
				}
				if (moves.currentPosition.column == c && moves.currentPosition.row == r) {
					this.buttons[r][c].setEnabled(true);
					this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.red, 3));
					this.buttons[r][c].addActionListener(e -> {
						showAvailableToMovePieces(colour);
					});
				}
			}
		}
	}

	public static void makeMove(HelpBoard helpBoard, Move move) {
		System.out.println(move);
		VisualBoard.moves.add(move);
	}

	public void removeActionListeners(JButton button) {
		ActionListener[] listeners = button.getActionListeners();
		for (ActionListener actionListener : listeners) {
			button.removeActionListener(actionListener);
		}
	}

	public static void presentResult(int result) {
		String message;
		if (result == 1) {
			message = "White won!";
		} else if (result == 2) {
			message = "Black won!";
		} else {
			message = "Draw";
		}
		JLabel label = new JLabel(message);
		label.setFont(new Font(null, Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		JOptionPane.showMessageDialog(null, label, "Game finished", JOptionPane.PLAIN_MESSAGE);
	}
}
