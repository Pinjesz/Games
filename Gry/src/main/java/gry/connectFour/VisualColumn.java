package gry.connectFour;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class VisualColumn extends JPanel {
	Column places; // help places
	Color backColor; // highlight
	Color activeColor; // light version of color for preview
	Color topColor; // color of current player for highlight

	public VisualColumn(HelpBoard helpBoard, int column) {
		places = helpBoard.board[column];
		backColor = VisualBoard.black;
		activeColor = VisualBoard.gray;
		topColor = VisualBoard.black;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		paintColumn(g2D);
	}

	// draw circle of given color
	public void paintCircle(int row, Graphics2D g, Color color) {
		g.setPaint(color);
		g.fillOval(10, 100 * (row + 1) + 10, 80, 80);
	}

	// draw lines between places
	public void paintLine(Graphics2D g, int row) {
		g.setPaint(Color.white);
		g.drawLine(0, 100 * (row + 1), 100, 100 * (row + 1));
	}

	// set color for background
	public void paintBackground(Graphics2D g) {
		g.setPaint(this.backColor);
		g.fillRect(0, 0, 100, 700);
	}

	/**
	 * set graphical image based on {@code places} and colors for background, top
	 * circle, preview circle
	 * 
	 * @param g
	 */
	public void paintColumn(Graphics2D g) {
		paintBackground(g);
		paintCircle(-1, g, this.topColor);
		for (int r = 0; r < 6; r++) {
			if (r != this.places.emptyPlace) {
				switch (this.places.places[r].colour) {
					case 0:
						paintCircle(r, g, VisualBoard.gray);
						break;
					case 1:
						paintCircle(r, g, VisualBoard.yellow);
						break;
					case 2:
						paintCircle(r, g, VisualBoard.red);
						break;
				}
			} else {
				paintCircle(r, g, this.activeColor);
			}
			paintLine(g, r);
		}
	}
}
