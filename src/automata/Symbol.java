package automata;

public class Symbol {
	private String symbol;
	
	public Symbol(Symbol symbol2) {
		symbol = symbol2.getSymbol();
	}
	public Symbol(String symbol){
		this.symbol=symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}	
	@Override
	public String toString(){
		return symbol;
	}

	@Override
	public int hashCode() {
		return symbol.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Symbol))
			return false;
		if (obj == this)
			return true;
		return this.symbol.equals(((Symbol) obj).symbol);
	}
}
