package com.github.librarymanagementsystem.controller;


import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.entity.Publisher;
import com.github.librarymanagementsystem.service.interfaces.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController (PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Publisher> listAllPublishers() {
        return publisherService.listAllPublishers();
    }

    @ResponseBody
    @RequestMapping("/view/{publisherId}")
    public Publisher getPublisherById(@PathVariable("publisherId") Long publisherId) {
        return publisherService.getPublisherById(publisherId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        if (publisher == null) {
            throw new IllegalStateException("Please submit a publisher to add.");
        }

        return publisherService.addPublisher(publisher);
    }

    @ResponseBody
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Publisher updatePublisher(@RequestBody Publisher publisher) {
        if (publisher == null) {
            throw new IllegalStateException("Please submit a publisher to update.");
        }

        return publisherService.updatePublisher(publisher);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{publisherId}", method = RequestMethod.DELETE)
    public String deletePublisher(@PathVariable("publisherId") Long publisherId) {
        return publisherService.deletePublisher(publisherId);
    }

    @ResponseBody
    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public long countAllPublishers() {
        return publisherService.countAllPublishers();
    }
}
