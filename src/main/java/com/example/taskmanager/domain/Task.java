package com.example.taskmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDate;

    public Task(String title, String description, LocalDateTime dueDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = Status.PENDING; // default status
        this.dueDate = dueDate;
    }


    public enum Status {
        PENDING, IN_PROGRESS, COMPLETED
    }
}
