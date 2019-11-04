package rs.hybridit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.hybridit.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

}