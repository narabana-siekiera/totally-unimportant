package automata;

import java.util.LinkedList;

public class Alphabet {
	private LinkedList<Symbol> alphabet;
	public Alphabet(LinkedList<Symbol> alphabet){
		this.alphabet=alphabet;
	}
	public Alphabet(){
		this.alphabet= new LinkedList<Symbol>();
	}
	public void addSymbol(Symbol symbol){
		this.alphabet.add(new Symbol(symbol));
	}
	public void removeSymbol(Symbol symbol){
		if(this.alphabet.contains(symbol)){
			this.alphabet.remove(symbol);
		}
	}
	public LinkedList<Symbol> getAlphabet(){
		return alphabet;
	}
	public Boolean isEmpty(){
		return new Boolean(alphabet.isEmpty());
	}
	public String toString(){
		String out = new String();
		for(Symbol x: alphabet){
			out=new String(out+" "+x);
		}
		return out;
	}
	
}
