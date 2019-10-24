package rs.hybridit.challenges;

import static org.junit.Assert.assertEquals;
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
import rs.hybridit.model.BookCopy;
import rs.hybridit.repository.BookCopyRepository;
import rs.hybridit.repository.BookRepository;
import rs.hybridit.serviceImpl.BookCopyServiceImpl;
import rs.hybridit.serviceImpl.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookCopyServiceImplTest {
	@InjectMocks
	private BookCopyServiceImpl bookCopyService;

	@Mock
	private BookCopyRepository bookCopyRepository;

	@Test
	public void findById_ExistingIdGiven_ShouldBeSuccessfull() {
		BookCopy bc = new BookCopy();
		bc.setId(1L);
		when(bookCopyRepository.getOne(1L)).thenReturn(bc);
		BookCopy bookCopy = bookCopyService.findById(1L);
		verify(bookCopyRepository).getOne(1L);
		assertEquals(1L, bookCopy.getId().longValue());
	}

	@Test
	public void findById_NonExistingIdGiven_ShouldReturnNull() {
		BookCopy bookCopy = bookCopyService.findById(1L);
		verify(bookCopyRepository).getOne(1L);
		assertEquals(null, bookCopy);
	}

	@Test
	public void getAll_BookCopysExist() {
		BookCopy bookCopy = new BookCopy();
		List<BookCopy> bookCopys = new ArrayList<BookCopy>();
		bookCopys.add(bookCopy);
		when(bookCopyRepository.findAll()).thenReturn(bookCopys);
		List<BookCopy> returnedBookCopys = bookCopyService.getAll();
		verify(bookCopyRepository).findAll();
		assertEquals(bookCopys, returnedBookCopys);
	}

	@Test
	public void getAll_BookCopysDoNotExist() {
		List<BookCopy> bookCopys = new ArrayList<BookCopy>();
		List<BookCopy> returnedBookCopys = bookCopyService.getAll();
		verify(bookCopyRepository).findAll();
		assertEquals(bookCopys, returnedBookCopys);
	}

	@Test
	public void create_BookDoesNotExist(){
		BookCopy bc = new BookCopy();
		bc.setId(1L);
		when(bookCopyRepository.save(bc)).thenReturn(bc);
		BookCopy bookCopy = bookCopyService.create(bc);
		verify(bookCopyRepository).save(bc);
		assertEquals(bc, bookCopy);
	}


}

