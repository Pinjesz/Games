package gry.connectFour;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VisualBoard {
	public JFrame frame;
	public VisualColumn[] columns;
	public HelpBoard helpBoard;

	// colors
	public static Color black = Color.black;
	public static Color lightBlack = new Color(80, 80, 80);
	public static Color gray = Color.gray;
	public static Color red = Color.red;
	public static Color yellow = Color.yellow;
	public static Color lightRed = new Color(255, 127, 127);
	public static Color lightYellow = new Color(200, 200, 140);

	public static ArrayList<Integer> moves = new ArrayList<Integer>();
	public static boolean noMoreMoves; // full board

	VisualBoard(HelpBoard helpBoard) {

		this.helpBoard = helpBoard;

		this.frame = new JFrame();
		this.frame.setSize(701, 738);
		this.frame.setResizable(false);
		this.frame.setTitle("Connect four");
		this.frame.setIconImage(new ImageIcon(this.getClass().getResource("icon/connectFourIcon.png")).getImage());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setBackground(Color.white);
		this.frame.setLayout(new GridLayout(1, 1, 1, 1));

		this.columns = new VisualColumn[7];

		// grid
		for (int c = 0; c < 7; c++) {
			this.columns[c] = new VisualColumn(helpBoard, c);
			this.frame.add(this.columns[c]);
		}
		this.frame.setVisible(true);
	}

	/**
	 * set mouse listeners to empty {@link VisualColumn}
	 * 
	 * @param colour -> current player
	 */
	public void showAvailableColumns(int colour) {
		for (int c = 0; c < 7; c++) {

			final int finalC = c;
			columns[finalC].repaint();
			this.removeMouseListeners(this.columns[c]);
			if (columns[finalC].places.emptyPlace != -1) {
				this.columns[c].addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						VisualBoard.makeMove(helpBoard, finalC, colour);
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						if (columns[finalC].places.emptyPlace != -1) {
							columns[finalC].backColor = VisualBoard.lightBlack;
							if (colour == 1) {
								columns[finalC].activeColor = VisualBoard.lightYellow;
								columns[finalC].topColor = VisualBoard.yellow;
							} else {
								columns[finalC].activeColor = VisualBoard.lightRed;
								columns[finalC].topColor = VisualBoard.red;
							}
							columns[finalC].repaint();
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						columns[finalC].backColor = Color.black;
						if (columns[finalC].places.emptyPlace != -1) {
							columns[finalC].activeColor = VisualBoard.gray;
						} else {
							columns[finalC].activeColor = VisualBoard.black;
						}
						columns[finalC].topColor = VisualBoard.black;
						columns[finalC].repaint();
					}
				});

				// highlight current visualColumn if is not full
				boolean unhighlight = false;																// start
				if (VisualBoard.moves.size() != 0) {
					if (VisualBoard.moves.get(VisualBoard.moves.size() - 1) == finalC) unhighlight = true;	// other
				}

				if (columns[finalC].places.emptyPlace != -1
						&& unhighlight) {
					columns[finalC].backColor = VisualBoard.lightBlack;
					if (colour == 1) {
						columns[finalC].activeColor = VisualBoard.lightYellow;
						columns[finalC].topColor = VisualBoard.yellow;
					} else {
						columns[finalC].activeColor = VisualBoard.lightRed;
						columns[finalC].topColor = VisualBoard.red;
					}
					columns[finalC].repaint();
				}
			} else { // set colors for full column
				columns[finalC].backColor = VisualBoard.black;
				columns[finalC].activeColor = VisualBoard.black;
				columns[finalC].topColor = VisualBoard.black;
				columns[finalC].repaint();
			}
		}
	}

	public static void makeMove(HelpBoard helpBoard, int column, int colour) {
		System.out.println("move: " + colour + " to column " + column);
		VisualBoard.moves.add(column);
		if (VisualBoard.moves.size() == 42) {
			VisualBoard.noMoreMoves = true;
		}
	}

	public void removeMouseListeners(JPanel panel) {
		MouseListener[] listeners = panel.getMouseListeners();
		for (MouseListener mouseListener : listeners) {
			panel.removeMouseListener(mouseListener);
		}
	}

	public static void presentResult(int result) {
		String message;
		if (result == 1) {
			message = "Yellow won!";
		} else if (result == 2) {
			message = "Red won!";
		} else {
			message = "Draw";
		}
		JLabel label = new JLabel(message);
		label.setFont(new Font(null, Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		JOptionPane.showMessageDialog(null, label, "Game finished", JOptionPane.PLAIN_MESSAGE);
	}
}
