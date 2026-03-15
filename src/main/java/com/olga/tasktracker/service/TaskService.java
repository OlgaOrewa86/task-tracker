package com.olga.tasktracker.service;

import com.olga.tasktracker.model.Status;
import com.olga.tasktracker.model.Task;
import com.olga.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task create(Task task) {
        task.setStatus(Status.TODO); // keep this if you want default status
        return repo.save(task);      // timestamps handled by @PrePersist
    }

    public Task update(Long id, Task updated) {
        Task existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());
        return repo.save(existing);  // updatedAt handled by @PreUpdate
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
