package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
	private String name;
	private final Boolean isFinal;
	private final Map<Symbol, Set<State>> transitions = new HashMap<>();
	
	public State(String name, Boolean isInitial, Boolean isFinal){
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
