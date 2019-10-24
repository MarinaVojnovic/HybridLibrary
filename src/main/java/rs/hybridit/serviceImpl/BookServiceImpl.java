package rs.hybridit.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.hybridit.model.Book;
import rs.hybridit.repository.BookRepository;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book getOne(long id) {
		return bookRepository.getOne(id);
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

