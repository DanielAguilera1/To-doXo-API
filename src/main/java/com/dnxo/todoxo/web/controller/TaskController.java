package com.dnxo.todoxo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnxo.todoxo.persistence.dto.TaskDTO;
import com.dnxo.todoxo.service.TaskService;
import com.dnxo.todoxo.service.dto.task.TaskDto;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(this.taskService.getAll());
    }

    @PutMapping()
    public ResponseEntity<Void> updateTask(@RequestBody TaskDto dto) {
        if (this.taskService.existsByUserIdAndTaskId(dto.getUserId(), dto.getTaskId())) {
            this.taskService.updateTask(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") int taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId).orElse(null));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByUser(@PathVariable("id") int userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId).orElse(null));
    }

    @GetMapping("/user/true/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserCompleted(@PathVariable("id") int userId) {
        return ResponseEntity.ok(taskService.getTasksByUserCompleted(userId).orElse(null));
    }

    @GetMapping("/user/false/{id}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserPending(@PathVariable("id") int userId) {
        return ResponseEntity.ok(taskService.getTasksByUserPending(userId).orElse(null));
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskDTO) {
        if (!this.taskService.exists(taskDTO.getTaskId())) {
            return ResponseEntity.ok(taskService.save(taskDTO));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int taskId) {
        if (this.taskService.exists(taskId)) {
            this.taskService.delete(taskId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
