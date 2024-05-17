package com.tasknoter;

import java.util.List;

class TaskApp {

    private final List<Task> taskList;

    TaskApp() {
        this.taskList = TaskAppUtil.loadSavedTasks();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    void addTask(String info) {
        taskList.add(new Task(info));
    }

    void updateTask(int taskNumber) {}

    void removeTask(int taskNumber) {}


}
