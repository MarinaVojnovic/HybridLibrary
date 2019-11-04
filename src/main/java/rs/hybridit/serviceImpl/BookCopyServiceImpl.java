package rs.hybridit.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.model.Library;
import rs.hybridit.model.User;
import rs.hybridit.repository.BookCopyRepository;
import rs.hybridit.service.BookCopyService;

@Service
public class BookCopyServiceImpl implements BookCopyService {

	private final BookCopyRepository bookCopyRepository;

	public BookCopyServiceImpl(BookCopyRepository bookCopyRepository) {
		this.bookCopyRepository = bookCopyRepository;
	}

	@Override
	public BookCopy findById(long id) {
		return bookCopyRepository.findById(id).orElse(null);
	}

	@Override
	public List<BookCopy> getAll() {
		return bookCopyRepository.findAll();
	}

	@Override
	public BookCopy create(BookCopy book) {
		return bookCopyRepository.save(book);
	}

	@Override
	public void delete(BookCopy book) {
		bookCopyRepository.delete(book);
	}

	@Override
	public List<BookCopy> findByBook(Book book) {
		return bookCopyRepository.findByBook(book);
	}

}
