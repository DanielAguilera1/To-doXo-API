package com.dnxo.todoxo.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dnxo.todoxo.persistence.dto.UserDTO;
import com.dnxo.todoxo.persistence.entity.UserEntity;
import com.dnxo.todoxo.service.dto.user.CreateUserDto;

@Mapper(componentModel = "spring", uses = { TaskMapper.class })
public interface UserMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "taskEntities", target = "tasks")
    UserDTO toUserDTO(UserEntity userEntity);

    List<UserDTO> toUsersDTO(List<UserEntity> userEntity);

    @InheritInverseConfiguration
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(UserDTO userDTO);

    Object toUserEntity(CreateUserDto userDto);
}
