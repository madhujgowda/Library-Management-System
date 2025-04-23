package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.UserType;

import java.util.List;

public interface UserTypeService {

    List<UserType> listAllUserTypes();
}
