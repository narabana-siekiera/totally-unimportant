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

	/**
	 * Porownanie czy dwa obiekty sa sobie rowne na wartosc - czyli czy maja ten sam 
	 * symbol, tj. ciag znakow jaki reprezentuja.
	 */
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
