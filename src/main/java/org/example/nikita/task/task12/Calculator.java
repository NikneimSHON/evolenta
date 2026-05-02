package org.example.nikita.task.task12;

import org.example.nikita.task.task12.operation.Operation;

public class Calculator {
    Operation operation;

    public Calculator(Operation operation) {
        this.operation = operation;
    }

    public void calc(double a, double b) {
        System.out.println(operation.getResult(a, b));
    }
}
