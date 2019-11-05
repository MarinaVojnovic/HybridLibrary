package rs.hybridit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.hybridit.model.Book;
import rs.hybridit.model.BookCopy;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

	List<BookCopy> findByBook(Book book);
}
