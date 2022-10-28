package de.riagade.viergewinnt.ai;


import java.util.Arrays;

public class NeuralNetwork {

	public static void main(String[] args) {
		System.out.println("running the calculations");
		var input_1 = new InputNeuron(1, 0.01);
		var input_2 = new InputNeuron(2, 0.02);
		var input_3 = new InputNeuron(3, 0.03);
		var inputs = new Neuron[] {input_1, input_2, input_3};

		var layer_1 = createNewLayer(inputs, 0.04, 0.05, 0.06, 0.07);
		var layer_2 = createNewLayer(layer_1, 0.08, 0.09, 0.10, 0.11, 0.12);
		var layer_3 = createNewLayer(layer_2, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18, 0.19);

		var output_1 = new OutputNeuron(layer_3, 1, 0.20);
		var output_2 = new OutputNeuron(layer_3, 2, 0.21);
		var output_3 = new OutputNeuron(layer_3, 3, 0.22);

		var outputResult = getHighest(output_1, output_2, output_3);
		System.out.println("printing result:");
		System.out.printf("You should invest in %d", outputResult.action());
		System.out.println();
	}

	private static Neuron[] createNewLayer(Neuron[] inputs, double... biases) {
		return Arrays.stream(biases)
				.mapToObj(b -> new Neuron(inputs, b))
				.toArray(Neuron[]::new);
	}

	private static OutputNeuron getHighest(OutputNeuron... outputs) {
		var highestNeuron = outputs[0];
		var highestCalc = outputs[0].caculate();
		for(var o : outputs) {
			var calc = o.caculate();
			if(calc > highestCalc) {
				highestCalc = calc;
				highestNeuron = o;
			}
		}
		return highestNeuron;
	}
}
