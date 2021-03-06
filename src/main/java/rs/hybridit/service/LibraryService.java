package rs.hybridit.service;

import java.util.Optional;
import rs.hybridit.model.Library;

import java.util.List;

public interface LibraryService {

	Library findById(long id);

	List<Library> getAll();

	Library create(Library library);

	void delete(Library library);

}
