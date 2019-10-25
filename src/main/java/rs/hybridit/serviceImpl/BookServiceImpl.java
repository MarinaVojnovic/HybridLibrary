package rs.hybridit.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import rs.hybridit.model.Book;
import rs.hybridit.repository.BookRepository;
import rs.hybridit.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book findById(long id) {
		Optional<Book> bookOptional = bookRepository.findById(id);
		if (bookOptional.isPresent()) {
			return bookOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(Book book) {
		bookRepository.delete(book);
	}

	@Override
	public Book findByName(String name) {
		return bookRepository.findByName(name);
	}

}
