package automata;

import java.util.HashMap;
import java.util.Map;

public class State {
	private String name;
	private final Boolean isFinal;
	private final Map<Symbol, State> transitions = new HashMap<>();
	
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
		transitions.put(symbol, to);
	}
	@Override
	public String toString(){
		return name+(isFinal()?"[F]":"");
	}
}
