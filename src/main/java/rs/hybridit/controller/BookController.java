package rs.hybridit.controller;


import java.util.List;
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
import rs.hybridit.dto.BookDto;
import rs.hybridit.dto.LibraryDto;
import rs.hybridit.model.Book;
import rs.hybridit.model.Library;
import rs.hybridit.service.BookService;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody BookDto bookDto) {
		Book book = bookService.create(new Book(bookDto));
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getBook(@PathVariable Long id) {
		Book book = bookService.findById(id);
		if (book != null) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<?> getBooks() {
		List<Book> books = bookService.getAll();
		if (books != null) {
			return new ResponseEntity<>(books, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
		Book book = bookService.findById(id);
		if (book != null) {
			book.setName(bookDto.getName());
			book.setAuthor(bookDto.getAuthor());
			book.setIsbn(bookDto.getIsbn());
			book.setRentingCounter(bookDto.getRentingCounter());
			book.setImage(bookDto.getImage());
			book.setBookCopies(bookDto.getBookCopies());
			return new ResponseEntity<>(book, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		Book book = bookService.findById(id);
		if (book != null) {
			bookService.delete(book);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
