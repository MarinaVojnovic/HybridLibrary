package rs.hybridit.service;

import rs.hybridit.model.BookInstance;

import java.util.List;

public interface BookInstanceService {
    public BookInstance getOne(long id);
    public List<BookInstance> getAll();
    public BookInstance create(BookInstance book);
    public BookInstance save(BookInstance book);
    public void delete(BookInstance book);
}
