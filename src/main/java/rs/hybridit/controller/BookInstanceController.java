package rs.hybridit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.service.BookInstanceService;
import rs.hybridit.serviceImpl.BookInstanceServiceImpl;

@RestController
public class BookInstanceController {

    @Autowired
    BookInstanceService bookInstanceService;
}
