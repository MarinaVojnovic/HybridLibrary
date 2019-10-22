package rs.hybridit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(catalog = "dbhybridlibrary", name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private Integer isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "image")
    private String image;

    @Column(name = "renting_counter")
    private Integer rentingCounter;
/*
    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<BookInstance> book_instances = new HashSet<>();
*/
    public Book() {
    }

    public Book(Long id, Integer isbn, String name, String author, String image, Integer rentingCounter/*, Set<BookInstance> book_instances */) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.image = image;
        this.rentingCounter = rentingCounter;
        //this.book_instances = book_instances;
    }

    public Book(Book book){
        this.id = book.id;
        this.isbn = book.isbn;
        this.name = book.name;
        this.author = book.author;
        this.image = book.image;
        this.rentingCounter=book.rentingCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRentingCounter() {
        return rentingCounter;
    }

    public void setRentingCounter(Integer rentingCounter) {
        this.rentingCounter = rentingCounter;
    }
/*
    public Set<BookInstance> getBook_instances() {
        return book_instances;
    }

    public void setBook_instances(Set<BookInstance> book_instances) {
        this.book_instances = book_instances;
    }
    */

}
