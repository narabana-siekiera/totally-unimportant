package automata;

public class State {
	private String name;
	private final Boolean isInitial;
	private final Boolean isFinal;
	
	public State(String name, Boolean isInitial, Boolean isFinal){
		this.name=name;
		this.isFinal=isFinal;
		this.isInitial=isInitial;
	}
	public State(String name){
		this.name=name;
		this.isFinal=false;
		this.isInitial=false;
	}
	public String getName(){
		return name;
	}
	public Boolean isInitial(){
		return isInitial;
	}
	public Boolean isFinal(){
		return isFinal;
	}
	public String toString(){
		return name+(isInitial()?"[S]":"")+(isFinal()?"[F]":"");
	}
}
