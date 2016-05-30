package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Shrinker {
Automaton automaton;
LinkedList<Transition> T ;
LinkedList<State> newStates;

public Shrinker(Automaton automaton){
	this.automaton=automaton.copy().determinize();
	T= new LinkedList<Transition>();
}
	public Automaton minimize(){	
	//Automaton kopia = this.automaton.copy();
	for(State S : automaton.getAllStates()){
		Boolean Sstart = S.isStart();
		Boolean Sfinal = S.isFinal();
		if(Sstart && !Sfinal){
			S.markFinal();
			S.toggleStart();
		}
		else if(!Sstart && Sfinal){
			S.markStart();
			S.toggleFinal();
		}
		for(Symbol Y : automaton.getAlphabet().getAlphabet()){
			Set<State> N = new HashSet<State>();
			N=S.getTransitions(Y);
			for(State U : N){				
				T.add(new Transition(U, Y, S));
			}
		}
		
	}
	for(State S : automaton.getAllStates()){
		S.clearTransitions();
	}
	for(Transition R : T){
		automaton.addTransition(R.from, R.symbol, R.to);
	}
	
	automaton.determinize();
	
	for(State S : automaton.getAllStates()){
		Boolean Sstart = S.isStart();
		Boolean Sfinal = S.isFinal();
		if(Sstart && !Sfinal){
			S.markFinal();
			S.toggleStart();
		}
		else if(!Sstart && Sfinal){
			S.markStart();
			S.toggleFinal();
		}
		for(Symbol Y : automaton.getAlphabet().getAlphabet()){
			Set<State> N = new HashSet<State>();
			N=S.getTransitions(Y);
			for(State U : N){				
				T.add(new Transition(U, Y, S));
			}
		}
		
	}
	for(State S : automaton.getAllStates()){
		S.clearTransitions();
	}
	for(Transition R : T){
		automaton.addTransition(R.from, R.symbol, R.to);
	}
	
	//throw new UnsupportedOperationException();
	return automaton;
}


	private class Transition{
		public State from ;
		public Symbol symbol;
		public State to;
		
		public Transition(State F, Symbol S, State T){
			this.from=F;
			this.symbol=S;
			this.to=T;
		}
	}
	
	
}
