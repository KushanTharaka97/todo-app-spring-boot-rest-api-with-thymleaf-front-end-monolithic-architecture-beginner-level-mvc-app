package com.todo.app.controller;

import com.todo.app.controller.request.TaskDetails;
import com.todo.app.models.Task;
import com.todo.app.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "This API is used to get calendar availability details of a resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success response",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<Task>> getTasks() {
        // Consider using debug level for this message
        log.debug("Get tasks controller triggered");
        List<Task> taskListGet = taskService.getTaskList();
        if (taskListGet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskListGet, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> completedTasks = taskService.getCompletedTask();
        return ResponseEntity.ok(completedTasks);
    }

    @GetMapping("/uncompleted")
    public ResponseEntity<List<Task>> getUnCompletedTasks() {
        List<Task> unCompletedTasks = taskService.getUnCompletedTask();
        return ResponseEntity.ok(unCompletedTasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task taskOptional = taskService.findTaskById(taskId);
        return ResponseEntity.ok(taskOptional);
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody TaskDetails taskDetails) {
        Task savedTask = taskService.saveTask(taskDetails);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task taskToUpdate) {
        taskToUpdate.setTaskId(taskId); // Set ID based on path variable
        Task updatedTask = taskService.updateTaskDetails(taskToUpdate);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build(); // Use build() for successful deletions
    }
}
