package handson.practice.shoppingcartapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DiscriminatorValue("Book")
public class Book extends Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String genre;
    private String author;
    private String publications;

    public Book() {
    }

    public Book(String productId, String name, float price, Integer quantity, Cart cart, String genre, String author, String publications) {
        super(productId, name, price, quantity, cart);
        this.genre = genre;
        this.author = author;
        this.publications = publications;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(genre, book.genre) &&
                Objects.equals(author, book.author) &&
                Objects.equals(publications, book.publications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genre, author, publications);
    }
}
