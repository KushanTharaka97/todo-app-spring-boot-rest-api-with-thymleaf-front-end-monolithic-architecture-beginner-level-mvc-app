package com.todo.app.repository;

import com.todo.app.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTimeToComplete(LocalDateTime timeToComplete);

    @Modifying
    @Query("UPDATE Task t SET t.titleOfTheTask = ?1 WHERE t.taskId = ?2")
    int setTitleOfTheTask(String title, Long taskId);

    // Assuming you have a field named 'timeToComplete' in your Task entity
    @Modifying
    @Query("UPDATE Task t SET t.timeToComplete = ?1 WHERE t.taskId = ?2")
    int setTimeToComplete(LocalDateTime timeToComplete, Long taskId);

    List<Task> findByCompletedStatus(boolean completedStatus);
}
