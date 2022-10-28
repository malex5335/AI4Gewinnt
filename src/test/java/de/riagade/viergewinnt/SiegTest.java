package de.riagade.viergewinnt;

import org.junit.jupiter.api.Test;

import static de.riagade.viergewinnt.Eigentuemer.*;
import static org.junit.jupiter.api.Assertions.*;

class SiegTest {

	@Test
	void noch_niemand_gewonnen() {
		// Given
		var spiel = new Kernspiel();

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(NIEMAND, gewinner);
	}

	@Test
	void gewinne_vertikal() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_1, gewinner);
	}

	@Test
	void gewinne_horizontal() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(1); // Spieler 2
		spiel.setzeStein(2); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(3); // Spieler 2
		spiel.setzeStein(4); // Spieler 1

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_1, gewinner);
	}

	@Test
	void gewinne_diagonal() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(2); // Spieler 1
		spiel.setzeStein(3); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(4); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(4); // Spieler 2
		spiel.setzeStein(4); // Spieler 1
		spiel.setzeStein(6); // Spieler 2
		spiel.setzeStein(4); // Spieler 1

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_1, gewinner);
	}

	@Test
	void gewinne_diagonal_absteigend() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(4); // Spieler 1
		spiel.setzeStein(3); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(2); // Spieler 1
		spiel.setzeStein(1); // Spieler 2
		spiel.setzeStein(2); // Spieler 1
		spiel.setzeStein(1); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(5); // Spieler 2
		spiel.setzeStein(1); // Spieler 1

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_1, gewinner);
	}

	@Test
	void spieler_2_gewinne_vertikal() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(2); // Spieler 2

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_2, gewinner);
	}

	@Test
	void spieler_2_gewinne_horizontal() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(2); // Spieler 1
		spiel.setzeStein(3); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(4); // Spieler 2
		spiel.setzeStein(4); // Spieler 1
		spiel.setzeStein(5); // Spieler 2

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_2, gewinner);
	}

	@Test
	void spieler_2_gewinne_diagonal() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(2); // Spieler 1
		spiel.setzeStein(1); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(3); // Spieler 1
		spiel.setzeStein(3); // Spieler 2
		spiel.setzeStein(4); // Spieler 1
		spiel.setzeStein(4); // Spieler 2
		spiel.setzeStein(4); // Spieler 1
		spiel.setzeStein(4); // Spieler 2

		// When
		var gewinner = spiel.getGewinner();

		// Then
		assertEquals(SPIELER_2, gewinner);
	}

	@Test
	void kein_zug_nach_sieg_erlaubt() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1
		spiel.setzeStein(2); // Spieler 2
		spiel.setzeStein(1); // Spieler 1

		// When
		assertThrows(SpielVorbeiException.class, () -> spiel.setzeStein(2)); // Spieler 2

		// Then
		var gewinner = spiel.getGewinner();
		assertEquals(SPIELER_1, gewinner);
	}
}
