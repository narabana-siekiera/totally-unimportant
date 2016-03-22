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
		State A = new State("A", true, false);
		State B = new State("B");
		State C = new State("C", false, true);
		State D = new State("D");
		State E = new State("E", false, true);
		Transition T1 = new Transition(A, zero, B);
		Transition T2 = new Transition(A, one, C);
		Transition T3 = new Transition(B, zero, B);
		Transition T4 = new Transition(B, one, D);
		Transition T5 = new Transition(C, zero, C);
		Transition T6 = new Transition(C, one, D);
		Automaton X= new Automaton();
		X.setAlphabet(L);
		X.addState(A);
		X.addState(B);
		X.addState(C);
		X.addState(D);
		X.addTransition(T1);
		X.addTransition(T2);
		X.addTransition(T3);
		X.addTransition(T4);
		System.out.println(X.toString());
		
	}

}
