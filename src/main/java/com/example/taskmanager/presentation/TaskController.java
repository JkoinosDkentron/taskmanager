package com.example.taskmanager.presentation;

import com.example.taskmanager.application.TaskService;
import com.example.taskmanager.domain.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Task> createTask(@RequestBody TaskDTO taskDTO) {
        return service.createTask(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDueDate());
    }

    @GetMapping
    public Flux<Task> getTasks() {
        return service.getAllTasks();
    }

    @PatchMapping("/{id}/status")
    public Mono<Task> updateStatus(@PathVariable String id, @RequestParam Task.Status status) {
        return service.updateTaskStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable String id) {
        return service.deleteTask(id);
    }
}

// DTO for creating a Task
@Setter
@Getter
class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;

}
