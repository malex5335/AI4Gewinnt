package de.riagade.viergewinnt.ai;

public class InputNeuron extends Neuron {
	double value;

	public InputNeuron(int value, double bias) {
		super(null, bias);
		this.value = value;
	}
	@Override
	public double caculate() {
		return value;
	}
}
