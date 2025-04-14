package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.LoginRequest;
import com.github.librarymanagementsystem.dto.LoginResponse;
import com.github.librarymanagementsystem.entity.Admin;
import com.github.librarymanagementsystem.repo.AdminRepo;
import com.github.librarymanagementsystem.service.interfaces.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepo adminRepo;

    public AdminServiceImpl (AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Admin admin = adminRepo.findByEmail(loginRequest.getEmail());

        if (admin.getPassword().equals(loginRequest.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserId(admin.getId());
            loginResponse.setFirstName(admin.getFirstName());
            loginResponse.setLastName(admin.getLastName());
            loginResponse.setUserType("admin");
            return loginResponse;
        } else {
            return null;
        }
    }
}
