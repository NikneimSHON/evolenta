package org.example.nikita.task.task7;

import org.example.nikita.task.task6.User;

import java.util.Comparator;

public class SortByAge implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }
}
