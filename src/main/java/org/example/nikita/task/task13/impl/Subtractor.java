package org.example.nikita.task.task13.impl;

import org.example.nikita.task.task13.Operation;
import org.springframework.stereotype.Component;

@Component
public class Subtractor implements Operation {

    @Override
    public double getResult(double a, double b) {
        return a - b;
    }

}
