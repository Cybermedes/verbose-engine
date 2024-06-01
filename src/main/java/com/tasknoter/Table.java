package com.tasknoter;

import java.time.format.DateTimeFormatter;

class Table {

    private Table() {
    }

    static void printTable(TaskApp tasks) {

        int idxLength = "id".length();
        int completedLength = "Done".length();
        int noteLength = "Note".length();
        int startedAtLength = "Started".length();
        int finishedAtLength = "Finished".length();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");

        var listOfTasks = tasks.getTaskList();

        for (Task task : listOfTasks) {
            if (task.getNote().length() > noteLength) {
                noteLength = task.getNote().length();
            }
            if (task.getStartedAt().format(dtf).length() > startedAtLength) {
                startedAtLength = task.getStartedAt().format(dtf).length();
            }

            if (task.getFinishedAt() != null) {
                if (task.getFinishedAt().format(dtf).length() > finishedAtLength) {
                    finishedAtLength = task.getFinishedAt().format(dtf).length();
                }
            }
        }

        printBorder(idxLength, noteLength, completedLength, startedAtLength, finishedAtLength);
        System.out.printf("| %-" + idxLength + "s | %-" +
                noteLength + "s | %-" +
                completedLength + "s | %-" +
                startedAtLength + "s | %-" +
                finishedAtLength + "s |%n", "#", "Note", "Done", "Started", "Finished");
        printBorder(idxLength, noteLength, completedLength, startedAtLength, finishedAtLength);

        for (int i = 0; i < listOfTasks.size(); i++) {
            String finishedAtString = listOfTasks.get(i).getFinishedAt() == null ? "" :
                    listOfTasks.get(i).getFinishedAt().format(dtf);
            String completed = listOfTasks.get(i).isCompleted() ? "yes" : "no";
            System.out.printf("| %-" + idxLength + "s | %-" +
                            noteLength + "s | %-" +
                            completedLength + "s | %-" +
                            startedAtLength + "s | %-" +
                            finishedAtLength + "s |%n",
                    (i + 1),
                    listOfTasks.get(i).getNote(),
                    completed,
                    listOfTasks.get(i).getStartedAt().format(dtf),
                    finishedAtString);
            printBorder(idxLength, noteLength, completedLength, startedAtLength, finishedAtLength);
        }
    }

    private static void printBorder(int idxLength, int noteLength, int completedLength, int startedAtLength, int finishedAtLength) {
        System.out.print("+");
        for (int i = 0; i < idxLength + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");

        for (int i = 0; i < noteLength + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");

        for (int i = 0; i < completedLength + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");

        for (int i = 0; i < startedAtLength + 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");

        for (int i = 0; i < finishedAtLength + 2; i++) {
            System.out.print("-");
        }
        System.out.printf("+%n");
    }
}
