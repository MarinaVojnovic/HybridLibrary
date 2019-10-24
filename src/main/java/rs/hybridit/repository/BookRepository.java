package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.hybridit.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	public Book findByName(String name);

}

