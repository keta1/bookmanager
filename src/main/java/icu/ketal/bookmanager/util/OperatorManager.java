package icu.ketal.bookmanager.util;

import icu.ketal.bookmanager.entry.Operator;

public class OperatorManager {
    private static Operator currentOperator;

    public static Operator getCurrentOperator() {
        return currentOperator;
    }

    public static void setCurrentOperator(Operator currentOperator) {
        OperatorManager.currentOperator = currentOperator;
    }
}
