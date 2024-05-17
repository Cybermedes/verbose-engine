package com.tasknoter;

import java.util.ArrayList;

class TaskAppUtil {

    static void printTaskTable(TaskApp table) {
        table.getTaskList().forEach(System.out::println);
    }

    static ArrayList<Task> loadSavedTasks() {
        return null;
    }

    static void storeNewTasks() {}
}
