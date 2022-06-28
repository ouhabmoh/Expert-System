package model;

import java.util.ArrayList;
import java.util.Vector;

public interface RuleBase {
    void setDisplay (JTextArea txtArea);
    void trace (String text);
    void displayVariables (JTextArea textArea);
    void displayRules (ITextArea textArea);
    void reset();
    void backwardChain(String goalVarName);
    ArrayList forwardChain();
    Vector getGoalVariables();


}
