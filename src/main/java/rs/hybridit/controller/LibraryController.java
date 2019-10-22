package rs.hybridit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.service.LibraryService;
import rs.hybridit.serviceImpl.LibraryServiceImpl;

@RestController
public class LibraryController {

    @Autowired
    LibraryService libraryService;
}
