package de.riagade.viergewinnt;

import lombok.*;

@Getter
public enum Eigentuemer {
	SPIELER_1('X'), SPIELER_2('O'), NIEMAND(' ');
	private final char value;

	Eigentuemer(char value) {
		this.value = value;
	}
}
