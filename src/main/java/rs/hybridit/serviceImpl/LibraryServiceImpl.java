package rs.hybridit.serviceImpl;


import java.util.Optional;
import org.springframework.stereotype.Service;
import rs.hybridit.model.Library;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.service.LibraryService;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

	private final LibraryRepository libraryRepository;

	public LibraryServiceImpl(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@Override
	public Library findById(long id) {
		Optional<Library> libraryOptional = libraryRepository.findById(id);
		if (libraryOptional.isPresent()) {
			return libraryOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Library> getAll() {
		return libraryRepository.findAll();
	}

	@Override
	public Library create(Library library) {
		return libraryRepository.save(library);
	}

	@Override
	public void delete(Library library) {
		libraryRepository.delete(library);
	}

}
