package fa.dfa;

import java.util.HashMap;

import fa.State;

/*  Class to represent a state in a Deterministic Finite Automaton (DFA)
*   
* @author JohannVargas
*/
public class DFAstate extends State {
    // Boolean flag to represent whether this state is a final state
    private boolean isFinal;
    // Boolean flag to represent whether this state is the start state
    private boolean isStart;
    // HashMap to store transitions for this state for each input character
    HashMap<Character, DFAstate> transition = new HashMap<Character, DFAstate>();

    /**
     * Constructor to initialize the state name
     * 
     * @param name name of the state
     */
    public DFAstate(String name) {
        super(name);
    }

    /**
     * Setter method to set the isFinal flag
     * 
     * @param isFinal the flag indicating whether this state is final
     */
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Getter method to get the isFinal flag
     * 
     * @return the flag indicating whether this state is final
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Setter method to set the isStart flag
     * 
     * @param isStart the flag indicating whether this state is start
     */
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    /**
     * Getter method to get the isStart flag
     * 
     * @return the flag indicating whether this state is start
     */
    public boolean isStart() {
        return isStart;
    }
}