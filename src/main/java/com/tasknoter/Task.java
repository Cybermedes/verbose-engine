package com.tasknoter;

import lombok.ToString;

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

    Task(String note,
         boolean isCompleted,
         LocalDateTime startedAt,
         LocalDateTime finishedAt,
         String description) {
        this.note = note;
        this.isCompleted = isCompleted;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.description = description;
    }
}
