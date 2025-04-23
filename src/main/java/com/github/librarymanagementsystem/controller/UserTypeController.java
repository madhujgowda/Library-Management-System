package com.github.librarymanagementsystem.controller;


import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.UserType;
import com.github.librarymanagementsystem.service.interfaces.UserTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userType")
public class UserTypeController {
    private UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UserType> listAllUserTypes() {
        return userTypeService.listAllUserTypes();
    }
}
