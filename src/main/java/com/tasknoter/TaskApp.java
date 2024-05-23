package com.tasknoter;

import lombok.Getter;

import java.time.LocalDateTime;
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

    void completeTask(int index) {
        if (index <= 0 || index > taskList.size()) {
            System.out.println("invalid index value/ index out of range");
            System.exit(0);
        }
        taskList.get(index - 1).setCompleted(true);
        taskList.get(index - 1).setFinishedAt(LocalDateTime.now());
        TaskAppUtil.saveUpdatedTasks(taskList);
    }

    void removeTask(int index) {
        if (index <= 0 || index > taskList.size()) {
            System.out.println("invalid index value/ index out of range");
            System.exit(0);
        }
        taskList.remove(index - 1);
        TaskAppUtil.saveUpdatedTasks(taskList);
    }
}
