package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    User addUser(User user);
}
