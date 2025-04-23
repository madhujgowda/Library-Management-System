package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
}
