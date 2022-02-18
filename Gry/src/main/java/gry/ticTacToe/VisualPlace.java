package gry.ticTacToe;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class VisualPlace extends JPanel {
	Place place;
	public int currImage;

	public VisualPlace(HelpBoard helpBoard, int column, int row) {

		place = helpBoard.board[column][row];
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		paintPlace(g2D);
	}

	/**
	 * set graphical image based on {@code currImage}
	 * 
	 * @param g
	 */
	public void paintPlace(Graphics2D g) {
		switch (this.currImage) {
			case 0:
				g.drawImage(VisualBoard.empty, 0, 0, null);
				break;
			case 1:
				g.drawImage(VisualBoard.circle, 0, 0, null);
				break;
			case -1:
				g.drawImage(VisualBoard.lightCircle, 0, 0, null);
				break;
			case 2:
				g.drawImage(VisualBoard.cross, 0, 0, null);
				break;
			case -2:
				g.drawImage(VisualBoard.lightCross, 0, 0, null);
				break;
		}
	}
}
