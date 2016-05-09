package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Automaton {
	// --- pola ---
	
	private Alphabet alphabet;
	private LinkedList<State> states;
	private LinkedList<State> startingStates = new LinkedList<State>();
	
	// --- konstruktory ---
	
	public Automaton(){
		alphabet=new Alphabet();
		states = new LinkedList<State>();
	}
	
	public Automaton(String regExp){
		RegExAutomaton res = new RegExAutomatonBuilder().buildAutomaton(regExp);
		states = res.states;
		startingStates.add(res.startState);
		res.finalState.markFinal();
    }
	
	// --- metody ---
	
	// publiczne

	public Automaton copy(){
		Automaton a = new Automaton();
		a.alphabet = this.alphabet;
		Map<State, State> newStates = new HashMap<State, State>();
		for(State s : this.states){
			State copyS = new State();
			if(s.isFinal())
				copyS.markFinal();
			newStates.put(s, copyS);
			a.states.add(copyS);
		}
		for(State s : this.startingStates){
			a.startingStates.add(newStates.get(s));
		}
		for(State s : this.states){
			State copyS = newStates.get(s);
			for(Symbol e : s.getOutgoingSymbols()){
				for(State to : s.getTransitions(e)){
					copyS.addTransition(e, newStates.get(to));
				}
			}
		}
		return a;
	}
	
	public void addState(State state) {
		states.add(state);
	}

	public void addTransition(State from, Symbol symbol, State to) {
		from.addTransition(symbol, to);
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	public LinkedList<State> getStates() {
		return states;
	}

	public void setStates(LinkedList<State> states) {
		this.states = states;
	}

	public Boolean isValid() {
		return new Boolean(hasInitialState() && hasFinalState() && !alphabet.isEmpty());
	}

	@Override
	public String toString() {
		String statesh = new String();
		for (State x : states) {
			statesh = new String(statesh + " " + x.toString());
		}
		String out = new String("Alphabet:" + alphabet.toString() + "\n" + "States:" + statesh + "\n");
		return out;
	}

	public boolean accepts(List<Symbol> word){
		Set<State> cur = new HashSet<State>();
		cur.addAll(startingStates);
		cur = moveEpsilon(cur);
		for(Symbol symbol : word){
			cur = move(cur, symbol);
			cur = moveEpsilon(cur);
		}
		for(State state: cur){
			if(state.isFinal()){
				return true;
			}
		}
		return false;
	}
	public Automaton complement(Automaton automaton){
		//TODO
		throw new UnsupportedOperationException();
	}
	public String getRegExp(){
		//TODO
		throw new UnsupportedOperationException();
	}
	public Automaton determinize(){
		//TODO
		throw new UnsupportedOperationException();
	}
	public Automaton minimize(){
		//TODO
		throw new UnsupportedOperationException();
	}
	
	public Automaton sum(Automaton B){
		if(B == null)
			throw new IllegalArgumentException();
		if(this.alphabet != B.alphabet) // porownujemy referencje, przyjmujemy ze automaty musza miec ten sam obiekt alfabetu
			throw new IllegalArgumentException();
		Automaton copyA = this.copy();
		Automaton copyB = B.copy();
		Automaton C = new Automaton();
		C.alphabet = this.alphabet;
		C.states.addAll(copyA.states);
		C.states.addAll(copyB.states);
		C.startingStates.addAll(copyA.startingStates);
		C.startingStates.addAll(copyB.startingStates);
		return C;
	}
	
	public Automaton product(Automaton B){
		throw new UnsupportedOperationException();
	}
	
	// prywatne

	private boolean hasInitialState(){
		return !startingStates.isEmpty();
	}

	private Boolean hasFinalState(){
		for(State state: states){
			if(state.isFinal()){
				return new Boolean(true);
			}
		}
		return new Boolean(false);
	}
	
	// - chodzenie po automacie, akceptowanie slow

	private Set<State> move(Set<State> cur, Symbol symbol){
		Set<State> result = new HashSet<State>();
		for(State state : cur){
			result.addAll(state.getTransitions(symbol));
		}
		return result;
	}
	
	private Set<State> moveEpsilon(Set<State> states) {
		Set<State> result = new HashSet<State>();
		Set<State> toAdd = new HashSet<State>();
		toAdd.addAll(states);
		while (!toAdd.isEmpty()) {
			result.addAll(toAdd);
			Set<State> nextToAdd = new HashSet<State>();
			for (State newState : toAdd) {
				Set<State> epsi = newState.getTransitions(null);
				for (State es : epsi) {
					if (!result.contains(es))
						nextToAdd.add(es);
				}
			}
			toAdd = nextToAdd;
		}
		return result;
	}
}
