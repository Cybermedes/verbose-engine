package com.tasknoter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class TaskAppUtil {

    protected static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    private static final File savedFile = new File("saved_tasks.csv");

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
        LocalDateTime startDT = LocalDateTime.parse(data[2], dtf);
        LocalDateTime finishDT = "TBD".equalsIgnoreCase(data[3]) ? null : LocalDateTime.parse(data[3], dtf);
        String description = data[4];

        return new Task(task, completed, startDT, finishDT, description);
    }

    static void storeNewTasks(List<Task> data) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(savedFile));
            bw.write("task;is_completed;started_at;finished_at;description");
            bw.newLine();

            for (Task datum : data) {
                bw.write(datum.getNote() + ";");
                bw.write(datum.isCompleted() ? "yes;" : "no;");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
                bw.write(datum.getStartedAt().format(dtf) + ";");
                bw.write((datum.getFinishedAt() == null) ? "TBD;" : datum.getStartedAt().format(dtf) + ";");
                bw.write(datum.getDescription());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }
}
