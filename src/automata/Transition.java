package automata;

public class Transition {
	private State fromState, toState;
	private Symbol symbol;
	public Transition(State from, Symbol symbol, State to){
		this.fromState=from;
		this.symbol=symbol;
		this.toState=to;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public State getToState() {
		return toState;
	}
	public void setToState(State toState) {
		this.toState = toState;
	}
	public State getFromState() {
		return fromState;
	}
	public void setFromState(State fromState) {
		this.fromState = fromState;
	}
	public String toString(){
		return new String("("+fromState.toString()+","+symbol.toString()+","+toState.toString()+")");
	}
}
