package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.entity.Genre;
import com.github.librarymanagementsystem.entity.Platform;

import java.util.List;

public interface PlatformService {

    List<Platform> listAllPlatforms();
}
