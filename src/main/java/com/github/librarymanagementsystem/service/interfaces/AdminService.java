package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.LoginRequest;
import com.github.librarymanagementsystem.dto.LoginResponse;

public interface AdminService {

    LoginResponse login(LoginRequest loginRequest);
}
