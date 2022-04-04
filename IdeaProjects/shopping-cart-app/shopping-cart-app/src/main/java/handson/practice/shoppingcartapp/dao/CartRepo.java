package handson.practice.shoppingcartapp.dao;

import handson.practice.shoppingcartapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
}
