package rs.hybridit.service;

import java.util.List;
import java.util.Optional;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;

public interface BookCopyService {

	BookCopy findById(long id);

	List<BookCopy> getAll();

	BookCopy create(BookCopy book);

	void delete(BookCopy book);

	List<BookCopy> findByBook(Book book);

}
