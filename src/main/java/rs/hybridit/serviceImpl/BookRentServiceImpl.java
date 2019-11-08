package rs.hybridit.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.hybridit.exception.InvalidIdException;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.model.Library;
import rs.hybridit.model.ReportCurrentlyRentedBooks;
import rs.hybridit.model.ReportFrequency;
import rs.hybridit.model.User;
import rs.hybridit.repository.BookCopyRepository;
import rs.hybridit.repository.BookRepository;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.service.BookRentService;

@Service
@Slf4j
public class BookRentServiceImpl implements BookRentService {

	private final BookCopyRepository bookCopyRepository;

	private final BookRepository bookRepository;

	private final LibraryRepository libraryRepository;

	private final UserRepository userRepository;


	public BookRentServiceImpl(BookCopyRepository bookCopyRepository, BookRepository bookRepository,
		LibraryRepository libraryRepository, UserRepository userRepository) {
		this.bookCopyRepository = bookCopyRepository;
		this.bookRepository = bookRepository;
		this.libraryRepository = libraryRepository;
		this.userRepository = userRepository;
	}

	public BookCopy rentBookCopy(Long bookId) {
		return bookRepository.findById(bookId).map(this::getBookCopy).orElseThrow(() -> {
			throw new InvalidIdException("Book with given id " + bookId + " does not exist.");
		});
	}

	@Override
	public BookCopy getBookCopy(Book book) {
		List<BookCopy> availableBookCopies = findAvailableBookCopies(book);
		if (availableBookCopies.isEmpty()) {
			throw new InvalidIdException("Available book with given id " + book.getId() + " does not exist.");
		} else {
			BookCopy bookCopy = availableBookCopies.get(0);
			Library library = libraryRepository.findAll().get(0);
			bookCopy.setRentStart(LocalDate.now());
			bookCopy.setRentEnd(LocalDate.now().plusDays(library.getRentPeriod()));
			book.increaseCounter();
			User user = this.userRepository
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			bookCopy.setUser(user);
			bookCopyRepository.save(bookCopy);
			return bookCopy;
		}
	}

	@Override
	public Optional<BookCopy> returnBookCopy(Long bookCopyId) {
		return bookCopyRepository.findById(bookCopyId).map(bookCopy -> {
			bookCopy.setRentStart(null);
			bookCopy.setRentEnd(null);
			bookCopy.setUser(null);
			this.bookCopyRepository.save(bookCopy);
			return Optional.of(bookCopy);
		}).orElseThrow(() -> {
			throw new InvalidIdException("Book with given id " + bookCopyId + " does not exist.");
		});
	}

	@Override
	public List<ReportFrequency> getRentingStatistics() {
		List<Book> books = findAllSortedRentingCounter();
		return books.stream().map(this::makeReport).collect(Collectors.toList());
	}

	public ReportFrequency makeReport(Book book) {
		return new ReportFrequency(book.getName(), book.getRentingCounter());
	}

	@Override
	public List<ReportCurrentlyRentedBooks> getCurrentlyRentedBooksReport() {
		return bookRepository.findAllRented().stream()
			.map(this::currentlyRentedBook)
			.collect(Collectors.toList());
	}

	public ReportCurrentlyRentedBooks currentlyRentedBook(Book book) {
		List<BookCopy> bookCopies = bookCopyRepository.findByBook(book);
		int available = bookCopies.stream().filter(bc -> isAvailable(bc)).collect(Collectors.toList()).size();
		return new ReportCurrentlyRentedBooks(book.getName(), bookCopies.size() - available, available);
	}

	public List<BookCopy> findAvailableBookCopies(Book book) {
		List<BookCopy> bookCopies = bookCopyRepository.findByBook(book);
		return bookCopies.stream().filter(bc -> isAvailable(bc)).collect(Collectors.toList());
	}

	private boolean isAvailable(BookCopy bookCopy) {
		return Objects.isNull(bookCopy.getUser()) && Objects.isNull(bookCopy.getRentStart()) && Objects
			.isNull(bookCopy.getRentEnd());
	}

	public List<Book> findAllSortedRentingCounter() {
		return bookRepository.findAll(sortByIdAsc());
	}

	private Sort sortByIdAsc() {
		return new Sort(Sort.Direction.ASC, "rentingCounter");
	}

	public Boolean deleteBookCopy(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			List<BookCopy> bookCopies = bookCopyRepository.findByBook(book.get());
			List<BookCopy> availableBookCopies = findAvailableBookCopies(book.get());
			if (bookCopies.size()==availableBookCopies.size()){
				bookRepository.delete(book.get());
				return true;
			}
		} else {
			 throw new InvalidIdException("Book with given id " + id + " does not exist.");
		}
		return false;
	}

}
