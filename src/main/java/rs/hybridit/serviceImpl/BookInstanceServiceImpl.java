package rs.hybridit.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.hybridit.model.BookInstance;
import rs.hybridit.repository.BookInstanceRepository;
import rs.hybridit.service.BookInstanceService;

import java.util.List;

@Service
public class BookInstanceServiceImpl implements BookInstanceService {

    @Autowired
    BookInstanceRepository bookInstanceRepository;

    @Override
    public BookInstance getOne(long id) {
        return bookInstanceRepository.getOne(id);
    }

    @Override
    public List<BookInstance> getAll() {
        return bookInstanceRepository.findAll();
    }

    @Override
    public BookInstance create(BookInstance book) {
        return bookInstanceRepository.save(book);
    }

    @Override
    public BookInstance save(BookInstance book) {
        return bookInstanceRepository.save(book);
    }

    @Override
    public void delete(BookInstance book) {
        bookInstanceRepository.delete(book);

    }
}
