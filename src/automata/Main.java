package automata;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
		Symbol zero = new Symbol("0");
		Symbol one = new Symbol("1");
		Alphabet L = new Alphabet();
		L.addSymbol(zero);
		L.addSymbol(one);
		State A = new State("A", false);
		State B = new State("B");
		State C = new State("C", true);
		State D = new State("D");
		State E = new State("E", true);
		Automaton X= new Automaton();
		X.setAlphabet(L);
		X.addState(A);
		X.addState(B);
		X.addState(C);
		X.addState(D);
		X.addTransition(A, zero, B);
		X.addTransition(A, one, C);
		X.addTransition(B, zero, B);
		X.addTransition(B, one, D);
		System.out.println(X.toString());
	}
}
