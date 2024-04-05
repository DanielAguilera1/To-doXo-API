package com.dnxo.todoxo.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dnxo.todoxo.persistence.entity.UserEntity;
import com.dnxo.todoxo.service.dto.user.CreateUserDto;
import com.dnxo.todoxo.service.dto.user.LoginUsersDto;
import com.dnxo.todoxo.service.dto.user.UpdatePasswordDto;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {

        @Query(value = "SELECT EXISTS (SELECT email, password FROM users " +
                        "WHERE email = :#{#loginUsersDto.email} " +
                        "AND password = crypt(:#{#loginUsersDto.password}, password)) as exist", nativeQuery = true)
        boolean login(@Param("loginUsersDto") LoginUsersDto loginUsersDto);

        @Query(value = "UPDATE users " +
                        "SET password = crypt(:#{#updatePasswordDto.newPassword}, gen_salt('bf')) " +
                        "WHERE email = :#{#updatePasswordDto.email} " +
                        "AND password = crypt(:#{#updatePasswordDto.oldPassword}, password)", nativeQuery = true)
        @Modifying
        void changePassword(@Param("updatePasswordDto") UpdatePasswordDto updatePasswordDto);

        boolean existsByUserId(int userId);

        @Query(value = "INSERT INTO users(username, email, password) " +
                        "VALUES (:#{#createUserDto.username}, :#{#createUserDto.email}, " +
                        "crypt(:#{#createUserDto.password}, gen_salt('bf')))", nativeQuery = true)
        @Modifying
        void createUserDto(@Param("createUserDto") CreateUserDto createUserDto);

        boolean existsByEmail(String email);

        boolean existsByUserIdAndEmail(int userId, String email);

        boolean existsByUsernameAndEmail(String username, String email);

        boolean existsByUsername(String username);
}