package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemStatus;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.mapper.BookMapper;
import com.github.librarymanagementsystem.repo.BookRepo;
import com.github.librarymanagementsystem.repo.ItemRepo;
import com.github.librarymanagementsystem.repo.ItemStatusRepo;
import com.github.librarymanagementsystem.repo.ItemTypeRepo;
import com.github.librarymanagementsystem.service.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepo bookRepo;

    private BookMapper bookMapper;

    private ItemRepo itemRepo;

    private ItemTypeRepo itemTypeRepo;

    private ItemStatusRepo itemStatusRepo;

    public BookServiceImpl (BookRepo bookRepo, BookMapper bookMapper, ItemRepo itemRepo, ItemTypeRepo itemTypeRepo, ItemStatusRepo itemStatusRepo) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
        this.itemStatusRepo = itemStatusRepo;
    }
    @Override
    public List<BookDTO> listAllBooks() {
        List<Book> bookList = bookRepo.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (Book book: bookList) {
            BookDTO bookDTO = bookMapper.mapBookDetails(book);

            Optional<ItemType> bookItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("book")).findFirst();

            List<Item> itemList = itemRepo.findByItemTypeIdAndMediaId(bookItemType.get().getId(), book.getId());

            bookDTO.setItems(itemList);

            bookDTOList.add(bookDTO);
        }

        return bookDTOList;
    }

    @Override
    public Book getBookById(Long bookId) {
        Optional<Book> bookResult = bookRepo.findById(bookId);

        Book book = null;
        if (bookResult.isPresent()) {
            book = bookResult.get();
        }

        return book;
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Book book = bookMapper.mapBookDTODetails(bookDTO);

        book = bookRepo.save(book);

        Optional<ItemType> bookItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("book")).findFirst();
        Optional<ItemStatus> availableItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Available")).findFirst();

        for (int i = 1; i <= bookDTO.getNoOfCopies(); i++) {
            Item item = new Item();
            item.setMediaId(book.getId());
            item.setItemType(bookItemType.get());
            item.setItemStatus(availableItemStatus.get());
            itemRepo.save(item);
        }

        return book;
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }
}
