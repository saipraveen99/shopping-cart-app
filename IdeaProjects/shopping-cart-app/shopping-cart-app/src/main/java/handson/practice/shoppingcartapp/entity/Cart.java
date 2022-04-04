package handson.practice.shoppingcartapp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_Amount", nullable = false)
    private double total_Amount;

//    @Column(name = "name", nullable = false)
//    private String name;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    public Cart() {
    }

    public Cart(Integer quantity, double total_Amount, List<Product> products) {
        this.quantity = quantity;
        this.total_Amount = total_Amount;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getTotal_Amount() {
        return total_Amount;
    }

    public void setTotal_Amount(double total_Amount) {
        this.total_Amount = total_Amount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", total_Amount=" + total_Amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Double.compare(cart.total_Amount, total_Amount) == 0 &&
                Objects.equals(id, cart.id) &&
                Objects.equals(quantity, cart.quantity) &&
                Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, total_Amount, products);
    }
}
