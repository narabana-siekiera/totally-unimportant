package automata;

import java.util.LinkedList;

/**
 * Klasa sluzaca do reprezentowania tymczasowego automatu podczas tworzenia 
 * automatu z wyrazenia regularnego.
 */
class RegExAutomaton {
	State startState;
	State finalState;
	LinkedList<State> states = new LinkedList<State>();

	/**
	 * Stworz automat akceptujacy tylko jedno slowo - symbol a.
	 */
	RegExAutomaton(Symbol a) {
		startState = new State();
		finalState = new State();
		startState.addTransition(a, finalState);
		states.add(startState);
		states.add(finalState);
	}
	
	/**
	 * Dolacz do tego automatu podany automat.
	 */
	void concatenate(RegExAutomaton automaton) {
		finalState.addTransition(null, automaton.startState);
		states.addAll(automaton.states);
		finalState = automaton.finalState;
	}

	/**
	 * Dodaj do tego automatu podany automat, tak zeby akceptowac zlowa zarowno z jednego
	 * jak i z drugiego automatu.
	 */
	void sum(RegExAutomaton automaton) {
		State newStart = new State();
		State newFinal = new State();
		newStart.addTransition(null, this.startState);
		newStart.addTransition(null, automaton.startState);
		this.finalState.addTransition(null, newFinal);
		automaton.finalState.addTransition(null, newFinal);
		states.add(newStart);
		states.add(newFinal);
		states.addAll(automaton.states);
		startState = newStart;
		finalState = newFinal;
	}

	/**
	 * Zmien ten automat w automat akcpetujacy konkatenacje dowolnej ilosci slow z tego automatu.
	 */
	void star() {
		State newStart = new State();
		State newFinal = new State();
		newStart.addTransition(null, startState);
		newStart.addTransition(null, newFinal);
		finalState.addTransition(null, startState);
		finalState.addTransition(null, newFinal);
		states.add(newStart);
		states.add(newFinal);
		startState = newStart;
		finalState = newFinal;
	}
}
