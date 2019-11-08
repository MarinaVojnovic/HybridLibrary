package rs.hybridit.service;

import java.util.List;
import java.util.Optional;
import rs.hybridit.exception.InvalidIdException;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.model.ReportCurrentlyRentedBooks;
import rs.hybridit.model.ReportFrequency;

public interface BookRentService {

	BookCopy rentBookCopy(Long bookId);

	Optional<BookCopy> returnBookCopy(Long bookCopyId);

	BookCopy getBookCopy(Book book);

	List<ReportFrequency> getRentingStatistics();

	List<ReportCurrentlyRentedBooks> getCurrentlyRentedBooksReport();

	List<Book> findAllSortedRentingCounter();

	public Boolean deleteBookCopy(Long id);

}
