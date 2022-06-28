package model;

import java.util.*;

public class BooleanRuleBase implements RuleBase {
    String name;
    Hashtable<String, RuleVariable> variableList = new Hashtable();
    Clause[] clauseVarList;
    Vector ruleList = new Vector();

    Vector conclusionVarList = new Vector();
    Rule rulePtr;
    Clause clausePtr;
    Stack goalClauseStack = new Stack();
    Hashtable effectors;
    Hashtable sensors;
    Vector factList;
    JTextArea textAreal;
    public String processDetails="";
    public BooleanRuleBase(String name) {
        this.name = name;

    }

    public void setVariableValue(String var, String value){
        RuleVariable ruleVariable = variableList.get(var);
       ruleVariable.setValue(value);

    }


    public void setDisplay(JTextArea txtArea) {

    }


    public void trace(String text) {

    }


    public void displayVariables(JTextArea textArea) {

    }


    public void displayRules(ITextArea textArea) {

    }


    public void reset() {

    }


    public void backwardChain(String goalVarName) {
        RuleVariable goalVar = (RuleVariable) variableList.get (goalVarName);
        Enumeration goalclauses = goalVar.clauseRefs.elements();
        while(goalclauses.hasMoreElements()) {
            Clause goalClause = (Clause) goalclauses.nextElement();
            if (goalClause.consequent == false) {
                continue;
            }
            goalClauseStack.push(goalClause);
            Rule goalRule = goalClause.getRule();
            Boolean ruleTruth = goalRule.backChain();
            if (ruleTruth == null) {
                trace("\nRule " + goalRule.name
                        + " ia null, can't determine truth value.");
            } else if (ruleTruth.booleanValue() == true) {
                goalVar.setValue(goalClause.rhs);
                goalVar.setRuleName(goalRule.name);
                goalClauseStack.pop();
                trace("\nRule" + goalRule.name + " is true, setting"
                        + goalVar.name + ": = " + goalVar.value);


                if (goalClauseStack.empty() == true) {
                    trace("\n +++ Found Solution for goal: " + goalVar.name);
                    break;
                }
            } else {
                goalClauseStack.pop();
                trace("\nRule" + goalRule.name + " is false, can't set"
                        + goalVar.name);
            }
        }

            if (goalVar.value == null) {
                trace("\n +++ Could Not Pind Solution for goal: " + goalVar.name);

            }
        }


    public ArrayList<String> forwardChain() {
        Vector conflictRuleSet = new Vector();
        ArrayList<String> newFacts = new ArrayList();
        conflictRuleSet = match(true);

        while (conflictRuleSet.size() > 0) {
            Rule selected = selectRule(conflictRuleSet);
            selected.fire();
//            System.out.println("selected : "+selected.name);
            processDetails+="selected : "+selected.name+"\n";
            newFacts.add(selected.name);
            conflictRuleSet = match(false);
        }
        return newFacts;
    }


    public Vector getGoalVariables() {
        return null;
    }

    public Vector match(boolean test) {
        Vector matchList = new Vector();
        Enumeration enm = ruleList.elements();

        while (enm.hasMoreElements()) {
            Rule testRule = (Rule) enm.nextElement();
            if (test)
                testRule.check();
            if (testRule.truth == null)
                continue;
            if ((testRule.truth.booleanValue() == true) && (testRule.fired == false)) {
                matchList.addElement(testRule);
            }


        }
        displayConflictSet(matchList);
        return matchList;
    }

    private void displayConflictSet(Vector matchList) {
        Enumeration enm = matchList.elements();
        if(enm.hasMoreElements())
            processDetails+="conflictRuleSet :\n";
//        System.out.println("conflictRuleSet : ");
        while (enm.hasMoreElements()){

            Rule iterateRule=(Rule) enm.nextElement();
            processDetails+=iterateRule.getClauses();
            processDetails+=" ==> "+iterateRule.name+"\n";
//            System.out.print(iterateRule.getClauses());
//            System.out.println(" ==> "+iterateRule.name);
        }
    }

    public Rule selectRule(Vector ruleSet) {
        Enumeration enm = ruleSet.elements();
        long numClauses;
        Rule nextRule;
        Rule bestRule = (Rule) enm.nextElement();
        long max = bestRule.numAntecedents();
        while (enm.hasMoreElements()) {
            nextRule = (Rule) enm.nextElement();

            if ((numClauses = nextRule.numAntecedents()) > max) {
                max = numClauses;
                bestRule = nextRule;
            }


        }
        return bestRule;
    }

    public void addVariable(RuleVariable ruleVariable) {
        variableList.put(ruleVariable.getName(),ruleVariable);
    }
}
