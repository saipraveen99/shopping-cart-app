package handson.practice.shoppingcartapp.dao;

import handson.practice.shoppingcartapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
