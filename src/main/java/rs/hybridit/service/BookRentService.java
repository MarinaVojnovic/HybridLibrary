package rs.hybridit.service;

import java.util.Optional;
import rs.hybridit.model.BookCopy;

public interface BookRentService {

	BookCopy rentBookCopy(Long bookId);

	Optional<BookCopy> returnBookCopy(Long bookCopyId);

}
