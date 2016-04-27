package automata;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Automaton {
	private Alphabet alphabet;
	private LinkedList<State> startingStates = new LinkedList<>();
	private LinkedList<State> states;
	public Automaton(){
		alphabet=new Alphabet();
		states = new LinkedList<State>();
	}
	public void addState(State state){
		states.add(state);
	}
	public void addTransition(State from, Symbol symbol, State to){
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
	public Boolean isValid(){
		return new Boolean(hasInitialState() && hasFinalState() && !alphabet.isEmpty());
	}
	public String toString(){
		String statesh = new String();
		for(State x : states){
			statesh= new String(statesh+" "+x.toString());
		}
		String out = new String(
				"Alphabet:"+ alphabet.toString()+"\n"+
				"States:"+statesh+"\n"
				);
		return out;
	}
	
	private Set<State> move(Set<State> cur, Symbol symbol){
		Set<State> result = new HashSet<>();
		for(State state : cur){
			result.addAll(state.getTransitions(symbol));
		}
		return result;
	}
	
	public boolean accepts(List<Symbol> word){
		Set<State> cur = new HashSet<>();
		cur.addAll(startingStates);
		cur = move(cur, null);
		for(Symbol symbol : word){
			cur = move(cur, symbol);
			cur = move(cur, null);
		}
		for(State state: cur){
			if(state.isFinal()){
				return true;
			}
		}
		return false;
	}
	public Automaton dopelnienie(Automaton automaton){
		//TODO
		throw new UnsupportedOperationException();
	}
	public String getRegExp(){
		//TODO
		throw new UnsupportedOperationException();
	}
	public Automaton(String regExp){
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
	public void addAutomaton(){
		//TODO
		throw new UnsupportedOperationException();
	}
}
