package automata;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Automaton {
	// --- pola ---
	
	private Alphabet alphabet;
	private LinkedList<State> states;
	private Set<State> startingStates = new HashSet<State>();
	
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
		String out = new String("Alphabet:" + alphabet + "\n" + "States:" + statesh + "\n");
		return out;
	}
	
	public void print() {
		System.out.println("Automat (" + ((alphabet == null) ? "" : alphabet) + "):");
		for(State s : states){
			System.out.println(s.print());
		}
		System.out.println("starting: " + startingStates);
	}

	public boolean accepts(List<Symbol> word){
		Set<State> cur = new HashSet<State>();
		cur.addAll(startingStates);
		cur = moveEpsilon(cur);
		for(Symbol symbol : word){
			cur = move(cur, symbol);
			cur = moveEpsilon(cur);
		}
		return containsFinal(cur);
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
		Automaton D = new Automaton();
		D.alphabet = alphabet;
		
		Automaton A = this.copy();
		// domknij przejscia po epsilonie
		A.startingStates = A.moveEpsilon(A.startingStates);
		for(State s : A.states){
			for(Symbol e : s.getOutgoingSymbols()){
				s.addTransitions(e, A.moveEpsilon(s.getTransitions(e)));
			}
		}
		// zbuduj automat potegowy
		Queue<Set<State>> toVisit = new LinkedList<Set<State>>();
		Map<Set<State>, State> visited = new HashMap<Set<State>, State>();
		
		State startState = new State();
		toVisit.add(A.startingStates);
		visited.put(A.startingStates, startState);
		D.startingStates.add(startState);
		
		while(!toVisit.isEmpty()){
			Set<State> curSetState = toVisit.poll();
			State curState = visited.get(curSetState);
			if(A.containsFinal(curSetState))
				curState.markFinal();
			Set<Symbol> symbols = new HashSet<Symbol>();
			for(State s : curSetState){
				symbols.addAll(s.getOutgoingSymbols());
			}
			for(Symbol sym : symbols){
				if(sym == null) continue;
				Set<State> newSetState = A.move(curSetState, sym);
				if(!visited.containsKey(newSetState)){
					toVisit.add(newSetState);
					visited.put(newSetState, new State());
				}
				curState.addTransition(sym, visited.get(newSetState));
			}
		}
		D.states.addAll(visited.values());
		return D;
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
	
	private Set<State> moveEpsilon(Collection<State> states) {
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
	
	private boolean containsFinal(Set<State> states){
		for(State s: states){
			if(s.isFinal()){
				return true;
			}
		}
		return false;
	}
}
