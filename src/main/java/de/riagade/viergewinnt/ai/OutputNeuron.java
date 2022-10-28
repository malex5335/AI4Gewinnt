package de.riagade.viergewinnt.ai;

public class OutputNeuron extends Neuron {
	int value;

	public OutputNeuron(Neuron[] inputs, int value, double bias) {
		super(inputs, bias);
		this.value = value;
	}

	public int action() {
		return this.value;
	}
}
