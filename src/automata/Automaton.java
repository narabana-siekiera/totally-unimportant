package automata;

import java.util.LinkedList;

public class Automaton {
	private Alphabet alphabet;
	private LinkedList<State> states;
	private LinkedList<Transition> transitions;
	public Automaton(){
		alphabet=new Alphabet();
		states = new LinkedList<State>();
		transitions= new LinkedList<Transition>();
	}
	public void addState(State state){
		states.add(state);
	}
	public void addTransition(Transition transition){
		transitions.add(transition);
	}
	public Alphabet getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	public LinkedList<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(LinkedList<Transition> transitions) {
		this.transitions = transitions;
	}
	public LinkedList<State> getStates() {
		return states;
	}
	public void setStates(LinkedList<State> states) {
		this.states = states;
	}
	private Boolean hasInitialState(){
		for(State state : states){
			if(state.isInitial()){
				return new Boolean(true);
			}
		}
		return new Boolean(false);
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
		String transitionsh= new String();		
		for(State x : states){
			statesh= new String(statesh+" "+x.toString());
		}
		for(Transition x: transitions){
			transitionsh = new String(transitionsh+" "+x.toString());
		}
		String out = new String(
				"Alphabet:"+ alphabet.toString()+"\n"+
				"States:"+statesh+"\n"+
				"Transitions:"+transitionsh+"\n"
				);
		return out;
	}
}
