package automata;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Klasa reprezentujaca niedeterministyczny automat skonczony. Dopuszczamy puste przejscia.
 */
public class Automaton {
	// --- pola ---
	private String name;
	private Alphabet alphabet;
	private LinkedList<State> states; // wszystkie stany automatu, lacznie ze startowymi!
	private Set<State> startingStates; // referencje do stanow startowych - podzbior listy states
	
	// --- konstruktory ---
	
	public Automaton(){
		alphabet=new Alphabet();
		states = new LinkedList<State>();
		startingStates = new HashSet<State>();
	}
	
	/**
	 * Tworzy automat z podanego wyrazenia regularnego, tj. automat akceptujacy slowa
	 * nalezace do jezyka zdefiniowanego przez podane wyrazenie regularne.
	 * @param regExp wyrazenie regularne
	 */
	public Automaton(String regExp){
		alphabet=new Alphabet();
		RegExAutomaton res = new RegExAutomatonBuilder().buildAutomaton(regExp);
		states = res.states;
		startingStates = new HashSet<State>();
		startingStates.add(res.startState);
		res.finalState.markFinal();
    }
	
	// --- metody ---
	
	// publiczne

	/**
	 * Zwraca kopie automatu. Kopia posiada swoje wlasne stany, dalsze modyfikacje automatow nie maja 
	 * wplywu na siebie. Jedynie obiekt alfabetu jest wspoldzielony (przepisana referencja).
	 * @return kopia automatu
	 */
	public Automaton copy(){
		Automaton a = new Automaton();
		// kopia alfabetu - przepisz referencje
		a.alphabet = this.alphabet;
		//kopia nazwy
		a.name=this.name +"kopia";
		// kopia stanow - mapuj kazdy stan w nowy stan
		Map<State, State> newStates = new HashMap<State, State>();
		for(State s : this.states){
			State copyS = new State(); // utworz kopie dla stanu s
			if(s.isFinal())
				copyS.markFinal(); // jesli s byl stanem koncowym, ustaw by kopia rowniez byla
			newStates.put(s, copyS); // dodaj mapowanie s -> kopia s
			a.states.add(copyS); // dodaj kopie s do kopii automatu
		}
		// przepisz zbior stanow startowych
		for(State s : this.startingStates){ 
			a.startingStates.add(newStates.get(s)); // kopia stanu startowego jest stanem startowym
		}
		// kopiuj przejscia pomiedzy stanami
		for(State s : this.states){ // przegladaj wszystkie stany
			State copyS = newStates.get(s);
			for(Symbol e : s.getOutgoingSymbols()){ // przegladaj wszystkie wychodzace krawedzie
				for(State to : s.getTransitions(e)){ // dla kazdego stanu docelowego
					copyS.addTransition(e, newStates.get(to)); // dodaj przejscie pomiedzy kopiami stanow
				}
			}
		}
		return a;
	}
	
	public void addState(State state) {
		states.add(state);
		if(state.isStart()){
			startingStates.add(state);
		}
	}

