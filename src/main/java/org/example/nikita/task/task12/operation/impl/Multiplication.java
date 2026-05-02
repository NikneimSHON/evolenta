package org.example.nikita.task.task12.operation.impl;

import org.example.nikita.task.task12.operation.Operation;

public class Multiplication implements Operation {
    @Override
    public double getResult(double a, double b) {
        return a * b;
    }
}
