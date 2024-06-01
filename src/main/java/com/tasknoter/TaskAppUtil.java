package com.tasknoter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class TaskAppUtil {

    private static final File savedFile = new File(".saved_tasks.csv");

    static List<Task> loadSavedTasks() {
        List<Task> taskList = new ArrayList<>();
        if (savedFile.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(savedFile));
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
        LocalDateTime startDT = LocalDateTime.parse(data[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime finishDT = "TBD".equalsIgnoreCase(data[3]) ? null : LocalDateTime.parse(data[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String description = data[4];

        return new Task(task, completed, startDT, finishDT, description);
    }

    static void saveUpdatedTasks(List<Task> data) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(savedFile));

            for (Task datum : data) {
                bw.write(datum.getNote() + ";");
                bw.write(datum.isCompleted() ? "yes;" : "no;");
                bw.write(datum.getStartedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ";");
                bw.write((datum.getFinishedAt() == null) ? "TBD;" : datum.getStartedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ";");
                bw.write(datum.getDescription());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }
}
