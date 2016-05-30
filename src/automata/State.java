package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class State {
	private static int id = 0;

	private String name;
	private Boolean isFinal;
	private Boolean isStart;
	private final Map<Symbol, Set<State>> transitions = new HashMap<Symbol, Set<State>>();
	
	public State() {
		name = String.format("s%02d", id);
		this.isFinal = false;
		this.isStart = false;
		++id;
	}

	public State(String name, Boolean isFinal) {
		this.name=name;
		this.isFinal=isFinal;
		this.isStart=false;
	}
	public State (String name, Boolean isFinal, Boolean isStart){
		this.name=name;
		this.isFinal=isFinal;
		this.isStart=isStart;
	}
	public State(String name){
		this.name=name;
		this.isFinal=false;
		this.isStart= false;
	}
	public String getName(){
		return name;
	}
	public Boolean isFinal(){
		return isFinal;
	}
	public Boolean isStart(){
		return isStart;
	}

	void markFinal() {
		isFinal = true;
	}
	void markStart(){
		isStart = true;
	}
	void toggleFinal(){
		isFinal=!isFinal;
	}
	void toggleStart(){
		isStart=!isStart;
	}
	void addTransition(Symbol symbol, State to){
		if(!transitions.containsKey(symbol)){
			transitions.put(symbol, new HashSet<State>());
		}
		transitions.get(symbol).add(to);
	}
	void addTransitions(Symbol symbol, Set<State> toStates){
		if(!transitions.containsKey(symbol)){
			transitions.put(symbol, new HashSet<State>());
		}
		transitions.get(symbol).addAll(toStates);
	}
	Set<State> getTransitions(Symbol symbol){
		if(transitions.containsKey(symbol))
			return transitions.get(symbol);
		else
			return new HashSet<State>();
	}
	Map<Symbol, Set<State>> getTransitionsMap(){
		return transitions;
	}
	Set<Symbol> getOutgoingSymbols(){
		Set<Symbol> outSymbols = new HashSet<Symbol>();
		outSymbols.addAll(transitions.keySet());
		return outSymbols;
	}
	@Override
	public String toString(){
		return name+(isFinal()?"[F]":"");
	}
	
	public String print(){
		return " " + this + " -> " + transitions;
	}
	public void clearTransitions(){
		transitions.clear();
	}
}
