package com.example.taskmanager.domain.repository;

import com.example.taskmanager.domain.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskRepository {
    Mono<Task> save(Task task);

    Mono<Task> findById(String id);

    Flux<Task> findAll();

    Mono<Void> deleteById(String id);
}
