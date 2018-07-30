package com.herokuapp.erpmesbackend.erpmesbackend.tasks;

import java.time.LocalDateTime;
import java.util.Random;

public class TaskFactory {

    private final String[] NAMES = {"Zapakować przesyłkę nr 1444", "Zapakować przesyłkę nr 1450",
            "Wysłać przesyłkę nr 1410", "Wysłać przesyłkę nr 1429", "Wysłać przesyłkę nr 1490"};

    private final String[] DETAILS = {"Dodać darmowy upominek", "Zmienić sposób dostarczenia na list ekonomiczny",
            "Zmienić sposób dostarczenia na list priorytetowy", "Nakleić informację: \"Uwaga! Szkło\""};

    private final Random random;

    public TaskFactory() {
        random = new Random();
    }

    private String generate(String[] array) {
        return array[random.nextInt(array.length)];
    }

    public String generateName() {
        return generate(NAMES);
    }

    public String generateDetails() {
        return generate(DETAILS);
    }

    public Category generateCategory() {
        return Category.values()[random.nextInt(Category.values().length)];
    }

    public Category generateDoneCategory() {
        return Category.DONE;
    }

    public Category generateTodoCategory() {
        return Category.TODO;
    }

    public int generateEstimatedTimeInMinutes() {
        return random.nextInt(15) + 5;
    }

    public LocalDateTime generateDeadline() {
        return LocalDateTime.now();
    }

//    public TaskRequest generateTaskWithoutPrecedingTasks() {
//
//    }
//
//    public TaskRequest generateTaskWithPrecedingTasks() {
//
//    }
}