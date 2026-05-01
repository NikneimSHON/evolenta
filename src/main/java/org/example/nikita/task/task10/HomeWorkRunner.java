package org.example.nikita.task.task10;

import java.util.stream.LongStream;

public class HomeWorkRunner {
    public static void main(String[] args) {
        long result = getArithmeticProgressionSum(10_000_000, 1_000_000_000);
        System.out.println(result);
    }

    public static long getArithmeticProgressionSum(int a, int b) {
        if (a >= b) {
            return 0;
        }
        return LongStream.range(a, b).sum();
    }
}
