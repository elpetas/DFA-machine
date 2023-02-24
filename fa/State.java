package fa;

public abstract class State {
	private String name;
	
	public State() {
		
	}
	public State(String name) {
		this.name = name;
	}
	
	/**
	 * getter for the string label
	 * @return returns the state label.
	 */
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}

	
}
