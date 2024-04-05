package com.dnxo.todoxo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnxo.todoxo.persistence.dto.UserDTO;
import com.dnxo.todoxo.service.UserService;
import com.dnxo.todoxo.service.dto.user.CreateUserDto;
import com.dnxo.todoxo.service.dto.user.LoginUsersDto;
import com.dnxo.todoxo.service.dto.user.UpdatePasswordDto;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        if (!this.userService.existsByUsernameAndEmail(dto.getUsername(), dto.getEmail())) {
            this.userService.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginUsersDto dto) {
        return new ResponseEntity<>(this.userService.loginUser(dto), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<UserDTO> getMethodName(@PathVariable("id") int userId) {
        if (this.userService.existsById(userId)) {
            return ResponseEntity.ok(this.userService.getUser(userId).orElse(null));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody UpdatePasswordDto dto) {
        if (this.userService.existsByUserIdAndEmail(dto.getId(), dto.getEmail())) {
            this.userService.changePassword(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
