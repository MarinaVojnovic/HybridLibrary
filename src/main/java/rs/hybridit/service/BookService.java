package rs.hybridit.service;

import java.util.List;
import java.util.Optional;
import rs.hybridit.model.Book;

public interface BookService {

	Book findById(long id);

	List<Book> getAll();

	Book create(Book book);

	void delete(Book book);

	Book findByName(String name);

	public List findAllOrderByRentingCounterDesc();

}

