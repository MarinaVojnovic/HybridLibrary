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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.dto.LibraryDto;
import rs.hybridit.model.Library;
import rs.hybridit.service.LibraryService;

@RestController
@RequestMapping(value = "/libraries", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibraryController {

	private LibraryService libraryService;

	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody LibraryDto libraryDto) {
		Library library = libraryService.create(new Library(libraryDto));
		return new ResponseEntity<>(library, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getLibrary(@PathVariable Long id) {
		Library library = libraryService.getOne(id);
		if (library != null) {
			return new ResponseEntity<>(library, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping
	public ResponseEntity<?> getLibraries() {
		List<Library> libraries = libraryService.getAll();
		if (libraries != null) {
			return new ResponseEntity<>(libraries, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateLibrary(@PathVariable Long id, @RequestBody LibraryDto libraryDto) {
		Library library = libraryService.getOne(id);
		if (library != null) {
			library.setRentPeriod(libraryDto.getRentPeriod());
			return new ResponseEntity<>(library, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteLibrary(@PathVariable Long id) {
		Library library = libraryService.getOne(id);
		if (library != null) {
			libraryService.delete(library);
			return new ResponseEntity<>(library, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
