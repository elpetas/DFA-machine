
package fa.dfa;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
*   
* @author JohannVargas
*/
public class DFA {
    private Set<Character> sigma; // set of all symbols in the alphabet of the DFA
    private Set<DFAstate> states; // set of all states in the DFA
    private Set<DFAstate> finalStates; // set of all final states in the DFA
    private DFAstate startState; // the start state of the DFA

    /**
     * Constructor for DFA class, initializes sigma, states, finalStates
     */
    public DFA() {
        sigma = new HashSet<Character>();
        states = new HashSet<DFAstate>();
        finalStates = new HashSet<DFAstate>();
    }

    /**
     * Adds a state to the DFA
     * 
     * @param name The name of the state
     * @return boolean indicating if the state was successfully added
     */
    public boolean addState(String name) {
        DFAstate newState = new DFAstate(name);
        if (containsState(name)) {
            return false;
        } else {
            states.add(newState);
            return true;
        }
    }

    /**
     * Sets a state as a final state in the DFA
     * 
     * @param name The name of the state
     * @return boolean indicating if the state was successfully set as final
     */
    public boolean setFinal(String name) {
        DFAstate state = getState(name);
        if (containsState(name)) {
            state.setFinal(true);
            finalStates.add(state);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets a state as the start state in the DFA
     * 
     * @param name The name of the state
     * @return boolean indicating if the state was successfully set as the start
     *         state
     */
    public boolean setStart(String name) {
        DFAstate state = getState(name);
        if (containsState(name)) {
            if (startState != null) {
                startState.setStart(false);
            }
            state.setStart(true);
            startState = state;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a symbol to the alphabet of the DFA
     * 
     * @param symbol the symbol to be added to the alphabet
     */
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    /**
     * Gets the alphabet of the DFA
     * 
     * @return set of characters representing the alphabet of the DFA
     */
    public Set<Character> getSigma() {
        return sigma;
    }

    /**
     * Determines if a state is the start state in the DFA
     * 
     * @param name The name of the state
     * @return boolean indicating if the state is the start state
     */
    public boolean isStart(String name) {
        if (startState.getName().equals(name)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if a state is final
     *
     * @param name name of the state to be checked
     * @return boolean indicating if the state is final or not
     */
    public boolean isFinal(String name) {
        for (DFAstate state : finalStates) {
            if (state.getName().equals(name)) {
                return state.isFinal();
            }
        }
        return false;
    }

    /**
     * Get state by name
     *
     * @param name name of the state to be retrieved
     * @return the state with the specified name, or null if it doesn't exist
     */
    public DFAstate getState(String name) {
        for (DFAstate state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    /**
     * Check if a state exists
     *
     * @param name name of the state to be checked
     * @return boolean indicating if the state exists or not
     */
    public boolean containsState(String name) {
        for (DFAstate state : states) {
            if (state.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a transition from one state to another with a specific symbol
     *
     * @param from   name of the source state
     * @param to     name of the target state
     * @param symbol symbol of the transition
     * @return boolean indicating if the transition was added successfully or not
     */
    public Boolean addTransition(String from, String to, char symbol) {
        DFAstate fromState = getState(from);
        DFAstate toState = getState(to);
        if (fromState != null && toState != null && sigma.contains(symbol)) {
            fromState.transition.put(symbol, toState);
            return true;
        }
        return false;
    }

    /**
     * Check if a string is accepted by the DFA
     *
     * @param s the string to be checked
     * @return boolean indicating if the string is accepted by the DFA or not
     */
    public boolean accepts(String s) {
        DFAstate currentState = startState;
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (sigma.contains(symbol)) {
                currentState = currentState.transition.get(symbol);
                if (currentState == null) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return currentState.isFinal();
    }

    /**
     * This method returns a new DFA where the transition symbol `symb1` is swapped
     * with `symb2`
     * 
     * @param symb1 The transition symbol to be swapped
     * @param symb2 The transition symbol to swap with
     * @return A new DFA with the swapped transition symbols
     */
    public DFA swap(char symb1, char symb2) {
        DFA newDFA = new DFA();
        newDFA.sigma = new HashSet<>(this.sigma);
        newDFA.finalStates = new HashSet<>(this.finalStates);
        for (DFAstate state : states) {
            newDFA.addState(state.getName());
            if (state.isFinal()) {
                newDFA.setFinal(state.getName());
            }
            if (state.isStart()) {
                newDFA.setStart(state.getName());
            }
        }
        for (DFAstate state : states) {
            for (Map.Entry<Character, DFAstate> transition : state.transition.entrySet()) {
                char symbol = transition.getKey();
                DFAstate toState = transition.getValue();
                if (symbol == symb1) {
                    symbol = symb2;
                } else if (symbol == symb2) {
                    symbol = symb1;
                }
                newDFA.addTransition(state.getName(), toState.getName(), symbol);
            }
        }

        return newDFA;
    }

    /**
     * Generates a string representation of the DFA
     * 
     * @returns a string representation of the DFA
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q = { ");
        for (DFAstate state : states) {
            sb.append(state.getName() + " ");
        }
        sb.append("}\nSigma = { ");
        for (char symbol : sigma) {
            sb.append(symbol + " ");
        }
        sb.append("}\ndelta =\n");
        sb.append("\t");
        for (char symbol : sigma) {
            sb.append(symbol + "\t");
        }
        sb.append("\n");
        for (DFAstate state : states) {
            sb.append(state.getName() + "\t");
            for (char symbol : sigma) {
                DFAstate nextState = state.transition.get(symbol);
                if (nextState != null) {
                    sb.append(nextState.getName() + "\t");
                } else {
                    sb.append("\t");
                }
            }
            sb.append("\n");
        }
        sb.append("q0 = " + startState.getName() + "\n");
        sb.append("F = { ");
        for (DFAstate state : finalStates) {
            sb.append(state.getName() + " ");
        }
        sb.append("}\n");
        return sb.toString();
    }
}