package com.dnxo.todoxo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnxo.todoxo.persistence.dto.UserDTO;
import com.dnxo.todoxo.persistence.entity.UserEntity;
import com.dnxo.todoxo.persistence.entity.UserRoleEntity;
import com.dnxo.todoxo.persistence.mapper.UserMapper;
import com.dnxo.todoxo.persistence.repository.UserRepository;
import com.dnxo.todoxo.service.dto.user.CreateUserDto;
import com.dnxo.todoxo.service.dto.user.LoginUsersDto;
import com.dnxo.todoxo.service.dto.user.UpdatePasswordDto;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
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
        UserEntity userEntity = this.userRepository.findByEmail(dto.getEmail()).orElse(null);
        return userEntity != null && passwordEncoder.matches(dto.getPassword(), userEntity.getPassword());
    }

    public boolean existsByUserIdAndEmail(int userId, String email) {
        return userRepository.existsByUserIdAndEmail(userId, email);
    }

    public boolean existsByUsernameAndEmail(String username, String email) {
        return this.userRepository.existsByUsernameAndEmail(username, email);
    }

    @Transactional
    public boolean changePassword(UpdatePasswordDto dto) {
        UserEntity userEntity = this.userRepository.findByEmail(dto.getEmail()).orElse(null);

        if (passwordEncoder.matches(dto.getOldPassword(), userEntity.getPassword())) {
            this.userRepository.changePassword(dto);
            return true;
        }
        return false;
    }

    @Transactional
    public void createUser(CreateUserDto createUserDto) {
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        this.userRepository.createUserDto(createUserDto);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " Not Found"));

        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole)
                .toArray(String[]::new);

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles)
                .accountLocked(false)
                .disabled(false)
                .build();
    }
}
