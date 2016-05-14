package tests;

import automata.Automaton;

public class SumTest1 {

	public static void main(String[] args) {
		String regExA = "0";
		String regExB = "1";
		System.out.println("Build automaton from -> " + regExA);
		System.out.println("Build automaton from -> " + regExB);
		Automaton A = new Automaton(regExA);
		Automaton B = new Automaton(regExB);
		Automaton C = A.sum(B);
		
		System.out.println("Test A, B, C = A+B:");
		TestUtils.testWordOnAutomatons("", A, B, C);
		TestUtils.testWordOnAutomatons("0", A, B, C);
		TestUtils.testWordOnAutomatons("1", A, B, C);
		TestUtils.testWordOnAutomatons("01", A, B, C);
		TestUtils.testWordOnAutomatons("10", A, B, C);
	}

}
