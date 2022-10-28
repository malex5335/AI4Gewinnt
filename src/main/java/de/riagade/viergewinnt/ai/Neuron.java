package de.riagade.viergewinnt.ai;

import java.util.stream.Stream;

public class Neuron {
	private final Neuron[] inputs;
	private final double bias;

	public Neuron(Neuron[] inputs, double bias) {
		this.bias = bias;
		this.inputs = inputs;
	}

	public double caculate() {
		return Stream.of(inputs)
				.mapToDouble(i -> i.caculate() * i.bias)
				.sum() + bias;
	}

}
