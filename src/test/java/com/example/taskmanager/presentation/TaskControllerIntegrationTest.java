package com.example.taskmanager.presentation;

import com.example.taskmanager.TaskmanagerApplication;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.infrastructure.ReactiveTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@SpringBootTest(classes = TaskmanagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class TaskControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveTaskRepository repository;

    @BeforeEach
    public void setup() {
        // Clean the repository before each test
        repository.deleteAll().block();
    }

    @Test
    public void createTaskIntegrationTest() {
        // Create a TaskDTO payload
        String payload = """
                {
                  "title": "Integration Test Task",
                  "description": "Testing integration",
                  "dueDate": "2025-04-30T12:00:00"
                }
                """;

        webTestClient.post().uri("/tasks")
                .header("Content-Type", "application/json")
                .body(Mono.just(payload), String.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.title").isEqualTo("Integration Test Task");
    }

    @Test
    public void getTasksIntegrationTest() {
        // Pre-create a task in the repository
        Task task = new Task("Pre-created Task", "Task created for test", LocalDateTime.now().plusDays(1));
        repository.save(task).block();

        webTestClient.get().uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Task.class)
                .hasSize(1);
    }
}
