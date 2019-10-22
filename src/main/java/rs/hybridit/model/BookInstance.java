package rs.hybridit.model;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(catalog = "dbhybridlibrary", name = "book_instances")
public class BookInstance {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "rent_start")
    private Date rentStart;

    @Column(name = "rent_end")
    private Date rent_end;

    @ManyToOne(fetch = FetchType.EAGER)
    Book book;

    public BookInstance() {
    }

    public BookInstance(Long id, Date rentStart, Date rent_end, Book book) {
        this.id = id;
        this.rentStart = rentStart;
        this.rent_end = rent_end;
        this.book = book;
    }

    public BookInstance(BookInstance bookInstance){
        this.id=bookInstance.id;
        this.rentStart=bookInstance.rentStart;
        this.rent_end=bookInstance.rent_end;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRentStart() {
        return rentStart;
    }

    public void setRentStart(Date rentStart) {
        this.rentStart = rentStart;
    }

    public Date getRent_end() {
        return rent_end;
    }

    public void setRent_end(Date rent_end) {
        this.rent_end = rent_end;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
