package rs.hybridit.serviceImpl;

import java.util.List;
import java.util.Optional;
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
		Optional<BookCopy> bookCopyOptional = bookCopyRepository.findById(id);
		if (bookCopyOptional.isPresent()) {
			return bookCopyOptional.get();
		} else {
			return null;
		}
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
