package model;

import java.util.Enumeration;
import java.util.Vector;

public class RuleVariable extends Variable {

    protected BooleanRuleBase rb;
    protected Vector clauseRefs = new Vector();
    protected String promptText;
    protected String ruleName;

    public RuleVariable(BooleanRuleBase rb, String name) {
        super(name);
        this.rb = rb;
        value = "";
        clauseRefs = new Vector();
        rb.addVariable(this);
    }

    public void setValue(String value) {
        this.value = value;
        updateClauses();
    }


    public String askUser() {
        return null;
    }


    public void addClauseRef(Clause ref) {
        clauseRefs.addElement(ref);
    }


    public void updateClauses() {
        Enumeration enm = clauseRefs.elements();
        while (enm.hasMoreElements()) {
            ((Clause) enm.nextElement()).check();
        }
    }


    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setPromptText(String prompText) {
        this.promptText = prompText;
    }

    public String getPromptText() {
        return promptText;
    }


    public void computeStatistics(String invalue) {
    }

    public int normalize(String invalue, float[] outArray, int inx) {
        return inx;
    }


}
