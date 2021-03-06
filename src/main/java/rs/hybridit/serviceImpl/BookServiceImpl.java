package rs.hybridit.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
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
		return bookRepository.findById(id).orElse(null);
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

	@Override
	public List findAllOrderByRentingCounterDesc() {
		return bookRepository.findAll(new Sort(Sort.Direction.ASC, "rentingCounter"));
	}

}

