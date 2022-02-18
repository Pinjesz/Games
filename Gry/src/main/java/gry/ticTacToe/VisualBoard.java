package gry.ticTacToe;

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
import java.awt.Image;

public class VisualBoard {
	public JFrame frame;
	public HelpBoard helpBoard;
	public VisualPlace[][] places;

	public static Image cross;
	public static Image lightCross;
	public static Image circle;
	public static Image lightCircle;
	public static Image empty;

	public static ArrayList<Place> moves = new ArrayList<Place>();
	public static boolean noMoreMoves; // full board

	VisualBoard(HelpBoard helpBoard) {

		this.helpBoard = helpBoard;

		// images
		VisualBoard.cross = new ImageIcon(this.getClass().getResource("pieces/cross.png")).getImage();
		VisualBoard.lightCross = new ImageIcon(this.getClass().getResource("pieces/lightCross.png")).getImage();
		VisualBoard.circle = new ImageIcon(this.getClass().getResource("pieces/circle.png")).getImage();
		VisualBoard.lightCircle = new ImageIcon(this.getClass().getResource("pieces/lightCircle.png")).getImage();
		VisualBoard.empty = new ImageIcon(this.getClass().getResource("pieces/empty.png")).getImage();

		this.frame = new JFrame();
		this.frame.setSize(620, 650);
		this.frame.setResizable(false);
		this.frame.setTitle("Tic Tac Toe");
		this.frame.setIconImage(new ImageIcon(this.getClass().getResource("icon/ticTacToeIcon.png")).getImage());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setBackground(Color.black);
		this.frame.setLayout(new GridLayout(3, 3, 10, 10));

		this.places = new VisualPlace[3][3];

		// grid
		for (int c = 0; c < 3; c++) {
			for (int r = 0; r < 3; r++) {
				this.places[c][r] = new VisualPlace(helpBoard, c, r);
				this.frame.add(this.places[c][r]);
			}
		}
		this.frame.setVisible(true);
	}

	/**
	 * set mouse listeners to empty {@link VisualPlace}
	 * 
	 * @param sign -> current player
	 */
	public void showAvailablePlaces(int sign) {
		for (int c = 0; c < 3; c++) {
			for (int r = 0; r < 3; r++) {
				final int finalC = c;
				final int finalR = r;
				this.removeMouseListeners(this.places[c][r]); // remove listeners from previous round
				this.places[finalC][finalR].addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if (places[finalC][finalR].place.sign == 0) {
							places[finalC][finalR].place.sign = sign;
							places[finalC][finalR].currImage = sign;
							VisualBoard.makeMove(helpBoard, places[finalC][finalR].place);
							places[finalC][finalR].repaint();
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						if (places[finalC][finalR].place.sign == 0) {
							if (sign == 1) {
								places[finalC][finalR].currImage = -1;
							} else {
								places[finalC][finalR].currImage = -2;
							}
							places[finalC][finalR].repaint();
						}
					}

					@Override
					public void mouseExited(MouseEvent e) {
						if (places[finalC][finalR].place.sign == 0) {
							places[finalC][finalR].currImage = 0;
						}
						places[finalC][finalR].repaint();
					}
				});
			}
		}
	}

	public static void makeMove(HelpBoard helpBoard, Place place) {
		System.out.println("move: " + place.toString());
		VisualBoard.moves.add(place);
		if (VisualBoard.moves.size() == 9) {
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
			message = "Circle won!";
		} else if (result == 2) {
			message = "Cross won!";
		} else {
			message = "Draw";
		}
		JLabel label = new JLabel(message);
		label.setFont(new Font(null, Font.BOLD, 50));
		label.setHorizontalAlignment(JLabel.CENTER);
		JOptionPane.showMessageDialog(null, label, "Game finished", JOptionPane.PLAIN_MESSAGE);
	}
}
