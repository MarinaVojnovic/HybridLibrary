package rs.hybridit.challenges;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.hybridit.model.Book;
import rs.hybridit.model.Library;
import rs.hybridit.repository.BookRepository;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.service.BookService;
import rs.hybridit.service.LibraryService;
import rs.hybridit.serviceImpl.BookServiceImpl;
import rs.hybridit.serviceImpl.LibraryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepository bookRepository;


	@Test
	public void findById_ExistingIdGiven_ShouldBeSuccessfull() {
		Book b = new Book();
		b.setId(1L);
		when(bookRepository.getOne(1L)).thenReturn(b);
		Book book = bookService.findById(1L);
		verify(bookRepository).getOne(1L);
		assertEquals(1L, book.getId().longValue());
	}

	@Test
	public void findById_NonExistingIdGiven_ShouldReturnNull() {
		Book book = bookService.findById(1L);
		verify(bookRepository).getOne(1L);
		assertEquals(null, book);
	}

	@Test
	public void getAll_BooksExist() {
		Book book = new Book();
		Book book2 = new Book();
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		books.add(book2);
		when(bookRepository.findAll()).thenReturn(books);
		List<Book> returnedBooks = bookService.getAll();
		verify(bookRepository).findAll();
		assertEquals(books, returnedBooks);
	}

	@Test
	public void getAll_BooksDoNotExist() {
		List<Book> books = new ArrayList<Book>();
		List<Book> returnedBooks = bookService.getAll();
		verify(bookRepository).findAll();
		assertEquals(books, returnedBooks);
	}

	@Test
	public void create_BookDoesNotExist() {
		Book b = new Book();
		b.setId(1L);
		when(bookRepository.save(b)).thenReturn(b);
		Book book = bookService.create(b);
		verify(bookRepository).save(b);
		assertEquals(b, book);
	}

	@Test
	public void findByName_ExistingNameGiven_ShouldBeSuccessfull() {
		Book b = new Book();
		String name = any();
		b.setName(name);
		when(bookRepository.findByName(name)).thenReturn(b);
		Book book = bookService.findByName(name);
		verify(bookRepository).findByName(name);
		assertEquals(b, book);
	}

	@Test
	public void findByName_NonExistingNameGiven_ShouldBeSuccessfull() {
		Book book = bookService.findByName(any());
		verify(bookRepository).findByName(any());
		assertEquals(null, book);
	}

}
