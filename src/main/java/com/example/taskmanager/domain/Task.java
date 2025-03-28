package com.example.taskmanager.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table("tasks")
public class Task implements Persistable<String> {

    @Id
    private String id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dueDate;

    public enum Status {
        PENDING, IN_PROGRESS, COMPLETED
    }

    // Constructor que genera un UUID para el id
    public Task(String title, String description, LocalDateTime dueDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = Status.PENDING;
        this.dueDate = dueDate;
    }

    @Override
    public boolean isNew() {
        // Siempre se considera la entidad como nueva para forzar una inserci\u00f3n
        return true;
    }
}
