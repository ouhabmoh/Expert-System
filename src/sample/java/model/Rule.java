package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class Rule {
    BooleanRuleBase rb;
    String name;
    Clause[] antecedents;
    Clause consequent;
    Boolean truth;
    boolean fired = false;

    public Rule(BooleanRuleBase rb, String name, Clause lhs, Clause rhs) {
        this.rb = rb;
        this.name = name;
        antecedents = new Clause[1];
        antecedents[0] = lhs;
        lhs.addRuleRef(this);
        consequent = rhs;
        rhs.addRuleRef(this);
        rhs.setConsequent();
        rb.ruleList.addElement(this);
        truth = null;
    }

    public Rule(BooleanRuleBase rb, String name, Clause[] lhsclauses, Clause rhs) {
        this.rb = rb;
        this.name = name;
        antecedents = new Clause[lhsclauses.length];
        for (int i = 0; i < lhsclauses.length; i++) {
            antecedents[i] = lhsclauses[i];
            antecedents[i].addRuleRef(this); //why add it lots of times
        }
        consequent = rhs;
        rhs.addRuleRef(this);
        rhs.setConsequent();
        rb.ruleList.addElement(this);
        truth = null;
    }


    public long numAntecedents() {
        return antecedents.length;
    }

    public ArrayList<String> getClauses(){
        ArrayList<String> clauses=new ArrayList<>();
        for (Clause antecedent : antecedents) {
            clauses.add(antecedent.tostring());
        }
        return clauses;
    }

    Boolean check() {
        rb.trace("\nTesting rule " + name);
        for (int i = 0; i < antecedents.length; i++) {
            if (antecedents[i].truth == null)
                return truth = null;
            if (antecedents[i].truth.booleanValue() == true)
                continue;
            else
                return truth = new Boolean(false);


        }
        return truth = new Boolean(true);
    }

    void fire() {
        rb.trace("\nFiring rule " + name);
        truth = new Boolean(true);
        fired = true;
//        if (consequent.lhs == null)
//            ((EffectorClause) consequent).perform(rb);
//        else {
//            consequent.lhs.setValue(consequent.rhs);
//            checkRules(consequent.lhs.clauseRefs);
//        }
        if(consequent.lhs != null){
            consequent.lhs.setValue(consequent.rhs);
            checkRules(consequent.lhs.clauseRefs);
        }
    }
    public static void checkRules (Vector clauseRefs) {
        Enumeration enm = clauseRefs.elements();
        while (enm.hasMoreElements()) {
            Clause temp = (Clause) enm.nextElement();
            Enumeration enum2 = temp.ruleRefs.elements();
            while(enum2.hasMoreElements()) {
                ((Rule) enum2.nextElement()).check();

            }
        }
    }

    public Boolean backChain(){
        for(int i = 0; i < antecedents.length; i++){
            if(antecedents[i].truth == null)
                rb.backwardChain(antecedents[i].lhs.name);

            if(antecedents[i].truth == null){
                antecedents[i].lhs.askUser();
                truth = antecedents[i].check();
            }

            if(antecedents[i].truth.booleanValue() == true)
                continue;
            else
                return truth = new Boolean(false);

        }

        return truth = new Boolean(true);
    }

}



