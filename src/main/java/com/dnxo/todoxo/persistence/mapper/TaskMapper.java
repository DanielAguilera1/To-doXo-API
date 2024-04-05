package com.dnxo.todoxo.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.dnxo.todoxo.persistence.dto.TaskDTO;
import com.dnxo.todoxo.persistence.entity.TaskEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mappings({
            @Mapping(source = "taskId", target = "taskId"),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "state", target = "state"),
    })
    TaskDTO toTaskDTO(TaskEntity taskEntity);

    List<TaskDTO> toTasksDTO(List<TaskEntity> taskEntities);

    @InheritInverseConfiguration()
    @Mapping(target = "userEntity", ignore = true)
    TaskEntity toTaskEntity(TaskDTO taskDTO);
}
