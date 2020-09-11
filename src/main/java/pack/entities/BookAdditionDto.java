package pack.entities;

public class BookAdditionDto {
    private Long authorId;
    private Book book;

    public BookAdditionDto() {}

    public BookAdditionDto(Long authorId, Book book) {
        this.authorId = authorId;
        this.book = book;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
