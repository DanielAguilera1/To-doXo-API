package com.dnxo.todoxo.domain.service;

import com.dnxo.todoxo.domain.Task;
import com.dnxo.todoxo.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    public Optional<Task> getTaskById (int taskId) {
        return taskRepository.getTaskById(taskId);
    }

    public Optional<List<Task>> getTasksByUser(int userId) {
        return taskRepository.getTasksByUser(userId);
    }

    public Optional<List<Task>> getTasksByUserCompleted(int userId) {
        return taskRepository.getTasksByUserCompleted(userId);
    }

    public Optional<List<Task>> getTasksByUserPending(int userId) {
        return taskRepository.getTasksByUserPending(userId);
    }

    public Task save (Task task) {
        return taskRepository.save(task);
    }

    public boolean delete(int taskId) {
       return getTaskById(taskId).map(task -> {
           taskRepository.delete(taskId);
           return true;
       }).orElse(false);
    }
}
