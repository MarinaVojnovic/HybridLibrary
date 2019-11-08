package rs.hybridit.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Library> create(@RequestBody @Valid LibraryDto libraryDto) {
		Library library = libraryService.create(new Library(libraryDto));
		return new ResponseEntity<>(library, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getLibrary(@PathVariable Long id) {
		Library library = libraryService.findById(id);
		if (library != null) {
			return new ResponseEntity<>(library, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Library with given id does not exist");
		}
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Library>> getLibraries() {
		List<Library> libraries = libraryService.getAll();
		return new ResponseEntity<>(libraries, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateLibrary(@PathVariable Long id, @RequestBody @Valid LibraryDto libraryDto) {
		Library library = libraryService.findById(id);
		if (library != null) {
			library.setRentPeriod(libraryDto.getRentPeriod());
			return new ResponseEntity<>(library, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Library with given id does not exist");
		}
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteLibrary(@PathVariable Long id) {
		Library library = libraryService.findById(id);
		if (library != null) {
			libraryService.delete(library);
			return new ResponseEntity<>(library, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Library with given id does not exist");
		}
	}

}
