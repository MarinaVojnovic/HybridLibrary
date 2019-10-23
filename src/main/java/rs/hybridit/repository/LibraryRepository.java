package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.hybridit.model.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
