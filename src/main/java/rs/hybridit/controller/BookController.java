package rs.hybridit.controller;


import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody BookDto bookDto) {
		return bookService.create(new Book(bookDto));
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book getBook(@PathVariable Long id) {
		Book book = bookService.getOne(id);
		return book;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Book> getBooks() {
		return bookService.getAll();
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Book updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
		Book book = bookService.getOne(id);
		if (book != null) {
			book.setName(bookDto.getName());
			book.setAuthor(bookDto.getAuthor());
			book.setIsbn(bookDto.getIsbn());
			book.setRentingCounter(bookDto.getRentingCounter());
			book.setImage(bookDto.getImage());
			book.setBookCopies(bookDto.getBookCopies());
		}
		return book;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Book deleteBook(@PathVariable Long id) {
		Book book = bookService.getOne(id);
		bookService.delete(book);
		return book;
	}

}
