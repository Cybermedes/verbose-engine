package com.tasknoter;

import java.time.LocalDateTime;

class Task {

    private String note;
    private boolean isCompleted;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String description;

    Task(String note) {
        this.note = note;
        this.startedAt = LocalDateTime.now();
        this.isCompleted = false;
        this.finishedAt = null;
        this.description = null;
    }

}
