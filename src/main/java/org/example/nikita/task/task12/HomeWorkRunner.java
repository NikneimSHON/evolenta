package org.example.nikita.task.task12;

import org.example.nikita.task.task12.operation.impl.Adder;
import org.example.nikita.task.task12.operation.impl.Divider;
import org.example.nikita.task.task12.operation.impl.Multiplier;
import org.example.nikita.task.task12.operation.impl.Subtractor;

import java.util.Scanner;

public class HomeWorkRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число a");
        double a = sc.nextDouble();
        System.out.println("Введите число b");
        double b = sc.nextDouble();

        System.out.print("Результат сложения a и b: ");
        new Calculator(new Adder()).calc(a, b);

        System.out.print("Результат вычитания a и b: ");
        new Calculator(new Subtractor()).calc(a, b);

        System.out.print("Результат умножения a и b: ");
        new Calculator(new Multiplier()).calc(a, b);

        System.out.print("Результат деления a и b: ");
        new Calculator(new Divider()).calc(a, b);


    }
}
