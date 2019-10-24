package rs.hybridit.service;

import java.util.List;
import rs.hybridit.model.BookCopy;

public interface BookCopyService {

	BookCopy findById(long id);

	List<BookCopy> getAll();

	BookCopy create(BookCopy book);

	void delete(BookCopy book);

}
