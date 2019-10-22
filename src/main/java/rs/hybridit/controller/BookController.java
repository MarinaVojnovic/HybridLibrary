package rs.hybridit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.service.BookService;
import rs.hybridit.serviceImpl.BookServiceImpl;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
}
