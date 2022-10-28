package de.riagade.viergewinnt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SpielzugTest {

	@Test
	void kein_stein_in_reihe() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();

		// When
		spiel.setzeStein(2);

		// Then
		var eingentuemer = spiel.eigentuemer(1,2);
		assertEquals(Eigentuemer.SPIELER_1, eingentuemer);
	}

	@Test
	void stein_bereits_vorhanden() throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();
		spiel.setzeStein(2);

		// When
		spiel.setzeStein(2);

		// Then
		var eingentuemer_vorher = spiel.eigentuemer(1,2);
		var eingentuemer = spiel.eigentuemer(2,2);
		assertEquals(Eigentuemer.SPIELER_1, eingentuemer_vorher);
		assertEquals(Eigentuemer.SPIELER_2, eingentuemer);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
	void keine_felder_nebendran_gesetzt(int spalte) throws PositionNichtVerfuegbarException {
		// Given
		var spiel = new Kernspiel();

		// When
		spiel.setzeStein(spalte);

		// Then
		for(var r = 1; r <= spiel.getAnzahlReihen(); r++) {
			for(var s = 1; s <= spiel.getAnzahlSpalten(); s++) {
				var eingentuemer = spiel.eigentuemer(r,s);
				if(s == spalte && r == 1) {
					assertEquals(Eigentuemer.SPIELER_1, eingentuemer);
				} else {
					assertEquals(Eigentuemer.NIEMAND, eingentuemer);
				}
			}
		}
	}
}
