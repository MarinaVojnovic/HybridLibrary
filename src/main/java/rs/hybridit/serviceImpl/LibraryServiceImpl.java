package rs.hybridit.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
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
		return libraryRepository.getOne(id);
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
