package pack.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false, unique = true, length = 50)
    private String title;
    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;
    @Column(name = "price", nullable = false)
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Customer> customers;

    public Book(){}

    public Book(String title, LocalDate publishDate, Double price, Author author, List<Customer> customers) {
        this.title = title;
        this.publishDate = publishDate;
        this.price = price;
        this.author = author;
        this.customers = customers;
    }

    public Book(String title, LocalDate publishDate, Double price, Author author) {
        this.title = title;
        this.publishDate = publishDate;
        this.price = price;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                '}';
    }
}
