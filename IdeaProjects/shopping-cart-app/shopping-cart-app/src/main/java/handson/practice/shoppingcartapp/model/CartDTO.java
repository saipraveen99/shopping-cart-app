package handson.practice.shoppingcartapp.model;

import java.io.Serializable;

public class CartDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String cartName;
    private Integer quantity;
    private double total_Amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
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
}
