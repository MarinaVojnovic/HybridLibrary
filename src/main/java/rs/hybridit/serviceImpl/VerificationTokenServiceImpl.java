package rs.hybridit.serviceImpl;

import org.springframework.stereotype.Service;
import rs.hybridit.model.VerificationToken;
import rs.hybridit.repository.VerificationTokenRepository;
import rs.hybridit.service.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	private final VerificationTokenRepository verificationTokenRepository;

	public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
		this.verificationTokenRepository = verificationTokenRepository;
	}

	@Override
	public void saveToken(VerificationToken token) {
		verificationTokenRepository.save(token);
	}

}
