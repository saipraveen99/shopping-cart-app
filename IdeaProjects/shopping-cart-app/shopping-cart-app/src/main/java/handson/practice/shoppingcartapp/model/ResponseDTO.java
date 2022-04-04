package handson.practice.shoppingcartapp.model;

import handson.practice.shoppingcartapp.entity.Cart;
import handson.practice.shoppingcartapp.entity.Product;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;

    private List<Product> products;

    private Cart cart;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
