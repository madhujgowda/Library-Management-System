package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.entity.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> listAllPublishers();

    Publisher getPublisherById(Long publisherId);

    Publisher addPublisher(Publisher publisher);

    Publisher updatePublisher(Publisher publisher);

    String deletePublisher(Long publisherId);

    long countAllPublishers();
}
