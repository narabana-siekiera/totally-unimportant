package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class State {
	private static int id = 0;

	private String name;
	private Boolean isFinal;
	private final Map<Symbol, Set<State>> transitions = new HashMap<Symbol, Set<State>>();
	
	public State() {
		name = String.format("s%02d", id);
		this.isFinal = false;
		++id;
	}

	public State(String name, Boolean isFinal) {
		this.name=name;
		this.isFinal=isFinal;
	}
	public State(String name){
		this.name=name;
		this.isFinal=false;
	}
	public String getName(){
		return name;
	}
	public Boolean isFinal(){
		return isFinal;
	}

	void markFinal() {
		isFinal = true;
	}
	void addTransition(Symbol symbol, State to){
		if(!transitions.containsKey(symbol)){
			transitions.put(symbol, new HashSet<State>());
		}
		transitions.get(symbol).add(to);
	}
	Set<State> getTransitions(Symbol symbol){
		if(transitions.containsKey(symbol))
			return transitions.get(symbol);
		else
			return new HashSet<State>();
	}
	@Override
	public String toString(){
		return name+(isFinal()?"[F]":"");
	}
}
