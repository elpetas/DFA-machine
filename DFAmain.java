/* @author Johann Vargas
 * 
*/
import fa.State;
import fa.dfa.DFA;
import java.util.Scanner;
public class DFAmain {
    public static void main(String args[])
    { 
        Scanner scanner = new Scanner(System.in);
        Scanner inputScanner = new Scanner(System.in);
        DFA dfa = new DFA();
        boolean quit = false;
        System.out.print("------------------- Create a new machine ------------------- \n");
        // Get input for alphabet
        System.out.print("Enter alphabet (separated by spaces): ");
        String alphabetInput = scanner.nextLine();
        String[] alphabetArray = alphabetInput.split(" ");
        for (String symbol : alphabetArray) {
            dfa.addSigma(symbol.charAt(0));
        }
        
        // Get input for states
        System.out.print("Enter states (separated by spaces): ");
        String statesInput = scanner.nextLine();
        String[] statesArray = statesInput.split(" ");
        for (String stateName : statesArray) {
            dfa.addState(stateName);
        }
        
        // Get input for final states
        System.out.print("Enter final states (separated by spaces): ");
        String finalStatesInput = scanner.nextLine();
        String[] finalStatesArray = finalStatesInput.split(" ");
        for (String finalState : finalStatesArray) {
            dfa.setFinal(finalState);
        }
        
        // Get input for start state
        System.out.print("Enter start state: ");
        String startState = scanner.nextLine();
        dfa.setStart(startState);
        
        // Get input for transitions
        System.out.println("Enter transitions (fromState toState onSymb):");
        System.out.println("Type 'done' when finished.");
        String transitionInput = scanner.nextLine();
        while (!transitionInput.equals("done")) {
            String[] transitionArray = transitionInput.split(" ");
            String fromState = transitionArray[0];
            String toState = transitionArray[1];
            char onSymb = transitionArray[2].charAt(0);
            dfa.addTransition(fromState, toState, onSymb);
            transitionInput = scanner.nextLine();
        }
        while (!quit) {
            System.out.println("Enter an option:");
            System.out.println("1. Print DFA");
            System.out.println("2. Test if string is accepted");
            System.out.println("3. Swap symbols and copy DFA");
            System.out.println("4. Quit");
            String choice = inputScanner.nextLine();
            
            switch (choice) {
                case "1":
                    String table = dfa.toString();
                    System.out.println(table);
                    break;
                case "2":
                    System.out.println("Enter string to test:");
                    String testString = inputScanner.nextLine();
                    boolean accepted = dfa.accepts(testString);
                    System.out.println("String " + (accepted ? "accepted" : "not accepted"));
                    break;
                case "3":
                    System.out.println("Enter first symbol:");
                    char symbol1 = inputScanner.nextLine().charAt(0);
                    System.out.println("Enter second symbol:");
                    char symbol2 = inputScanner.nextLine().charAt(0);
                    DFA swappedDFA = dfa.swap(symbol1, symbol2);
                    String swappedTable = swappedDFA.toString();
                    System.out.println(swappedTable);
                    break;
                case "4":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
        }

        scanner.close();
        inputScanner.close();
    }


}
