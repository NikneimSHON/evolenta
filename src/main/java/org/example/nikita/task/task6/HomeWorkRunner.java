package org.example.nikita.task.task6;


import java.util.Scanner;

public class HomeWorkRunner {

    public static void main(String[] args) {
        User user1;
        User user2;

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите имя первого пользователя");
        String name1 = sc.nextLine();
        System.out.println("Введите возраст первого пользователя");
        Integer age1 = sc.nextInt();
        sc.nextLine();

        user1 = new User(name1, age1);

        System.out.println("Введите имя второго пользователя");
        String name2 = sc.nextLine();
        System.out.println("Введите возраст второго пользователя");
        Integer age2 = sc.nextInt();

        user2 = new User(name2, age2);

        System.out.println(Math.min(user1.getAge(), user2.getAge()));


    }
}
