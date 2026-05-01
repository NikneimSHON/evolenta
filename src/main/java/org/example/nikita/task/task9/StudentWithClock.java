package org.example.nikita.task.task9;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudentWithClock implements Learner {
    private Learner learner;

    public StudentWithClock(Learner learner) {
        this.learner = learner;
    }

    @Override
    public void learn() {
        learner.learn();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Текущее время: " + currentTime);
    }
}