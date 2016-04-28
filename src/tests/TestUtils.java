package tests;

import java.util.LinkedList;
import java.util.List;

import automata.Automaton;
import automata.Symbol;

public class TestUtils {
	static List<Symbol> convertWordToSymbols(String word) {
		List<Symbol> res = new LinkedList<Symbol>();
		final int len = word.length();
		for (int i = 0; i < len; i++) {
			res.add(new Symbol(String.valueOf(word.charAt(i))));
		}
		return res;
	}

	static void testAccept(Automaton automaton, String word) {
		System.out.print("Automat accepts " + word + "?... ");
		try{
			System.out.println(automaton.accepts(convertWordToSymbols(word)));
		} catch (Exception e) {
			System.out.println("ERROR");
			System.out.println(e);
		}
	}
}
