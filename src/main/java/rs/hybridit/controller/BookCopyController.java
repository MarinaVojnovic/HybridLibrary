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
	public ResponseEntity<BookCopy> create(@RequestBody BookCopyDto bookCopyDto) {
		BookCopy bookCopy = new BookCopy(bookCopyDto);
		return new ResponseEntity<>(bookCopy, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getBookCopy(@PathVariable Long id) {
		BookCopy bookCopy = bookCopyService.findById(id);
		if (bookCopy != null) {
			return new ResponseEntity<>(bookCopy, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Book copy with given id does not exist");
		}
	}

	@GetMapping
	public ResponseEntity<List<BookCopy>> getBookCopys() {
		List<BookCopy> bookCopies = bookCopyService.getAll();
		return new ResponseEntity<>(bookCopies, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateBookCopy(@PathVariable Long id, @RequestBody BookCopyDto bookCopyDto) {
		BookCopy bookCopy = bookCopyService.findById(id);
		if (bookCopy != null) {
			bookCopy.setRentStart(bookCopyDto.getRentStart());
			bookCopy.setRentEnd(bookCopyDto.getRentEnd());
			return new ResponseEntity<>(bookCopy, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body("Book copy with given id does not exist");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteBookCopy(@PathVariable Long id) {
		BookCopy bookCopy = bookCopyService.findById(id);
		if (bookCopy != null) {
			bookCopyService.delete(bookCopy);
			return new ResponseEntity<>(bookCopy, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Book copy with given id does not exist");
		}

	}

}
