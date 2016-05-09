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
		
		System.out.println("Test A:");
		TestUtils.testAccept(A, "");
		TestUtils.testAccept(A, "0");
		TestUtils.testAccept(A, "1");
		TestUtils.testAccept(A, "01");
		TestUtils.testAccept(A, "10");
		
		System.out.println("Test B:");
		TestUtils.testAccept(B, "");
		TestUtils.testAccept(B, "0");
		TestUtils.testAccept(B, "1");
		TestUtils.testAccept(B, "01");
		TestUtils.testAccept(B, "10");
		
		System.out.println("Test C = A+B:");
		TestUtils.testAccept(C, "");
		TestUtils.testAccept(C, "0");
		TestUtils.testAccept(C, "1");
		TestUtils.testAccept(C, "01");
		TestUtils.testAccept(C, "10");
	}

}
