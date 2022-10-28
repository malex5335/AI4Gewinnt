package de.riagade.viergewinnt;

import lombok.*;

import static de.riagade.viergewinnt.Eigentuemer.*;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Kernspiel {
	Eigentuemer[][] steine;
	Eigentuemer spieler;
	Eigentuemer gewinner;
	int anzahlReihen;
	int anzahlSpalten;
	int siegZahl;

	public Kernspiel() {
		setAnzahlReihen(6);
		setAnzahlSpalten(7);
		setSteine(new Eigentuemer[getAnzahlReihen()][getAnzahlSpalten()]);
		setSpieler(SPIELER_1);
		setSiegZahl(4);
		setGewinner(NIEMAND);
	}
	public void setzeStein(int spalte) throws PositionNichtVerfuegbarException {
		if(!getGewinner().equals(NIEMAND))
			throw new SpielVorbeiException(String.format("%s hat das Spiel bereits gewonnen", getGewinner()));
		for(var reihe = 1; reihe <= getAnzahlReihen(); reihe++) {
			var eigentuemer = eigentuemer(reihe, spalte);
			if(eigentuemer.equals(NIEMAND)) {
				getSteine()[reihe-1][spalte-1] = getSpieler();
				setGewinner(pruefeGewinner());
				setSpieler(naechsterSpieler());
				return;
			}
		}
		throw new PositionNichtVerfuegbarException("die Reihe ist bereits voll");
	}

	private Eigentuemer naechsterSpieler() {
		return getSpieler().equals(SPIELER_1) ? SPIELER_2 : SPIELER_1;
	}

	public Eigentuemer eigentuemer(int reihe, int spalte) throws PositionNichtVerfuegbarException {
		try {
			var eigentuemer = getSteine()[reihe - 1][spalte - 1];
			return eigentuemer != null ? eigentuemer : NIEMAND;
		} catch (IndexOutOfBoundsException e) {
			throw new PositionNichtVerfuegbarException(e);
		}
	}

	private Eigentuemer pruefeGewinner() {
		if( pruefeJeFeld()) {
			return getSpieler();
		}
		return NIEMAND;
	}

	private boolean pruefeJeFeld() {
		for(var x = 1; x <= getAnzahlReihen(); x++) {
			for(var y = 1; y <= getAnzahlSpalten(); y++) {
				if (pruefeGruppe(x, y, 1, 1) ||
						pruefeGruppe(x, y, -1, 1) ||
						pruefeGruppe(x, y, -1, -1) ||
						pruefeGruppe(x, y, 1, -1) ||
						pruefeGruppe(x, y, 0, 1) ||
						pruefeGruppe(x, y, 0, -1) ||
						pruefeGruppe(x, y, 1, 0) ||
						pruefeGruppe(x, y, -1, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean pruefeGruppe(int baseX, int baseY, int xUp, int yUp) {
		var anzahl = 0;
		while (baseX >= 1 && baseY >= 1) {
			try {
				if (getSpieler().equals(eigentuemer(baseX, baseY))) {
					if (++anzahl >= getSiegZahl()) {
						return true;
					}
				} else {
					anzahl = 0;
				}
			} catch (PositionNichtVerfuegbarException e) {
				break;
			}
			baseX += xUp;
			baseY += yUp;
		}
		return false;
	}
}
