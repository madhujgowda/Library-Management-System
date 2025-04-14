package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Admin;
import com.github.librarymanagementsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo  extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
}
