package com.dnxo.todoxo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnxo.todoxo.persistence.dto.UserDTO;
import com.dnxo.todoxo.persistence.mapper.UserMapper;
import com.dnxo.todoxo.persistence.repository.UserRepository;
import com.dnxo.todoxo.service.dto.user.CreateUserDto;
import com.dnxo.todoxo.service.dto.user.LoginUsersDto;
import com.dnxo.todoxo.service.dto.user.UpdatePasswordDto;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getUsers() {
        return userMapper.toUsersDTO(this.userRepository.findAll());
    }

    public Optional<UserDTO> getUser(int userId) {
        return Optional.of(this.userMapper.toUserDTO(this.userRepository.findById(userId)
                .orElse(null)));
    }

    public boolean existsById(int userId) {
        return this.userRepository.existsById(userId);
    }

    public boolean loginUser(LoginUsersDto dto) {
        return this.userRepository.login(dto);
    }

    public boolean existsByUserIdAndEmail(int userId, String email) {
        return userRepository.existsByUserIdAndEmail(userId, email);
    }

    public boolean existsByUsernameAndEmail(String username, String email) {
        return this.userRepository.existsByUsernameAndEmail(username, email);
    }

    @Transactional
    public void changePassword(UpdatePasswordDto dto) {
        this.userRepository.changePassword(dto);
    }

    @Transactional
    public void createUser(CreateUserDto createUserDto) {
        this.userRepository.createUserDto(createUserDto);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

}
