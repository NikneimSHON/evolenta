package org.example.nikita.task.task7;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HomeWorkRunner {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Введите имя пользователя");
            String name = sc.nextLine();
            System.out.println("Введите возраст пользователя");
            Integer age = sc.nextInt();
            users.add(new User(name, age));
            sc.nextLine();

        }

        Collections.sort(users,new SortByAge());

        for(User user:users){
            System.out.println(user.toString());
        }
    }
}
