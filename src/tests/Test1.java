package tests;

import automata.Automaton;

public class Test1 {

	public static void main(String[] args) {
		String regEx = "0";
		System.out.println("Build automaton from -> " + regEx);
		Automaton A = new Automaton(regEx);
		TestUtils.testAccept(A, "");
		TestUtils.testAccept(A, "0");
		TestUtils.testAccept(A, "1");
		TestUtils.testAccept(A, "01");
		TestUtils.testAccept(A, "10");
	}

}
