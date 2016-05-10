package tests;

import automata.Automaton;

public class DeterminizeTest1 {
	public static void main(String[] args) {
		String regEx = "10*";
		System.out.println("Build automaton from -> " + regEx);
		Automaton A = new Automaton(regEx);
		
		System.out.println("Test A:");
		TestUtils.testAccept(A, "1");
		TestUtils.testAccept(A, "");
		TestUtils.testAccept(A, "0");
		TestUtils.testAccept(A, "10");
		TestUtils.testAccept(A, "100");
		TestUtils.testAccept(A, "1001");
		
		System.out.println("Determinize...");
		Automaton D = A.determinize();
		System.out.println("Test D:");
		TestUtils.testAccept(D, "1");
		TestUtils.testAccept(D, "");
		TestUtils.testAccept(D, "0");
		TestUtils.testAccept(D, "10");
		TestUtils.testAccept(D, "100");
		TestUtils.testAccept(D, "1001");
	}
}
