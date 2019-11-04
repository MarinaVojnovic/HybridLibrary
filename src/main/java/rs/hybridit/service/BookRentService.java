package rs.hybridit.service;

import rs.hybridit.model.BookCopy;

public interface BookRentService {

	BookCopy rentBookCopy(Long bookId);

	Boolean returnBookCopy(Long bookCopyId);

}
