package handson.practice.shoppingcartapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name= "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public class Product implements Serializable, Comparable<Product> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "productId", nullable = false)
    private String productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @JoinColumn(name = "id_cart", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY) //cascade = CascadeType.PERSIST
    private Cart cart;

    public Product() {
    }

    public Product(String productId, String name, float price, Integer quantity, Cart cart) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(productId, product.productId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(quantity, product.quantity) &&
                Objects.equals(cart, product.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, name, price, quantity, cart);
    }

    @Override
    public int compareTo(Product o) {
        return this.productId.compareTo(o.getProductId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", cart=" + cart +
                '}';
    }
}
