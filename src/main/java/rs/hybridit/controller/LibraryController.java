package rs.hybridit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.service.LibraryService;

@RestController
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(final LibraryService libraryService) {

        this.libraryService = libraryService;

    }
}
