package de.riagade.viergewinnt;

public class PositionNichtVerfuegbarException extends Throwable {
	public PositionNichtVerfuegbarException(Throwable t) {
		super(t);
	}

	public PositionNichtVerfuegbarException(String message) {
		super(message);
	}
}
