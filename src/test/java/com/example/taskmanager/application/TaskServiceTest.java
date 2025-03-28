package com.example.taskmanager.application;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.infrastructure.ReactiveTaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

public class TaskServiceTest {

    @Test
    public void createTask_ShouldReturnCreatedTask() {
        // Arrange
        ReactiveTaskRepository repository = Mockito.mock(ReactiveTaskRepository.class);
        TaskService service = new TaskService(repository);
        String title = "Test Task";
        String description = "This is a test";
        LocalDateTime dueDate = LocalDateTime.now().plusDays(1);
        Task task = new Task(title, description, dueDate);

        Mockito.when(repository.save(Mockito.any(Task.class))).thenReturn(Mono.just(task));

        // Act
        Mono<Task> createdTask = service.createTask(title, description, dueDate);

        // Assert
        StepVerifier.create(createdTask)
                .expectNextMatches(t -> t.getTitle().equals(title))
                .verifyComplete();
    }
}
