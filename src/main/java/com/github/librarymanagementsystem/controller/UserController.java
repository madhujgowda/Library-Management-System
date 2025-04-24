package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.LoginRequest;
import com.github.librarymanagementsystem.dto.LoginResponse;
import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.User;
import com.github.librarymanagementsystem.service.interfaces.BookService;
import com.github.librarymanagementsystem.service.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @RequestMapping("/view/{userId}")
    public UserDTO getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        if (user == null) {
            throw new IllegalStateException("Please submit a user to add.");
        }

        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public User updateUser(@RequestBody User user) {
        if (user == null) {
            throw new IllegalStateException("Please submit a user to update.");
        }

        return userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
