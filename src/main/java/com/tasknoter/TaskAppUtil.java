package com.tasknoter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class TaskAppUtil {

    private static final Path savedFile = Path.of(".saved_tasks.csv");

    static List<Task> loadSavedTasks() {

        List<Task> taskList = new ArrayList<>();
        if (Files.exists(savedFile)) {
            try (Stream<String> lines = Files.lines(savedFile)) {
                lines.map(s -> s.split(";"))
                        .map(TaskAppUtil::createTaskObject)
                        .forEach(taskList::add);
            } catch (IOException e) {
                System.err.format("%s%n", e);
            }
        }
        return taskList;
    }

    static void saveUpdatedTasks(List<Task> data) {
        try (var writer = Files.newBufferedWriter(savedFile)) {
            for (Task datum : data) {
                writer.write(datum.getNote() + ";");
                writer.write(datum.isCompleted() ? "yes;" : "no;");
                writer.write(datum.getStartedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ";");
                writer.write((datum.getFinishedAt() == null) ? "TBD;" : datum.getStartedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ";");
                writer.write(datum.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }

    private static Task createTaskObject(String[] data) {

        String task = data[0];
        boolean completed = "yes".equalsIgnoreCase(data[1]);
        LocalDateTime startDT = LocalDateTime.parse(data[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime finishDT = "TBD".equalsIgnoreCase(data[3]) ? null : LocalDateTime.parse(data[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String description = data[4];

        return new Task(task, completed, startDT, finishDT, description);
    }
}
