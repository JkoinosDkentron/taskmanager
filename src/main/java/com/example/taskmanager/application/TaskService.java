package com.example.taskmanager.application;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.infrastructure.ReactiveTaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
    private final ReactiveTaskRepository repository;

    public TaskService(ReactiveTaskRepository repository) {
        this.repository = repository;
    }

    public Mono<Task> createTask(String title, String description, java.time.LocalDateTime dueDate) {
        Task task = new Task(title, description, dueDate);
        return repository.save(task);
    }

    public Mono<Task> updateTaskStatus(String id, Task.Status newStatus) {
        return repository.findById(id)
                .flatMap(task -> {
                    task.setStatus(newStatus);
                    return repository.save(task);
                });
    }

    public Flux<Task> getAllTasks() {
        return repository.findAll();
    }

    public Mono<Void> deleteTask(String id) {
        return repository.deleteById(id);
    }
}
