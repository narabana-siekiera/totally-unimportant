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

	/**
	 * Testuje czy automat automaton akcpetuje slowo word i wypisuje wynik. Robi 
	 * konwersje z ciagu znakow (string) do ciagu symboli (lista obiektow Symbol).
	 */
	static void testAccept(Automaton automaton, String word) {
		System.out.print("Automat accepts " + word + "?... ");
		try{
			System.out.println(automaton.accepts(convertWordToSymbols(word)));
		} catch (Exception e) {
			System.out.println("ERROR");
			System.out.println(e);
		}
	}
	
	/**
	 * Testuje czy podane slowo jest akceptowane przez podane automaty i wypisuje wyniki. 
	 * Robi konwersje z ciagu znakow (string) do ciagu symboli (lista obiektow Symbol).
	 */
	static void testWordOnAutomatons(String word, Automaton... automatons){
		try{
			List<Symbol> covertedWord = convertWordToSymbols(word);
			System.out.print("Automaton accepts " + word + "?...");
			for(Automaton a : automatons) {
				System.out.print("   " + a.accepts(covertedWord));
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("ERROR");
			System.out.println(e);
		}
	}
}
