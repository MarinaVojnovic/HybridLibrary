package rs.hybridit.service;

import java.util.Optional;
import rs.hybridit.exception.InvalidIdException;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;

public interface BookRentService {

	BookCopy rentBookCopy(Long bookId);

	Optional<BookCopy> returnBookCopy(Long bookCopyId);

	BookCopy getBookCopy(Book book);


}
