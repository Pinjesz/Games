package gry;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		File batch = new File("Launcher.bat");
		if (batch.exists())
			batch.delete(); // delete created file
		Console console = System.console();
		if (console == null && !GraphicsEnvironment.isHeadless()) { // create a new terminal if there is none
			String filename = App.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
			try {
				if (!batch.exists()) {
					// create a file to run in terminal
					batch.createNewFile();
					PrintWriter writer = new PrintWriter(batch);
					writer.println("@echo off");
					writer.println("java -jar " + filename);
					writer.println("exit");
					writer.flush();
					writer.close();
				}
				Runtime.getRuntime().exec("cmd /c start \"\" " + batch.getPath()); // run this file in terminal
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // if there is terminal
			// present options
			System.out.println("Choose the game:");
			System.out.println("1 -> chess");
			System.out.println("2 -> connect four");
			System.out.println("3 -> tic tac toe");

			Scanner input = new Scanner(System.in);
			int choice = 0;
			boolean loop = true;
			while (loop) {
				try {
					choice = input.nextInt();
				} catch (Exception e) {
					input.next();
				} finally {
					if (choice == 0)
						System.out.println("Try again");
					else
						loop = false;
				}
			}
			input.close();
			new Games(choice); // start new game
		}
	}
}

class Games {
	Games(int gameChoice) {
		int result;
		switch (gameChoice) {
			case 1:
				result = szachy();
				break;
			case 2:
				result = connectFour();
				break;
			case 3:
				result = ticTacToe();
				break;
			default:
				result = -1;
		}
		System.out.println("\nResult");
		System.out.println(result); // -1 -> draw, 1 -> starting player wins, 2 -> second player wins
	}

	public static int szachy() {
		gry.szachy.Game szachy = new gry.szachy.Game();
		return szachy.play();
	}

	public static int connectFour() {
		gry.connectFour.Game connectFour = new gry.connectFour.Game();
		return connectFour.play();
	}

	public static int ticTacToe() {
		gry.ticTacToe.Game ticTacToe = new gry.ticTacToe.Game();
		return ticTacToe.play();
	}
}