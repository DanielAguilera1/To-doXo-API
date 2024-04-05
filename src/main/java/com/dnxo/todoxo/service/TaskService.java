package com.dnxo.todoxo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnxo.todoxo.persistence.dto.TaskDTO;
import com.dnxo.todoxo.persistence.mapper.TaskMapper;
import com.dnxo.todoxo.persistence.repository.TaskRepository;
import com.dnxo.todoxo.service.dto.task.TaskDto;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final TaskMapper mapper;

    public TaskService(TaskRepository taskRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public List<TaskDTO> getAll() {
        return mapper.toTasksDTO(this.taskRepository.findAll());
    }

    public Optional<TaskDTO> getTaskById(int taskId) {
        return Optional.of(this.mapper.toTaskDTO(this.taskRepository.findById(taskId)
                .orElse(null)));
    }

    public Optional<List<TaskDTO>> getTasksByUser(int userId) {
        return Optional.of(this.mapper.toTasksDTO(this.taskRepository.findByUserId(userId)
                .orElse(null)));
    }

    public Optional<List<TaskDTO>> getTasksByUserCompleted(int userId) {
        return Optional.of(this.mapper.toTasksDTO(this.taskRepository.findByUserIdAndState(userId, true)
                .orElse(null)));
    }

    public Optional<List<TaskDTO>> getTasksByUserPending(int userId) {
        return Optional.of(this.mapper.toTasksDTO(this.taskRepository.findByUserIdAndState(userId, false)
                .orElse(null)));
    }

    public TaskDTO save(TaskDTO taskDTO) {
        return mapper.toTaskDTO(this.taskRepository.save(mapper.toTaskEntity(taskDTO)));
    }

    public boolean exists(int taskId) {
        return this.taskRepository.existsById(taskId);
    }

    public boolean existsByUserIdAndTaskId(int userId, int taskId) {
        return this.taskRepository.existsByUserIdAndTaskId(userId, taskId);
    }

    @Transactional
    public void updateTask(TaskDto dto) {
        this.taskRepository.updateTask(dto);
    }

    public void delete(int taskId) {
        this.taskRepository.deleteById(taskId);
    }

}
