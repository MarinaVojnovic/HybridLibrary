package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.hybridit.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
