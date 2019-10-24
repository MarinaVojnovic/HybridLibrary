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
import rs.hybridit.dto.BookCopyDto;
import rs.hybridit.dto.BookDto;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.service.BookCopyService;

@RestController
@RequestMapping(value = "/bookCopies", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookCopyController {

	private BookCopyService bookCopyService;

	public BookCopyController(BookCopyService bookCopyService) {
		this.bookCopyService = bookCopyService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public BookCopy create(@RequestBody BookCopyDto bookCopyDto) {
		return bookCopyService.create(new BookCopy(bookCopyDto));
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BookCopy getBookCopy(@PathVariable Long id) {
		BookCopy bookCopy = bookCopyService.getOne(id);
		return bookCopy;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<BookCopy> getBookCopys() {
		return bookCopyService.getAll();
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public BookCopy updateBookCopy(@PathVariable Long id, @RequestBody BookCopyDto bookCopyDto) {
		BookCopy bookCopy = bookCopyService.getOne(id);
		if (bookCopy != null) {
			bookCopy.setRentStart(bookCopyDto.getRentStart());
			bookCopy.setRentEnd(bookCopyDto.getRentEnd());
		}
		return bookCopy;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BookCopy deleteBookCopy(@PathVariable Long id) {
		BookCopy bookCopy = bookCopyService.getOne(id);
		bookCopyService.delete(bookCopy);
		return bookCopy;
	}
}
