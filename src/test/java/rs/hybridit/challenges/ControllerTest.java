package rs.hybridit.challenges;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.hybridit.controller.LibraryController;
import rs.hybridit.dto.LibraryDto;
import rs.hybridit.model.Library;
import rs.hybridit.service.LibraryService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	private LibraryController libraryController;

	@Mock
	private LibraryService libraryService;

	@Before
	public void init() {
		libraryController = new LibraryController(libraryService);
	}

	@Test
	public void getLibrary_ExistingIdGiven_ShouldBeSuccessfull()

	{
		Library l = new Library();
		l.setId(1L);
		when(libraryService.getOne(1L)).thenReturn(l);

		Library library = libraryController.getLibrary(1L);

		verify(libraryService).getOne(1l);

		//assertEquals(1l, user.getId().longValue());
		assertEquals(1l, library.getId().longValue());
	}

	@Test
	public void getLibrary_NonExistingIdGiven_ShouldReturnNull()

	{
		Library library = libraryController.getLibrary(1L);
		verify(libraryService).getOne(1l);
		assertEquals(null, library);
	}


	@Test
	public void deleteLibrary_ExistingIdGiven_ShouldBeSuccessfull()

	{
		Library l = new Library();
		l.setId(1L);
		when(libraryService.getOne(1L)).thenReturn(l);

		Library library = libraryController.deleteLibrary(1L);

		verify(libraryService).getOne(1l);
		assertEquals(1l, library.getId().longValue());
	}

	@Test
	public void deleteLibrary_NonExistingIdGiven_ShouldReturnNull()

	{
		Library library = libraryController.getLibrary(1L);
		verify(libraryService).getOne(1l);
		assertEquals(null, library);
	}


	@Test
	public void updateLibrary_NonExistingIdGiven_ShouldReturnNull()

	{
		LibraryDto libraryDto = new LibraryDto();
		libraryDto.setId(1L);

		Library library = libraryController.updateLibrary(libraryDto);
		verify(libraryService).getOne(1l);

		//assertEquals(1l, user.getId().longValue());
		assertEquals(null, library);
	}

	@Test
	public void updateLibrary_ExistingIdGiven_ShouldBeSuccessfull()

	{
		Library library=new Library();
		library.setId(1L);

		LibraryDto libraryDto = new LibraryDto();
		libraryDto.setId(1L);
		libraryDto.setRentPeriod(15);

		when(libraryService.getOne(1L)).thenReturn(library);

		Library returnedLibrary = libraryController.updateLibrary(libraryDto);
		verify(libraryService).getOne(1L);

		//assertEquals(1l, user.getId().longValue());
		assertEquals(libraryDto.getRentPeriod(), returnedLibrary.getRentPeriod());
	}

	@Test
	public void getLibraries_LibrariesExist(){
		Library library = new Library();
		List<Library> libraries = new ArrayList<Library>();
		libraries.add(library);

		when(libraryService.getAll()).thenReturn(libraries);

		List<Library> returnedLibraries = libraryController.getLibraries();
		verify(libraryService).getAll();

		assertEquals(libraries, returnedLibraries);
	}

	@Test
	public void getLibraries_LibrariesDoNotExist(){

		List<Library> libraries = new ArrayList<Library>();

		List<Library> returnedLibraries = libraryController.getLibraries();
		verify(libraryService).getAll();

		assertEquals(libraries, returnedLibraries);
	}
}
