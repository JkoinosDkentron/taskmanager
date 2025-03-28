CREATE TABLE tasks
(
    id          VARCHAR(36) PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(255),
    status      VARCHAR(50),
    due_date    TIMESTAMP
);
