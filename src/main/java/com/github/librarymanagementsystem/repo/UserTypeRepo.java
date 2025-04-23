package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.User;
import com.github.librarymanagementsystem.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepo extends JpaRepository<UserType, Long> {
}
