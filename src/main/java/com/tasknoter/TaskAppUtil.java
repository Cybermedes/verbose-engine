package com.tasknoter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TaskAppUtil {

    static void printTaskTable(TaskApp table) {
        table.getTaskList().forEach(System.out::println);
    }

    static List<Task> loadSavedTasks() {
        List<Task> taskList = new ArrayList<>();
        File savedFile = new File("saved_tasks.csv");
        if (savedFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(savedFile));
                br.readLine();
                for (String line; (line = br.readLine()) != null; ) {
                    String[] data = line.split(";");
                    taskList.add(createTaskObject(data));
                }
                br.close();
            } catch (IOException e) {
                System.err.format("%s%n", e);
            }
        }
        return taskList;
    }

    private static Task createTaskObject(String[] data) {

        String task = data[0];
        boolean completed = "yes".equalsIgnoreCase(data[1]);
        LocalDateTime startDT = LocalDateTime.parse(data[2]);
        LocalDateTime finishDT = "null".equalsIgnoreCase(data[3]) ? null : LocalDateTime.parse(data[3]);
        String description = data[4];

        return new Task(task, completed, startDT, finishDT, description);
    }

    static void storeNewTasks() {}
}
