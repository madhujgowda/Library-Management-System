package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.UserDTO;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.entity.Movie;
import com.github.librarymanagementsystem.entity.User;
import com.github.librarymanagementsystem.mapper.UserMapper;
import com.github.librarymanagementsystem.repo.UserRepo;
import com.github.librarymanagementsystem.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    private UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepo.findAll();

        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user: userList) {
            UserDTO userDTO = userMapper.mapUserDetails(user);

            userDTOList.add(userDTO);
        }

        return userDTOList;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> userResult = userRepo.findById(userId);

        UserDTO userDTO = null;
        if (userResult.isPresent()) {
            userDTO = userMapper.mapUserDetails(userResult.get());
        }

        return userDTO;
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userResult = userRepo.findById(user.getId());

        if (userResult.isPresent()) {
            user.setPassword(userResult.get().getPassword());
            user = userRepo.save(user);
        }

        return user;
    }
}
