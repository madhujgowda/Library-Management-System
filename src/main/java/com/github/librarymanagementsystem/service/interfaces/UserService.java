package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.LoginRequest;
import com.github.librarymanagementsystem.dto.LoginResponse;
import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    UserDTO getUserById(Long userId);

    User addUser(User user);

    User updateUser(User user);

    LoginResponse login(LoginRequest loginRequest);
}
