package rs.hybridit.challenges;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.hybridit.controller.BookController;
import rs.hybridit.controller.BookCopyController;
import rs.hybridit.dto.BookCopyDto;
import rs.hybridit.dto.BookDto;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;
import rs.hybridit.service.BookCopyService;
import rs.hybridit.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookCopyControllerTest {

	@InjectMocks
	private BookCopyController bookCopyController;

	@Mock
	private BookCopyService bookCopyService;

	@Test
	public void getBookCopy_ExistingIdGiven_ShouldBeSuccessfull() {
		BookCopy bc = new BookCopy();
		bc.setId(1L);
		when(bookCopyService.getOne(1L)).thenReturn(bc);
		BookCopy bookCopy = bookCopyController.getBookCopy(1L);
		verify(bookCopyService).getOne(1L);
		assertEquals(1L, bookCopy.getId().longValue());
	}

	@Test
	public void getBookCopy_NonExistingIdGiven_ShouldReturnNull() {
		BookCopy bookCopy = bookCopyController.getBookCopy(1L);
		verify(bookCopyService).getOne(1L);
		assertEquals(null, bookCopy);
	}

	@Test
	public void deleteBookCopy_ExistingIdGiven_ShouldBeSuccessfull() {
		BookCopy bc = new BookCopy();
		bc.setId(1L);
		when(bookCopyService.getOne(1L)).thenReturn(bc);
		BookCopy bookCopy = bookCopyController.deleteBookCopy(1L);
		verify(bookCopyService).getOne(1L);
		assertEquals(1L, bookCopy.getId().longValue());
	}

	@Test
	public void deleteBookCopy_NonExistingIdGiven_ShouldReturnNull() {
		BookCopy bookCopy = bookCopyController.deleteBookCopy(1L);
		verify(bookCopyService).getOne(1L);
		assertEquals(null, bookCopy);
	}

	@Test
	public void updateBookCopy_ExistingIdGiven_ShouldBeSuccessfull() {
		BookCopy bookCopy = new BookCopy();
		bookCopy.setId(1L);
		BookCopyDto bookCopyDto = new BookCopyDto();
		bookCopyDto.setId(1L);
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		Date endDate;
		try {
			startDate = ft.parse("2019-10-30");
			endDate = ft.parse("2019-11-05");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		when(bookCopyService.getOne(1L)).thenReturn(bookCopy);
		BookCopy returnedBookCopy = bookCopyController.updateBookCopy(1L, bookCopyDto);
		verify(bookCopyService).getOne(1L);
		assertEquals(bookCopy.getRentStart(), returnedBookCopy.getRentStart());
		assertEquals(bookCopy.getRentEnd(), returnedBookCopy.getRentEnd());
	}

	@Test
	public void updateBookCopy_NonExistingIdGiven_ShouldReturnNull() {
		BookCopyDto bookCopyDto = new BookCopyDto();
		bookCopyDto.setId(1L);
		BookCopy bookCopy = bookCopyController.updateBookCopy(1L, bookCopyDto);
		verify(bookCopyService).getOne(1L);
		assertEquals(null, bookCopy);
	}

	@Test
	public void getBookCopys_BookCopysExist() {
		BookCopy bookCopy = new BookCopy();
		List<BookCopy> bookCopys = new ArrayList<BookCopy>();
		bookCopys.add(bookCopy);
		when(bookCopyService.getAll()).thenReturn(bookCopys);
		List<BookCopy> returnedBookCopys = bookCopyController.getBookCopys();
		verify(bookCopyService).getAll();
		assertEquals(bookCopys, returnedBookCopys);
	}

	@Test
	public void getBookCopys_BookCopysDoNotExist() {
		List<BookCopy> bookCopys = new ArrayList<BookCopy>();
		List<BookCopy> returnedBookCopys = bookCopyController.getBookCopys();
		verify(bookCopyService).getAll();
		assertEquals(bookCopys, returnedBookCopys);
	}

}
