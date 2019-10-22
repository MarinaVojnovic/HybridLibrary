package rs.hybridit.service;

import rs.hybridit.model.Book;

import java.util.List;

public interface BookService {
    public Book getOne(long id);
    public List<Book> getAll();
    public Book create(Book book);
    public Book save(Book book);

    public void delete(Book book);
    public Book findByName(String name);
}
