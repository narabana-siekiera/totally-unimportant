package automata;

public class Symbol {
	private String symbol;

	
	public Symbol(Symbol symbol2) {
		this.setSymbol(symbol2.getSymbol());
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
	public String toString(){
		return symbol;
	}
}
