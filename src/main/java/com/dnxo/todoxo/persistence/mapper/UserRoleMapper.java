package com.dnxo.todoxo.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;

import com.dnxo.todoxo.persistence.dto.UserRoleDTO;
import com.dnxo.todoxo.persistence.entity.UserRoleEntity;

public interface UserRoleMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "grantedDate", target = "grantedDate")
    UserRoleDTO toUserRoleDTO(UserRoleEntity userRoleEntity);

    @InheritInverseConfiguration
    @Mapping(target = "user", ignore = true)
    UserRoleEntity toUserRoleEntity(UserRoleDTO userRoleDTO);
}