	public void addTransition(State from, Symbol symbol, State to) {
		from.addTransition(symbol, to);
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public LinkedList<State> getStates() {
		return states;
	}
	public LinkedList<State> getAllStates(){
		LinkedList<State> states = new LinkedList<State>();
		for(State S : this.states){
			states.add(S);
		}
		for(State S: startingStates){
			states.add(S);
		}
		return states;
	}
	public void setStates(LinkedList<State> states) {
		this.states = states;
	}

	public Boolean isValid() {
		return new Boolean(hasInitialState() && hasFinalState() && !alphabet.isEmpty());
	}

	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * Wypisz automat.
	 */
	public String getPrint() {
		StringBuilder res = new StringBuilder();
		res.append("Automat (" + ((alphabet == null) ? "" : alphabet) + "):");
		res.append("\n");
		for(State s : states){
			res.append(s.getPrint());
			res.append("\n");
		}
		res.append("starting: " + startingStates);
		res.append("\n");
		return res.toString();
	}
	
	/**
	 * Wypisz automat.
	 */
	public void print() {
		System.out.println(getPrint());
	}
	
	/**
	 * Sprawdza czy automat akceptuje podane slowo. Slowo nad alfabetem zlozonym z obiektow
	 * klasy Symbol, to lista obiektow klasy Symbol.
	 * @param word slowo do zaakceptowania
	 * @return czy automat akceptuje czy nie
	 */
	public boolean accepts(List<Symbol> word){
		Set<State> cur = new HashSet<State>();
		cur.addAll(startingStates); // zaczynamy od stanow startowych
		cur = moveEpsilon(cur); // dopelniamy przejscia "pustym" symbolem
		for(Symbol symbol : word){ // dla kazdej literki ze slowa
			cur = move(cur, symbol); // wykonaj przejscie
			cur = moveEpsilon(cur); // dopelnij je "pustymi" przejsciami
		}
		return containsFinal(cur); // sprawdz czy doszlismy do jakiegos stanu koncowego
	}
	/**
	 * zwraca automat bedacy dopelnieniem argumentu
	 * @param automaton
	 * @return automaton - dopelnienie argumentu
	 */
	public Automaton complement(Automaton automaton){
		Automaton L = automaton.copy();
		Automaton M = new Automaton();
		M.setAlphabet(L.getAlphabet());
		
		for(State x: L.getAllStates()){
			x.toggleFinal();
			M.addState(x);
		}
		return M;
	}
	public String getRegExp(){
		RegExBuilder R= new RegExBuilder (this);
		return R.getRegEx();
		//throw new UnsupportedOperationException();
	}
	/**
	 * Zwraca automat, ktory odpowiada zdeterminizowanemu automatowi. Zdeterminizowany auomat
	 * akceptuje te same slowa, ale nie ma "pustych przejsc" (null) i kazda krawedz prowadzi tylko
	 * do jednego innego stanu. Funkcja nie modyfikuje tego automatu.
	 * @return automat po determinizacji
	 */
	public Automaton determinize(){
		Automaton D = new Automaton(); // automat wynikowy - po determinizacji
		D.alphabet = alphabet;
		
		Automaton A = this.copy(); // dla celow pomocniczych tworzymy kopie automatu
		// domknij przejscia po epsilonie
		A.startingStates = A.moveEpsilon(A.startingStates); // domknij zbior startowy
		for(State s : A.states){ // dla kazdego stanu
			for(Symbol e : s.getOutgoingSymbols()){ // i kazdej krawedzi wychodzacej
				s.addTransitions(e, A.moveEpsilon(s.getTransitions(e))); // domknij przejscie po tej krawedzi
			}
		}
		// -----------------------
		// zbuduj automat potegowy - stanem w tym automacie jest podzbior stanow (stad Set<State>>)
		Queue<Set<State>> toVisit = new LinkedList<Set<State>>(); // nowe stany, do odwiedzenia
		Map<Set<State>, State> visited = new HashMap<Set<State>, State>(); // mapowanie podzbior stanow, stan w automacie potegowym
		
		// > mapuj stany startowe w stan startowy w automacie potegowym
		State startState = new State();
		toVisit.add(A.startingStates);
		visited.put(A.startingStates, startState);
		D.startingStates.add(startState);
		
		// > konstruuj automat potegowy, chodzac po tworzonyh stanach
		while(!toVisit.isEmpty()){
			Set<State> curSetState = toVisit.poll(); // rozpatrujemy ten stan (podzbior stanow)
			State curState = visited.get(curSetState); // pobieramy odpowiadajacy mu obiekt stanu w D
			D.states.add(curState); // dodajemy go do listy stanow w D
			if(A.containsFinal(curSetState)) // jesli stan zawiera ktorys ze stanow koncowych, to jest koncowy
				curState.markFinal();
			// sprawdz jakie krawedzie (symbole) wychodza z tego zbioru stanow
			Set<Symbol> symbols = new HashSet<Symbol>();
			for(State s : curSetState){
				symbols.addAll(s.getOutgoingSymbols()); // po prostu dodaj krawedzie wychodzace z kazdego stanu
			}
			// dodaj krawedzie w automacie D
			for(Symbol sym : symbols){
				if(sym == null) continue; // puste przejscia pomijamy
				Set<State> newSetState = A.move(curSetState, sym); // przechodzimy ze zbioru stanow krawedzia sym
				if(!visited.containsKey(newSetState)){ // patrzmy czy juz "odwiedzilismy" taki podzbior stanow
					toVisit.add(newSetState); // nie to zapamietuejmy go i tworzymy nowy stan w automacie D
					visited.put(newSetState, new State());
				}
				curState.addTransition(sym, visited.get(newSetState)); /// dodajemy krawedz w D
			}
		}
		return D;
	}
	public Automaton minimize(){
		Shrinker S = new Shrinker(this);
		return S.minimize();		
	}
	/**
	 * Zwraca sume tego automatu z podanym automatem B. Suma automatow, to automat akceptujacy slowa
	 * rozpoznawane przez dowolny z tych dwoch automatow. Ten automat nie zostaje zmodyfikowany.
	 * @param B automat, ktory ma zostac dodany do tego automatu
	 * @return automat bedacy suma tego i podanego automatu
	 */
	public Automaton sum(Automaton B){
		/// sprawdz poprawnosc argumentu B
		if(B == null)
			throw new IllegalArgumentException();
		if(this.alphabet != B.alphabet) // porownujemy referencje, przyjmujemy ze automaty musza miec ten sam obiekt alfabetu
			throw new IllegalArgumentException();
		// utworz kopie automatow - kopiujemy po to, zeby utworzony automat byl niezalezny od automatow z ktorych powstal
		Automaton copyA = this.copy();
		Automaton copyB = B.copy();
		// utworz automat bedacy polaczeniem A i B
		Automaton C = new Automaton();
		C.alphabet = this.alphabet;
		C.states.addAll(copyA.states); // dodaj stany z A (tego automatu)
		C.states.addAll(copyB.states); // dodaj stany z B
		C.startingStates.addAll(copyA.startingStates); // stanami startowymi sa te zarowno z A
		C.startingStates.addAll(copyB.startingStates); // jak i z B
		return C;
	}
	/**
	 * Zwraca automat bedacy przecieciem tego automatu z automatem B
	 * dziala indentycznie jak suma oprocz tego ze jest przecieciem :D
	 * @param B
	 * @return
	 */
	public Automaton product(Automaton B){
		if(B == null)
			throw new IllegalArgumentException();
		if(this.alphabet != B.alphabet) // porownujemy referencje, przyjmujemy ze automaty musza miec ten sam obiekt alfabetu
			throw new IllegalArgumentException();
		Automaton L = this.copy();
		Automaton M = B.copy();		
		L= complement(L);
		M= complement(M);
		L.sum(M);
		L=complement(L);
		return L;		
	}
	
	// prywatne

	private boolean hasInitialState(){
		return !startingStates.isEmpty();
	}

	private Boolean hasFinalState(){
		for(State state: states){
			if(state.isFinal()){
				return new Boolean(true);
			}
		}
		return new Boolean(false);
	}
	
	// - chodzenie po automacie, akceptowanie slow

	/**
	 * Funkcja przejscia dla podzbioru stanow.
	 * @param cur podzbior stanow w ktowych jestesmy
	 * @param symbol symbol ktorym idziemy
	 * @return podzbior stanow w ktorych znajdziemy sie, zaczynajac z cur i idac krawedzia cur
	 */
	private Set<State> move(Set<State> cur, Symbol symbol){
		Set<State> result = new HashSet<State>(); // zbior wynikowy
		for(State state : cur){ // przegladnij stany w ktorych jestesmy
			result.addAll(state.getTransitions(symbol)); // zsumuj stany do ktorych dojdziemy - dzieki HashSet nie bedzie duplikatow
		}
		return result;
	}
	
	/**
	 * Funkcja przejscia dla symbolu "pustego" (reprezentowanego przez null). Funkcja ta rozni sie od zwyczajnej
	 * funkcji przejscia, ze mozemy krawedzia null przejs kilka razy.
	 * @param states stany w ktorych jestesmy
	 * @return stany do ktorych mozemy dojsc z states, uzywajac wielokrotnie (badz wcale) krawedzi typu null
	 */
	private Set<State> moveEpsilon(Collection<State> states) {
		Set<State> result = new HashSet<State>(); // stany osiagalne - zbior wynikowy
		Set<State> toAdd = new HashSet<State>(); // nowe stany, do ktorych dotarlismy w danej iteracji
		toAdd.addAll(states); // zaczynamy od stanow w ktorych jestesmy
		while (!toAdd.isEmpty()) { // dopoki sa nowe stany, przetwarzajmy je
			result.addAll(toAdd); // dodajemy wszystkie te stany
			Set<State> nextToAdd = new HashSet<State>(); // spamietujemy nowe stany do odwiedzenia
			for (State newState : toAdd) { // przegladamy dodane stany
				Set<State> epsi = newState.getTransitions(null); // przechodzimy krawedzia null z tego tanu
				for (State es : epsi) {
					if (!result.contains(es)) // jesli mozemy dojsc do stanu w jakim jeszcze nie bylismy,, to go dodajemy
						nextToAdd.add(es);
				}
			}
			toAdd = nextToAdd;
		}
		return result;
	}
	
	/**
	 * Funkcja pomocnicza, sprawdza czy w zbiorze stanow jest stan koncowy (akceptujacy).
	 * @param states zbior stanow
	 * @return czy ktorys ze stanow jest akceptujacy
	 */
	private boolean containsFinal(Set<State> states){
		for(State s: states){
			if(s.isFinal()){
				return true;
			}
		}
		return false;
	}
}
