package rs.hybridit.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.hybridit.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
