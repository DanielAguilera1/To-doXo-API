package com.dnxo.todoxo.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dnxo.todoxo.persistence.entity.TaskEntity;
import com.dnxo.todoxo.service.dto.task.TaskDto;

@Repository
public interface TaskRepository extends ListCrudRepository<TaskEntity, Integer> {
    Optional<List<TaskEntity>> findByUserId(int userId);

    Optional<List<TaskEntity>> findByUserIdAndState(int userId, boolean state);

    @Query(value = "UPDATE tasks " +
            "SET description = :#{#updateTaskDto.description}, " +
            "date = :#{#updateTaskDto.date}, state = :#{#updateTaskDto.state} " +
            "WHERE task_id = :#{#updateTaskDto.taskId} " +
            "AND user_id = :#{#updateTaskDto.userId}", nativeQuery = true)
    @Modifying
    void updateTask(@Param("updateTaskDto") TaskDto taskDto);

    boolean existsByUserIdAndTaskId(int userId, int taskId);
}