package com.todo.app.service;

import com.todo.app.controller.TaskController;
import com.todo.app.controller.request.TaskDetails;
import com.todo.app.exception.TaskNotFoundException;
import com.todo.app.models.Task;
import com.todo.app.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTaskList() {
        log.info("Fetching all tasks");
        try {
            List<Task> tasks = taskRepository.findAll();
            log.info("Retrieved {} tasks", tasks.size());
            return tasks;
        } catch (DataAccessException e) {
            log.error("Error fetching tasks: {}", e.getMessage());
            throw new ServiceException("Failed to retrieve tasks", e);
        }
    }

    @Override
    public Task findTaskById(Long taskId) {
        // Log information about retrieving a task by ID
        log.info("Retrieving task with ID: {}", taskId);

        // Attempt to find the task by its ID
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        // Check if the task was found
        if (optionalTask.isPresent()) {
            return optionalTask.get(); // Return the retrieved task
        } else {
            // Log information about the task not being found
            log.warn("Task with ID {} not found", taskId);
            // Throw a custom exception indicating the task wasn't found
            throw new TaskNotFoundException("Task with ID " + taskId + " not found");
        }
    }

    @Override
    public List<Task> getTaskListByDate(LocalDateTime localDateTime) {
        // Logs information about the method being called
        log.info("Fetching tasks for date: {}", localDateTime);

        // Attempts to find tasks with the specified date
        List<Task> tasksByDate = taskRepository.findByTimeToComplete(localDateTime); // Assuming taskService is injected

        // Checks if any tasks were found
        if (tasksByDate.isEmpty()) {
            log.info("No tasks found for date: {}", localDateTime);
            // You can choose to return an empty list or throw a custom exception here
            return Collections.emptyList(); // Returning an empty list
        } else {
            return tasksByDate;
        }
    }

    @Override
    public List<Task> getCompletedTask() {
        // Logs information about the method being called
        log.info("Fetching completed tasks");

        // Finds all tasks with completed status set to true
        List<Task> completedTasks = taskRepository.findByCompletedStatus(true); // Assuming taskService is injected

        // Checks if any completed tasks were found
        if (completedTasks.isEmpty()) {
            log.info("No completed tasks found");
            // You can choose to return an empty list or throw a custom exception here
            return Collections.emptyList(); // Returning an empty list
        } else {
            return completedTasks;
        }
    }

    @Override
    public List<Task> getUnCompletedTask() {
        // Logs information about the method being called
        log.info("Fetching completed tasks");

        // Finds all tasks with completed status set to true
        List<Task> completedTasks = taskRepository.findByCompletedStatus(false); // Assuming taskService is injected

        // Checks if any completed tasks were found
        if (completedTasks.isEmpty()) {
            log.info("No completed tasks found");
            // You can choose to return an empty list or throw a custom exception here
            return Collections.emptyList(); // Returning an empty list
        } else {
            return completedTasks;
        }
    }


    @Override
    public Task updateTaskDetails(Task taskToUpdate) {
        log.info("Updating task details: {}", taskToUpdate);

        Optional<Task> existingTaskOptional = taskRepository.findById(taskToUpdate.getTaskId());

        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitleOfTheTask(taskToUpdate.getTitleOfTheTask());
            existingTask.setTimeToComplete(taskToUpdate.getTimeToComplete());
            // Update other fields if needed

            // Save the updated task
            return taskRepository.save(existingTask);
        } else {
            log.error("Task with ID {} not found for update", taskToUpdate.getTaskId());
            // You can choose to throw a custom exception here (e.g., ResourceNotFoundException)
            throw new RuntimeException("Task not found");
        }
    }


    @Override
    public void deleteTask(Long taskId) {
        // Logs information about the method being called with task ID
        log.info("Deleting task with ID: {}", taskId);

        // Attempts to find the task by its ID
        Optional<Task> taskToDelete = taskRepository.findById(taskId); // Assuming taskService is injected

        // Checks if the task exists


        // Deletes the task
        taskRepository.delete(taskToDelete); // Assuming taskService is injected

        log.info("Task with ID {} deleted successfully", taskId);
    }

    @Override
    public Task saveTask(TaskDetails taskDetails) {
        // Logs information about the method being called with task details
        log.info("Saving new task with details: {}", taskDetails);

        // Converts TaskDetails object to a Task object (assuming conversion logic exists)
        Task newTask = mapperTaskDetailsToTask(taskDetails);

        // Saves the new task
        Task savedTask = taskRepository.save(newTask); // Assuming taskService is injected

        return savedTask;
    }

    private Task mapperTaskDetailsToTask(TaskDetails taskDetails) {
        Task task = new Task();
        task.setTitleOfTheTask(taskDetails.getTitleOfTheTask());
        task.setCompletedStatus(taskDetails.isCompletedStatus());
        task.setTimeToComplete(taskDetails.getTimeToComplete());
        return task;
    }
}

