package rs.hybridit.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.hybridit.repository.LibraryRepository;
import rs.hybridit.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
}
