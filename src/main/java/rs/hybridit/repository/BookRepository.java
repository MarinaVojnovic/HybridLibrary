package rs.hybridit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.hybridit.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByName(String name);

	@Query(nativeQuery = true, value =
		"SELECT * FROM dbhybridlibrary.books b left join dbhybridlibrary.book_copies bc on b.id = bc.book_id"
			+ "and bc.user_id is null"
			+ "and bc.rent_start is null"
			+ "and bc.rent_end is null;")
	List<Book> findAllRented();


}

