package com.dnxo.todoxo.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dnxo.todoxo.persistence.entity.UserEntity;
import com.dnxo.todoxo.service.dto.user.CreateUserDto;
import com.dnxo.todoxo.service.dto.user.UpdatePasswordDto;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {

        // @Query(value = "SELECT EXISTS (SELECT email, password FROM users " +
        // "WHERE email = :#{#loginUsersDto.email} " +
        // "AND password = :#{#loginUsersDto.password}) AS exist", nativeQuery = true)
        // boolean login(@Param("loginUsersDto") LoginUsersDto loginUsersDto);

        @Query(value = "UPDATE users " +
                        "SET password = :#{#updatePasswordDto.newPassword} " +
                        "WHERE email = :#{#updatePasswordDto.email} " +
                        "AND password = :#{#updatePasswordDto.oldPassword}", nativeQuery = true)
        @Modifying
        void changePassword(@Param("updatePasswordDto") UpdatePasswordDto updatePasswordDto);

        boolean existsByUserId(int userId);

        @Query(value = "INSERT INTO users(username, email, password) " +
                        "VALUES (:#{#createUserDto.username}, :#{#createUserDto.email}, " +
                        ":#{#createUserDto.password}) ", nativeQuery = true)
        @Modifying
        void createUserDto(@Param("createUserDto") CreateUserDto createUserDto);

        boolean existsByEmail(String email);

        boolean existsByUserIdAndEmail(int userId, String email);

        boolean existsByUsernameAndEmail(String username, String email);

        boolean existsByUsername(String username);

        Optional<UserEntity> findByUsername(String username);

        Optional<UserEntity> findByEmail(String email);
}