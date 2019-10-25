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
import rs.hybridit.model.Library;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.service.LibraryService;
import rs.hybridit.serviceImpl.LibraryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceImplTest {

	@InjectMocks
	LibraryServiceImpl libraryService;

	@Mock
	LibraryRepository libraryRepository;

	@Test
	public void findOne_ExistingIdGiven_ShouldBeSuccessfull() {
		Library l = new Library();
		l.setId(1L);
		when(libraryRepository.findById(1L)).thenReturn(java.util.Optional.of(l));
		Library library = libraryService.findById(1L);
		verify(libraryRepository).findById(1L);
		assertEquals(1L, library.getId().longValue());
	}

	@Test
	public void findOne_NonExistingIdGiven_ShouldReturnNull() {
		Library library = libraryService.findById(1L);
		verify(libraryRepository).findById(1L);
		assertEquals(null, library);
	}

	@Test
	public void getAll_LibrariesExist() {
		Library library = new Library();
		List<Library> libraries = new ArrayList<Library>();
		libraries.add(library);
		when(libraryRepository.findAll()).thenReturn(libraries);
		List<Library> returnedLibraries = libraryService.getAll();
		verify(libraryRepository).findAll();
		assertEquals(libraries, returnedLibraries);
	}

	@Test
	public void getAll_LibrariesDoNotExist() {
		List<Library> libraries = new ArrayList<Library>();
		List<Library> returnedLibraries = libraryService.getAll();
		verify(libraryRepository).findAll();
		assertEquals(libraries, returnedLibraries);
	}

	@Test
	public void create_LibraryDoesNotExist() {
		Library l = new Library();
		l.setId(1L);
		when(libraryRepository.save(l)).thenReturn(l);
		Library library = libraryService.create(l);
		verify(libraryRepository).save(l);
		assertEquals(l, l);
	}

}
