package de.riagade.viergewinnt.console;

import de.riagade.viergewinnt.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		var game = new Kernspiel();
		var infoMessage = "";
		while(game.getGewinner().equals(Eigentuemer.NIEMAND)){
			drawGame(game);
			if(!infoMessage.isEmpty()) {
				System.out.println(infoMessage);
				infoMessage = "";
			}
			System.out.printf("%s, wo möchtest du einen Stein setzen? ", game.getSpieler());
			try {
				var input = askForInput();
				var min = 1;
				var max = game.getAnzahlSpalten();
				game.setzeStein(Math.min(Math.max(input, min),max));
			} catch (PositionNichtVerfuegbarException e) {
				infoMessage = "Du kannst diese Position nicht bedienen, wähle eine neue aus!";
			}
		}
		drawGame(game);
		System.out.printf("%s hat das Spiel gewonnen.", game.getGewinner());
	}

	private static int askForInput() {
		var scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	private static void drawGame(Kernspiel game) {
		System.out.println();
		for(var x = game.getAnzahlReihen(); x >= 1; x--) {
			var line = new StringBuilder(" |");
			for(var y = 1; y <= game.getAnzahlSpalten(); y++) {
				try {
					line.append(String.format("%s|", game.eigentuemer(x, y).getValue()));
				} catch (PositionNichtVerfuegbarException e) {
					break;
				}
			}
			System.out.println(line);
		}
		var indicator = new StringBuilder("/ ");
		for(var x = 1; x <= game.getAnzahlSpalten(); x++) {
			indicator.append(String.format("%d ",x));
		}
		indicator.append("\\");
		System.out.println(indicator);
	}
}
