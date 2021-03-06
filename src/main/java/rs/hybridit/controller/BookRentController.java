package rs.hybridit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.model.ReportCurrentlyRentedBooks;
import rs.hybridit.model.ReportFrequency;
import rs.hybridit.service.BookRentService;

@RestController
@RequestMapping(value = "/bookRent", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookRentController {

	private BookRentService bookRentService;

	public BookRentController(BookRentService bookRentService) {
		this.bookRentService = bookRentService;
	}

	@GetMapping(value = "/{bookId}")
	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> rentBookCopy(Long bookId) {
		return new ResponseEntity<>(bookRentService.rentBookCopy(bookId), HttpStatus.OK);
	}

	@PutMapping(value = "/return/{bookCopyId}")
	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> returnBookCopy(Long bookCopyId) {
		return new ResponseEntity<>(bookRentService.returnBookCopy(bookCopyId), HttpStatus.OK);
	}

	@GetMapping(value = "/statistics")
	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<ReportFrequency>> getRentingStatistics() {
		return new ResponseEntity<>(bookRentService.getRentingStatistics(), HttpStatus.OK);
	}

	@GetMapping(value = "/currentlyRentedBooks")
	@PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<ReportCurrentlyRentedBooks>> getCurrentlyRentedBooks() {
		return new ResponseEntity<>(bookRentService.getCurrentlyRentedBooksReport(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteBook/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		Boolean result = bookRentService.deleteBookCopy(id);
		if (result == true){
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return ResponseEntity.badRequest().body("Book with given id cannot be deleted because there are copies rented..");
		}
	}

}
