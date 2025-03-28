package com.example.taskmanager.infrastructure;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// La interfaz R2dbcRepository proporciona operaciones CRUD reactivas
public interface ReactiveTaskRepository extends R2dbcRepository<Task, String>, TaskRepository {
    // Puedes definir consultas adicionales si fuera necesario
}
