package com.todo.app.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetails {
    private String titleOfTheTask;

    public String getTitleOfTheTask() {
        return titleOfTheTask;
    }

    public void setTitleOfTheTask(String titleOfTheTask) {
        this.titleOfTheTask = titleOfTheTask;
    }

    public boolean isCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public LocalDateTime getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(LocalDateTime timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    private boolean completedStatus;
    private LocalDateTime timeToComplete;
}
