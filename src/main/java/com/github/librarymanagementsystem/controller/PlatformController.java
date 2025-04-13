package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.entity.Genre;
import com.github.librarymanagementsystem.entity.Platform;
import com.github.librarymanagementsystem.service.interfaces.PlatformService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    private PlatformService platformService;

    public  PlatformController (PlatformService platformService) {
        this.platformService = platformService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Platform> listAllPlatforms() {
        return platformService.listAllPlatforms();
    }
}
