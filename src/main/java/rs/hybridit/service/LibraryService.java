package rs.hybridit.service;

import rs.hybridit.model.Library;

import java.util.List;

public interface LibraryService {
    public Library getOne(long id);
    public List<Library> getAll();
    public Library create(Library library);
    public void delete(Library library);
}
