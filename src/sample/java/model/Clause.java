package model;

import java.util.Vector;

public class Clause {
    Vector ruleRefs;
    RuleVariable lhs;
    String rhs;
    Condition cond;
    boolean consequent;
    Boolean truth;

    public boolean booleanValue(){
        return truth.booleanValue();
    }
    public Clause(RuleVariable lhs, Condition cond, String rhs) {
        this.lhs = lhs;
        this.cond = cond;
        this.rhs = rhs;
        lhs.addClauseRef(this);
        ruleRefs = new Vector();

        truth = null;
        consequent = false;
    }


    public Clause() {
    }


    public String tostring() {
        return lhs.name + cond.toString() + rhs + "";
    }

    public void addRuleRef(Rule ref) {
        ruleRefs.addElement(ref);
    }

    public Boolean check() {
        if (consequent == true)
            return truth = null;

        if (lhs.value == null) {
            return truth = null;
        } else {
            Double lhsNumericValue = null;
            Double rhsNumericValue = null;
            boolean bothNumeric = true;
            try {
                lhsNumericValue = Double.valueOf(lhs.value);
                rhsNumericValue = Double.valueOf(rhs);
            } catch (Exception e) {
                bothNumeric = false;
            }

            switch (cond.index) {
                case 1:
                    if (bothNumeric)
                        truth = new Boolean(lhsNumericValue.compareTo(rhsNumericValue) == 0);
                    else
                        truth = new Boolean(lhs.value.contains(rhs));
                    break;
                case 2:
                    if (bothNumeric) {
                        truth = new Boolean(lhsNumericValue.compareTo(rhsNumericValue) > 0);
                    } else {
                        truth = new Boolean(lhs.value.compareTo(rhs) > 0);
                    }
                    break;
                case 3:
                    if (bothNumeric) {
                        truth = new Boolean(lhsNumericValue.compareTo(rhsNumericValue) < 0);
                    } else {
                        truth = new Boolean(lhs.value.compareTo(rhs) < 0);
                    }
                    break;
                case 4:
                    if (bothNumeric) {
                        truth = new Boolean(lhsNumericValue.compareTo(rhsNumericValue) != 0);
                    } else {
                        truth = new Boolean(lhs.value.compareTo(rhs) != 0);
                    }
                    break;

            }
            return truth;
        }
    }

    public void setConsequent() {
        consequent = true;
    }

    public Rule getRule() {
        if (consequent == true)
            return (Rule) ruleRefs.firstElement();

        return null;
    }

}