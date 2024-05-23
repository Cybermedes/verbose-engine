package com.tasknoter;

import lombok.Getter;

import java.util.List;

@Getter
class TaskApp {

    private final List<Task> taskList;

    TaskApp() {
        this.taskList = TaskAppUtil.loadSavedTasks();
    }

    void addTask(String info) {
        taskList.add(new Task(info));
        TaskAppUtil.saveUpdatedTasks(taskList);
    }

    void updateTask(int taskNumber) {}

    void removeTask(int taskNumber) {}


}
