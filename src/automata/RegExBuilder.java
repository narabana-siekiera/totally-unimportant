package automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/** 
 *Klasa sluzaca do zbudowania wyrazenia regularnego z automatu
 */

public class RegExBuilder {
	private static final String epsilon= new String("\u03B5");	
	private String regEx;
	private Automaton automaton;
	private LinkedList<Equation> equations = new LinkedList<Equation>();

	
	private void equationsBuilder(){
		for(State S : automaton.getAllStates()){
			Regexp L = new Regexp("Q"+S.getName());
			Equation E = new Equation(L);
			for(Symbol Y : automaton.getAlphabet().getAlphabet()){
				for(State Z : S.getTransitions(Y)){
					Regexp R = new Regexp(Y.getSymbol()+"Q"+Z.getName());
					E.Right.add(R);
				}
			} 
			if(S.isFinal()){
				E.Right.add(new Regexp(new String(epsilon)));
			}
			
			
			
			
			equations.add(E);
		}
	}
	private void Arden(){
		for(Equation E : equations){
			
		}
	}
	
	public RegExBuilder (Automaton automaton){
		this.automaton=automaton.copy().determinize();		
		this.regEx=new String();
		this.equationsBuilder();
		
		
	}
	public String getRegEx(){
		return regEx;	
	}
	
	private class Equation{
		public Regexp Left;
		public LinkedList<Regexp> Right;
		public Equation(Regexp regexp){
			this.Left=regexp;
		}
		
	}
	private class Regexp{
		public String value;
		public Regexp(String string) {
			this.value=new String(string);
		}

		
	}
	
	
	
	
}
