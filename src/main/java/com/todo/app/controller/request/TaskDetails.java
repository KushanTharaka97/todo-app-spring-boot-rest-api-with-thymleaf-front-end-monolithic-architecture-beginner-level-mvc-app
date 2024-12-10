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
    private boolean completedStatus;
    private LocalDateTime timeToComplete;
}
