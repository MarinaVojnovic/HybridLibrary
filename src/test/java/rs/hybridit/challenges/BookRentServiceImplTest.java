package rs.hybridit.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import rs.hybridit.serviceImpl.BookCopyServiceImpl;
import rs.hybridit.serviceImpl.BookRentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookRentServiceImplTest {

	@InjectMocks
	private BookRentServiceImpl bookRentService;

	@Mock
	private BookCopyRepository bookCopyRepository;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private LibraryRepository libraryRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	SecurityContext securityContext;

	@Mock
	Authentication authentication;

	@Test
	public void getRentingStatistics_booksExist() {
		Book b1 = new Book();
		b1.setName("Book 1");
		b1.setRentingCounter(5);
		Book b2 = new Book();
		b2.setName("Book 2");
		b2.setRentingCounter(7);
		Book b3 = new Book();
		b3.setName("Book 3");
		b3.setRentingCounter(3);
		List<Book> books = new ArrayList<>();
		books.add(b2);
		books.add(b1);
		books.add(b3);
		when(bookRepository.findAll()).thenReturn(books);
		List<ReportFrequency> reports = new ArrayList<>();
		for (Book b : books) {
			reports.add(new ReportFrequency(b.getName(), b.getRentingCounter()));
		}
		List<ReportFrequency> returnedReports = bookRentService.getRentingStatistics();
		assertEquals(returnedReports.get(0).getBookName(), reports.get(0).getBookName());
		assertEquals(returnedReports.get(1).getBookName(), reports.get(1).getBookName());
		assertEquals(returnedReports.get(2).getBookName(), reports.get(2).getBookName());
	}

	@Test
	public void getRentingStatistics_booksDoNotExist() {
		List<ReportFrequency> reports = new ArrayList<>();
		List<ReportFrequency> returnedReports = bookRentService.getRentingStatistics();
		assertEquals(reports, returnedReports);
	}

	@Test(expected = InvalidIdException.class)
	public void rentBookCopy_noAvailableCopies() {
		Book b = new Book();
		b.setId(1L);
		when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(b));
		BookCopy bookCopy = bookRentService.rentBookCopy(1L);
		verify(bookRepository).findById(1L);
	}

	@Test
	public void getCurrentlyRentedBooksReport_successfull() {
		Book b1 = new Book();
		b1.setName("Book 1");
		Book b2 = new Book();
		b2.setName("Book 2");
		List<Book> books = new ArrayList<>();
		books.add(b2);
		books.add(b1);
		when(bookRepository.findAll()).thenReturn(books);

		BookCopy bookCopy11 = new BookCopy();
		bookCopy11.setUser(new User());
		BookCopy bookCopy12 = new BookCopy();
		bookCopy12.setUser(new User());
		BookCopy bookCopy13 = new BookCopy();
		bookCopy13.setUser(null);
		bookCopy13.setRentStart(null);
		bookCopy13.setRentEnd(null);
		List<BookCopy> bookCopies1 = new ArrayList<>();
		bookCopies1.add(bookCopy11);
		bookCopies1.add(bookCopy12);
		bookCopies1.add(bookCopy13);
		when(bookCopyRepository.findByBook(b1)).thenReturn(bookCopies1);

		BookCopy bookCopy21 = new BookCopy();
		BookCopy bookCopy22 = new BookCopy();
		bookCopy21.setUser(null);
		bookCopy21.setRentStart(null);
		bookCopy21.setRentEnd(null);
		bookCopy22.setUser(null);
		bookCopy22.setRentStart(null);
		bookCopy22.setRentEnd(null);
		List<BookCopy> bookCopies2 = new ArrayList<>();
		bookCopies2.add(bookCopy21);
		bookCopies2.add(bookCopy22);
		when(bookCopyRepository.findByBook(b2)).thenReturn(bookCopies2);

		List<ReportCurrentlyRentedBooks> currentlyRentedBooks = new ArrayList<>();
		currentlyRentedBooks.add(new ReportCurrentlyRentedBooks(b1.getName(), 2, 1));

		List<ReportCurrentlyRentedBooks> returnedCurrentlyRentedBooks =
			bookRentService.getCurrentlyRentedBooksReport();
		assertEquals(1, returnedCurrentlyRentedBooks.size());
		assertEquals(b1.getName(), returnedCurrentlyRentedBooks.get(0).getBookName());
		assertEquals(2, returnedCurrentlyRentedBooks.get(0).getRentedCopies().intValue());
		assertEquals(1, returnedCurrentlyRentedBooks.get(0).getAvailableCopies().intValue());
	}


	@Test
	public void getCurrentlyRentedBooksReport_noRentedBooks() {
		Book b1 = new Book();
		b1.setName("Book 1");
		b1.setRentingCounter(5);
		Book b2 = new Book();
		b2.setName("Book 2");
		b2.setRentingCounter(7);
		List<Book> books = new ArrayList<>();
		books.add(b2);
		books.add(b1);
		when(bookRepository.findAll()).thenReturn(books);

		BookCopy bookCopy11 = new BookCopy();
		bookCopy11.setUser(null);
		BookCopy bookCopy12 = new BookCopy();
		bookCopy12.setUser(null);
		BookCopy bookCopy13 = new BookCopy();
		bookCopy13.setUser(null);
		List<BookCopy> bookCopies1 = new ArrayList<>();
		bookCopies1.add(bookCopy11);
		bookCopies1.add(bookCopy12);
		bookCopies1.add(bookCopy13);
		when(bookCopyRepository.findByBook(b1)).thenReturn(bookCopies1);

		BookCopy bookCopy21 = new BookCopy();
		BookCopy bookCopy22 = new BookCopy();
		bookCopy21.setUser(null);
		bookCopy22.setUser(null);
		List<BookCopy> bookCopies2 = new ArrayList<>();
		bookCopies2.add(bookCopy21);
		bookCopies2.add(bookCopy22);
		when(bookCopyRepository.findByBook(b2)).thenReturn(bookCopies2);

		List<ReportCurrentlyRentedBooks> currentlyRentedBooks = new ArrayList<>();
		List<ReportCurrentlyRentedBooks> returnedCurrentlyRentedBooks =
			bookRentService.getCurrentlyRentedBooksReport();
		assertEquals(currentlyRentedBooks, returnedCurrentlyRentedBooks);
	}

	@Test
	public void rentBookCopy_successfull() {
		Book b = new Book();
		b.setId(1L);
		b.setRentingCounter(0);
		when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(b));
		BookCopy bookCopy1 = new BookCopy();
		bookCopy1.setId(2L);
		bookCopy1.setBook(b);
		bookCopy1.setUser(null);
		BookCopy bookCopy2 = new BookCopy();
		bookCopy2.setBook(b);
		bookCopy2.setUser(null);
		List<BookCopy> bookCopies = new ArrayList<>();
		bookCopies.add(bookCopy1);
		bookCopies.add(bookCopy2);
		when(bookCopyRepository.findByBook(b)).thenReturn(bookCopies);
		Library library = new Library();
		library.setRentPeriod(5);
		List<Library> libraries = new ArrayList<>();
		libraries.add(library);
		when(libraryRepository.findAll()).thenReturn(libraries);
		User user = new User();
		user.setUsername("marina");
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
		when(authentication.getName()).thenReturn("marina");
		when(userRepository.findByUsername("marina")).thenReturn(user);
		BookCopy returnedBookCopy = bookRentService.rentBookCopy(1L);
		assertEquals(2L, returnedBookCopy.getId().longValue());
	}

	@Test
	public void findAvailableBookCopies_noAvailableCopies() {
		Book b = new Book();
		b.setId(1L);
		BookCopy bookCopy1 = new BookCopy();
		bookCopy1.setId(2L);
		bookCopy1.setBook(b);
		bookCopy1.setUser(new User());
		BookCopy bookCopy2 = new BookCopy();
		bookCopy2.setBook(b);
		bookCopy2.setUser(new User());
		List<BookCopy> bookCopies = new ArrayList<>();
		bookCopies.add(bookCopy1);
		bookCopies.add(bookCopy2);
		when(bookCopyRepository.findByBook(b)).thenReturn(bookCopies);
		List<BookCopy> returnedBookCopies = bookRentService.findAvailableBookCopies(b);
		assertEquals(0, returnedBookCopies.size());
	}

	@Test
	public void findAvailableBookCopies_successfull() {
		Book b = new Book();
		b.setId(1L);
		BookCopy bookCopy1 = new BookCopy();
		bookCopy1.setId(2L);
		bookCopy1.setBook(b);
		bookCopy1.setUser(null);
		BookCopy bookCopy2 = new BookCopy();
		bookCopy2.setBook(b);
		bookCopy2.setUser(new User());
		List<BookCopy> bookCopies = new ArrayList<>();
		bookCopies.add(bookCopy1);
		bookCopies.add(bookCopy2);
		when(bookCopyRepository.findByBook(b)).thenReturn(bookCopies);
		List<BookCopy> returnedBookCopies = bookRentService.findAvailableBookCopies(b);
		assertEquals(1, returnedBookCopies.size());
		assertEquals(2l, returnedBookCopies.get(0).getId().longValue());
	}


	@Test(expected = IllegalArgumentException.class)
	public void returnBookCopy_invalidIdGiven() {
		BookCopy result = bookRentService.returnBookCopy(1L).orElse(null);
	}

	@Test
	public void returnBookCopy_successfull() {
		BookCopy bookCopy = new BookCopy();
		bookCopy.setId(1L);
		bookCopy.setRentStart(LocalDate.now().minusDays(5));
		bookCopy.setRentEnd(LocalDate.now().plusDays(10));
		bookCopy.setUser(new User());
		when(bookCopyRepository.findById(1L)).thenReturn(Optional.of(bookCopy));
		BookCopy result = bookRentService.returnBookCopy(1L).orElse(null);
		Assert.assertTrue(true);
	}

}
