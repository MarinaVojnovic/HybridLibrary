package rs.hybridit.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.hybridit.model.BookCopy;
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
		return bookCopyRepository.getOne(id);
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

}
