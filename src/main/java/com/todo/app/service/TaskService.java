package com.todo.app.service;

import com.todo.app.controller.request.TaskDetails;
import com.todo.app.models.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

  /**
   * @return
   */
  List<Task> getTaskList();

  /**
   * @param localDateTime
   * @return
   */
  List<Task> getTaskListByDate(LocalDateTime localDateTime);

  /**
   * @return
   */
  List<Task> getCompletedTask();

  /**
   * @return
   */
  List<Task> getUnCompletedTask();

  /**
   * @param taskToUpdate
   * @return
   */
  Task updateTaskDetails(Task taskToUpdate);

  /**
   * @param taskId
   */
  void deleteTask(Long taskId);

  /**
   * @param taskDetails
   * @return
   */
  Task saveTask(TaskDetails taskDetails);

  Task findTaskById(Long taskId);
}
