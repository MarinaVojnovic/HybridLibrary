package rs.hybridit.challenges;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.hybridit.controller.BookController;
import rs.hybridit.controller.LibraryController;
import rs.hybridit.dto.BookDto;
import rs.hybridit.dto.LibraryDto;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.model.Library;
import rs.hybridit.service.BookService;
import rs.hybridit.service.LibraryService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@InjectMocks
	private BookController bookController;

	@Mock
	private BookService bookService;

	@Test
	public void getBook_ExistingIdGiven_ShouldBeSuccessfull() {
		Book b = new Book();
		b.setId(1L);
		when(bookService.getOne(1L)).thenReturn(b);
		Book book = bookController.getBook(1L);
		verify(bookService).getOne(1L);
		assertEquals(1L, book.getId().longValue());
	}

	@Test
	public void getBook_NonExistingIdGiven_ShouldReturnNull() {
		Book book = bookController.getBook(1L);
		verify(bookService).getOne(1L);
		assertEquals(null, book);
	}

	@Test
	public void deleteBook_ExistingIdGiven_ShouldBeSuccessfull() {
		Book b = new Book();
		b.setId(1L);
		when(bookService.getOne(1L)).thenReturn(b);
		Book book = bookController.deleteBook(1L);
		verify(bookService).getOne(1L);
		assertEquals(1L, book.getId().longValue());
	}

	@Test
	public void deleteBook_NonExistingIdGiven_ShouldReturnNull() {
		Book book = bookController.deleteBook(1L);
		verify(bookService).getOne(1L);
		assertEquals(null, book);
	}

	@Test
	public void updateBook_ExistingIdGiven_ShouldBeSuccessfull() {
		Book book = new Book();
		book.setId(1L);
		BookDto bookDto = new BookDto();
		bookDto.setId(1L);
		bookDto.setName("New book name");
		bookDto.setAuthor("New author");
		bookDto.setImage("C:\\Users\\Marina\\Desktop\\hybridLibrary\\HybridLibrary");
		bookDto.setIsbn("198765421234");
		book.setRentingCounter(2);
		BookCopy bookCopy = new BookCopy();
		bookCopy.setId(1L);
		Set<BookCopy> bookCopies = new HashSet<BookCopy>();
		bookCopies.add(bookCopy);
		bookDto.setBookCopies(bookCopies);
		when(bookService.getOne(1L)).thenReturn(book);
		Book returnedBook = bookController.updateBook(1L, bookDto);
		verify(bookService).getOne(1L);
		assertEquals(book.getName(), returnedBook.getName());
		assertEquals(book.getAuthor(), returnedBook.getAuthor());
		assertEquals(book.getImage(), returnedBook.getImage());
		assertEquals(book.getIsbn(), returnedBook.getIsbn());
		assertEquals(book.getRentingCounter(), returnedBook.getRentingCounter());
		assertEquals(book.getBookCopies(), returnedBook.getBookCopies());
	}

	@Test
	public void updateBook_NonExistingIdGiven_ShouldReturnNull() {
		BookDto bookDto = new BookDto();
		bookDto.setId(1L);
		Book book = bookController.updateBook(1L, bookDto);
		verify(bookService).getOne(1L);
		assertEquals(null, book);
	}

	@Test
	public void getBooks_BooksExist() {
		Book book = new Book();
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		when(bookService.getAll()).thenReturn(books);
		List<Book> returnedBooks = bookController.getBooks();
		verify(bookService).getAll();
		assertEquals(books, returnedBooks);
	}

	@Test
	public void getBooks_BooksDoNotExist() {
		List<Book> books = new ArrayList<Book>();
		List<Book> returnedBooks = bookController.getBooks();
		verify(bookService).getAll();
		assertEquals(books, returnedBooks);
	}

}
