package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.LoginRequest;
import com.github.librarymanagementsystem.dto.LoginResponse;
import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.service.interfaces.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return adminService.login(loginRequest);
    }
}
