package org.example.nikita.task.task12.operation.impl;

import org.example.nikita.task.task12.operation.Operation;

public class Divider implements Operation {


    @Override
    public double getResult(double a, double b)  {
        return a / b;
    }
}
