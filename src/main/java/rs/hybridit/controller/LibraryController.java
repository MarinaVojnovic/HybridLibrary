package rs.hybridit.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.dto.LibraryDto;
import rs.hybridit.model.Library;
import rs.hybridit.service.LibraryService;

@RestController
public class LibraryController {

	private final LibraryService libraryService;

	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@PostMapping(value = "/createLibrary", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public Library create(@RequestBody LibraryDto libraryDto) {
		return libraryService.create(new Library(libraryDto));

	}
	@GetMapping(value = "/getLibrary/{id}")
    @ResponseStatus(HttpStatus.OK)
	public Library getLibrary(@PathVariable Long id) {
		Library library = libraryService.getOne(id);
		return library;
	}

    @GetMapping("/libraries")
    public List<Library> getLibraries() {
        return libraryService.getAll();
    }

	@PutMapping(value = "/updateLibrary", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
        MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public Library updateLibrary(@RequestBody LibraryDto libraryDto) {
		Library library = libraryService.getOne(libraryDto.getId());
		if (library != null) {
            library.setRentPeriod(libraryDto.getRentPeriod());
		}
		return library;
	}

	@DeleteMapping(value = "/deleteLibrary/{id}")
    @ResponseStatus(HttpStatus.OK)
	public Library deleteLibrary(@PathVariable Long id) {
		Library library = libraryService.getOne(id);
		libraryService.delete(library);
		return library;
	}


}
