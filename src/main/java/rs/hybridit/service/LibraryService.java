package rs.hybridit.service;

import rs.hybridit.model.Library;

import java.util.List;

public interface LibraryService {
    Library getOne(long id);
    List<Library> getAll();
    Library create(Library library);
    void delete(Library library);
}
