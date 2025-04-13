package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.entity.Platform;
import com.github.librarymanagementsystem.repo.PlatformRepo;
import com.github.librarymanagementsystem.service.interfaces.PlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements PlatformService {
    private PlatformRepo platformRepo;

    public PlatformServiceImpl (PlatformRepo platformRepo) {
        this.platformRepo = platformRepo;
    }
    @Override
    public List<Platform> listAllPlatforms() {
        return platformRepo.findAll();
    }
}
