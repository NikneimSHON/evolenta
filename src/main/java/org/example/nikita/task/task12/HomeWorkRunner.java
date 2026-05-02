package org.example.nikita.task.task12;

import org.example.nikita.task.task12.operation.impl.Adder;
import org.example.nikita.task.task12.operation.impl.Division;
import org.example.nikita.task.task12.operation.impl.Multiplication;
import org.example.nikita.task.task12.operation.impl.Subtractor;

public class HomeWorkRunner {
    public static void main(String[] args) {
        double a = 1.0;
        double b = 5.0;

        Calculator calculator1 = new Calculator(new Adder());
        calculator1.calc(a, b);

        Calculator calculator2 = new Calculator(new Division());
        calculator2.calc(a, b);

        Calculator calculator3 = new Calculator(new Multiplication());
        calculator3.calc(a, b);

        Calculator calculator4 = new Calculator(new Subtractor());
        calculator4.calc(a, b);


    }
}
