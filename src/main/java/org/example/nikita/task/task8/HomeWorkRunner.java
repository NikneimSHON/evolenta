package org.example.nikita.task.task8;

import java.util.*;

public class HomeWorkRunner {

    public static void main(String[] args) {
        HashMap<Integer, List<User>> map = createUser();
        Scanner sc = new Scanner(System.in);

        Integer age;
        System.out.println("Введите требуемый возраст ");
        age = sc.nextInt();
        if (!map.containsKey(age)) {
            System.out.println("Пользователь с возрастом " + age + " не найден");
        }else {
            map.get(age).stream()
                    .sorted(Comparator.comparing(User::getName))
                    .map(User::toString)
                    .forEach(System.out::println);
        }

    }

    private static HashMap<Integer, List<User>> createUser() {
        HashMap<Integer, List<User>> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i < 6; i++) {
            System.out.println("Введите имя пользователя " + i);
            String name = sc.nextLine();
            System.out.println("Введите возраст пользователя " + i);
            Integer age = sc.nextInt();
            User user = new User(name, age);

            if (map.containsKey(age)) {
                map.get(age).add(user);
            } else {
                List<User> list = new ArrayList<>();
                list.add(user);
                map.put(age, list);
            }
            sc.nextLine();

        }

        return map;
    }
}