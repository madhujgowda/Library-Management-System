package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.mapper.BookMapper;
import com.github.librarymanagementsystem.repo.BookRepo;
import com.github.librarymanagementsystem.repo.ItemRepo;
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

    public BookServiceImpl (BookRepo bookRepo, BookMapper bookMapper, ItemRepo itemRepo, ItemTypeRepo itemTypeRepo) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
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
}
