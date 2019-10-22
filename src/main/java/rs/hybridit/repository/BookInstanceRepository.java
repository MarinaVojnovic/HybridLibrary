package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.hybridit.model.BookInstance;

@Repository
public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {


}
