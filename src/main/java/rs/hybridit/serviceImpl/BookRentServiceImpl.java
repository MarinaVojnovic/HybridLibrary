package rs.hybridit.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.model.Library;
import rs.hybridit.model.User;
import rs.hybridit.repository.BookCopyRepository;
import rs.hybridit.repository.BookRepository;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.repository.UserRepository;
import rs.hybridit.service.BookRentService;

@Service
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

	@Override
	public BookCopy rentBookCopy(Long bookId) {
		Book book = bookRepository.findById(bookId).orElse(null);
		if (book != null) {
			List<BookCopy> availableBookCopies = findAvailableBookCopies(book);
			if (availableBookCopies.isEmpty()) {
				return null;
			} else {
				BookCopy bookCopy = availableBookCopies.get(0);
				Library library = libraryRepository.findAll().get(0);
				bookCopy.setRentStart(LocalDate.now());
				bookCopy.setRentEnd(LocalDate.now().plusDays(library.getRentPeriod()));
				book.setRentingCounter(book.getRentingCounter() + 1);
				User user = (User) this.userRepository
					.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
				bookCopy.setUser(user);
				return bookCopy;
			}
		}
		return null;
	}

	@Override
	public Optional<BookCopy> returnBookCopy(Long bookCopyId) {
		Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookCopyId);
		if (bookCopy.isPresent()) {
			bookCopy.get().setRentStart(null);
			bookCopy.get().setRentEnd(null);
			bookCopy.get().setUser(null);
		}
		return bookCopy;
	}

	public List<BookCopy> findAvailableBookCopies(Book book) {
		List<BookCopy> bookCopies = bookCopyRepository.findByBook(book);
		return bookCopies.stream().filter(bc -> isAvailable(bc)).collect(Collectors.toList());
	}

	private boolean isAvailable(BookCopy bookCopy) {
		return Objects.isNull(bookCopy.getUser()) && Objects.isNull(bookCopy.getRentStart()) && Objects
			.isNull(bookCopy.getRentEnd());
	}

}
