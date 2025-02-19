package com.todo.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.*;

//@Builder
@Entity
@Data
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String titleOfTheTask;
    private boolean completedStatus;
    private LocalDateTime timeToComplete;

    public Long getTaskId() {
        return taskId;
    }

    public String getTitleOfTheTask() {
        return titleOfTheTask;
    }

    public boolean isCompletedStatus() {
        return completedStatus;
    }

    public LocalDateTime getTimeToComplete() {
        return timeToComplete;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setTitleOfTheTask(String titleOfTheTask) {
        this.titleOfTheTask = titleOfTheTask;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }

    public void setTimeToComplete(LocalDateTime timeToComplete) {
        this.timeToComplete = timeToComplete;
    }
}
