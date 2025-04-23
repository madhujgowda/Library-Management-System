package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.service.interfaces.BookService;
import com.github.librarymanagementsystem.service.interfaces.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UserDTO> listAllUsers() {
        return userService.listAllUsers();
    }
}
