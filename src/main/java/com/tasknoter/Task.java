package com.tasknoter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
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
        this.description = "no description";
    }

}
