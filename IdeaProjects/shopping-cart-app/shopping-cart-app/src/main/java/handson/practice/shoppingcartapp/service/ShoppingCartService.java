package handson.practice.shoppingcartapp.service;

import handson.practice.shoppingcartapp.dao.CartRepo;
import handson.practice.shoppingcartapp.dao.ProductRepo;
import handson.practice.shoppingcartapp.entity.Apparel;
import handson.practice.shoppingcartapp.entity.Book;
import handson.practice.shoppingcartapp.entity.Cart;
import handson.practice.shoppingcartapp.entity.Product;
import handson.practice.shoppingcartapp.model.RequestDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ProductRepo productRepo;

    private final CartRepo cartRepo;

    public ShoppingCartService(ProductRepo productRepo, CartRepo cartRepo) {
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
    }

    @Transactional
    public Cart addProduct(Cart cart, Product product) {
        Long cartId = cart.getId();
        List<Product> products = null;
        if (null != cartId) {
            products = this.getAllProducts();
        }

        Product finalProduct = product;
        boolean sameProduct = false;
        if (null != products) {
            products = products.stream().filter(product1 -> product1.getCart().getId().compareTo(cartId) == 0)
            .collect(Collectors.toList());
            for (Product product1 : products) {
                if (product1.getProductId().equalsIgnoreCase(finalProduct.getProductId())) {
                    product1.setQuantity(Integer.sum(product1.getQuantity(), finalProduct.getQuantity()));
                    sameProduct = true;
                }
            }
        }
        if (!sameProduct && null != products) {
            products.add(product);
        }
        if (null != products && !products.isEmpty()) {
            cart.setProducts(products);
        }
        cart = this.updateCart(cart, product);
        product.setCart(cart);
        productRepo.saveAndFlush(product);
        return cart;
    }

    private Cart updateCart(Cart cart, Product product) {
        cart.setQuantity(Integer.sum(cart.getQuantity(), product.getQuantity()));
        cart.setTotal_Amount(Double.sum(cart.getTotal_Amount(), (double) product.getQuantity() * product.getPrice()));
        cart = cartRepo.saveAndFlush(cart);
        return cart;
    }

    private List<Product> getAllProducts() {
        List<Product> list = productRepo.findAll();
        return list;
    }

    private Product getProductById(String productId) {
        List<Product> result = this.getAllProducts().stream()
                .filter(product1 -> product1.getProductId().equalsIgnoreCase(productId))
                .collect(Collectors.toList());
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Transactional
    public void delete(Product product) {
        Product productDB = this.getProductById(product.getProductId());
        if (null != productDB) {
            productRepo.deleteById(productDB.getId());
        }
    }

    @Transactional
    public Cart update(Cart cart, Product product) {
        if (product.getQuantity() == 0) {
            this.delete(product);
            Product productDB = this.getProductById(product.getProductId());
            if (null != productDB) {
                cartRepo.deleteById(productDB.getCart().getId());
            }
        }
        else {
            Product productDB = this.getProductById(product.getProductId());
            if (null != productDB) {
                productDB.setQuantity(product.getQuantity());
                cart = this.updateCart(cart, product);
                productDB.setCart(cart);
                productRepo.saveAndFlush(productDB);
            }
        }
        return cart;
    }

    @Transactional
    public List<Product> viewCart(Long id) {
        List<Product> viewList = this.getAllProducts().stream().filter(product -> product.getCart().getId().compareTo(id) == 0)
                .collect(Collectors.toList());
        return viewList;
    }

    public Product getProduct(RequestDTO product) {
        Product productObj = null;
        if (null != product && !product.getAuthor().trim().isEmpty() && !product.getPublications().trim().isEmpty() &&
        !product.getGenre().trim().isEmpty()) {
            productObj = new Book(product.getProductId().trim(), product.getName().trim(), product.getPrice(),
                    product.getQuantity(), null, product.getGenre().trim(), product.getAuthor().trim(),
                    product.getPublications().trim());

        }
        else if (null != product && !product.getBrand().trim().isEmpty() && !product.getDesign().trim().isEmpty()
        && !product.getType().trim().isEmpty()) {
            productObj = new Apparel(product.getProductId().trim(), product.getName().trim(), product.getPrice(),
                    product.getQuantity(), null, product.getType().trim(), product.getBrand().trim(),
                    product.getDesign().trim());
        }
        else {
            productObj = new Product(product.getProductId().trim(), product.getName().trim(), product.getPrice(),
                    product.getQuantity(), null);
        }
        return productObj;
    }
}
