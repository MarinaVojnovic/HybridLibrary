package rs.hybridit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.hybridit.model.BookCopy;
import rs.hybridit.service.BookRentService;

@RestController
@RequestMapping(value = "/bookRent", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookRentController {

	private BookRentService bookRentService;

	public BookRentController(BookRentService bookRentService) {
		this.bookRentService = bookRentService;
	}

	@GetMapping(value = "/{bookId}")
	@PreAuthorize("hasRole('ADMIN', 'LIBRARIAN')")
	public ResponseEntity<?> rentBookCopy(Long bookId) {
		BookCopy bookCopy = bookRentService.rentBookCopy(bookId);
		if (bookCopy == null) {
			return ResponseEntity.badRequest().body("No available copies");
		} else {
			return new ResponseEntity<>(bookCopy, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/return/{bookCopyId}")
	@PreAuthorize("hasRole('ADMIN', 'LIBRARIAN')")
	public ResponseEntity<?> returnBookCopy(Long bookCopyId) {
		Boolean result = bookRentService.returnBookCopy(bookCopyId);
		if (result == true) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Invalid book copy id given.");
		}
	}

}
