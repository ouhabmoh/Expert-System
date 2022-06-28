package model;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class Variable {
    protected String name;
    protected String value;
    protected Vector labels;
    protected int column;

    public Variable() {
    }

    public Variable(String name) {
        this.name = name;
        value = null;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public void setLabels(String newLabels) {
        labels = new Vector();
        StringTokenizer tok = new StringTokenizer(newLabels, "");
        while (tok.hasMoreTokens()) {
            labels.addElement(new String(tok.nextToken()));
        }
    }

    public String getLabel(int index) {
        return (String) labels.elementAt(index);
    }

    public Vector getLabels() {
        return (Vector) labels.clone();

    }

    public String getLabelsAsString() {
        String labelList = new String();
        Enumeration enm = labels.elements();
        while (enm.hasMoreElements()) {
            labelList += enm.nextElement() + " ";
        }
        return labelList;
    }

    public int getIndex(String label) {
        int index = -1;
        if (labels == null)
            return index;

        for (int i = 0; i < labels.size(); i++) {
            if (label.equals((String) labels.elementAt(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean categorical() {
        if (labels != null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return name;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public abstract void computeStatistics(String inValue);

    public abstract int normalize(String inValue, float[] outArray,
                                  int inx);

    public int normalizedSize() {

        return 1;
    }

    public String getDecodedValue(float[] act, int index){
        return String.valueOf(act[index]);
    }


}