package com.example.taskmanager.infrastructure;

import com.example.taskmanager.domain.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveTaskRepository extends ReactiveCrudRepository<Task, String> {
    // Additional custom queries if necessary.
}
