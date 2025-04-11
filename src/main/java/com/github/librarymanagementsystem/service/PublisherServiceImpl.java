package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.entity.Publisher;
import com.github.librarymanagementsystem.repo.PublisherRepo;
import com.github.librarymanagementsystem.service.interfaces.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepo publisherRepo;
    public PublisherServiceImpl(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<Publisher> listAllPublishers() {
        return publisherRepo.findAll();
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
        Optional<Publisher> publisherResult = publisherRepo.findById(publisherId);

        Publisher publisher = null;
        if (publisherResult.isPresent()) {
            publisher = publisherResult.get();
        }

        return publisher;
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @Override
    public String deletePublisher(Long publisherId) {
        Optional<Publisher> publisherResult = publisherRepo.findById(publisherId);

        if (publisherResult.isPresent()) {
            publisherRepo.delete(publisherResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to delete. Please try again");
        }
    }

    @Override
    public long countAllPublishers() {
        return publisherRepo.count();
    }
}
