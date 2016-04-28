package tests;

import automata.Automaton;

public class Test3 {

	public static void main(String[] args) {
		String regEx = "10*";
		System.out.println("Build automaton from -> " + regEx);
		Automaton A = new Automaton(regEx);
		TestUtils.testAccept(A, "1");
		TestUtils.testAccept(A, "");
		TestUtils.testAccept(A, "0");
		TestUtils.testAccept(A, "10");
		TestUtils.testAccept(A, "100");
		TestUtils.testAccept(A, "1001");
	}

}
