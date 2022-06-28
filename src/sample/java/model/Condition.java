package model;

public class Condition {
    int index;
    String symbol;
    public Condition (String symbol) {
        this.symbol = symbol;

        if (symbol.equals("=")) {
            index = 1;
        } else if (symbol.equals(">")) {
            index = 2;
        } else if (symbol.equals("<")) {
            index = 3;
        } else if (symbol.equals("!=")) {
            index = 4;
        } else {
            index = -1;
        }
    }
            public String toString() {
                return symbol;
            }

            }

