package tests;

import automata.Automaton;

public class DeterminizeTest1 {
	public static void main(String[] args) {
		String regEx = "10*";
		System.out.println("Build automaton from \"" + regEx + "\"");
		Automaton A = new Automaton(regEx);
		System.out.println("Determinize...");
		Automaton D = A.determinize();
		
		System.out.println("\nTest A and D:");
		TestUtils.testWordOnAutomatons("1", A, D);
		TestUtils.testWordOnAutomatons("", A, D);
		TestUtils.testWordOnAutomatons("0", A, D);
		TestUtils.testWordOnAutomatons("10", A, D);
		TestUtils.testWordOnAutomatons("100", A, D);
		TestUtils.testWordOnAutomatons("1001", A, D);
	}
}
