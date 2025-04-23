package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.User;
import com.github.librarymanagementsystem.entity.UserType;
import com.github.librarymanagementsystem.repo.UserTypeRepo;
import com.github.librarymanagementsystem.service.interfaces.UserTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    private UserTypeRepo userTypeRepo;

    public UserTypeServiceImpl(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }
    @Override
    public List<UserType> listAllUserTypes() {

        return userTypeRepo.findAll();
    }
}
