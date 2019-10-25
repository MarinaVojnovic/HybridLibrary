package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.hybridit.model.BookCopy;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

}
