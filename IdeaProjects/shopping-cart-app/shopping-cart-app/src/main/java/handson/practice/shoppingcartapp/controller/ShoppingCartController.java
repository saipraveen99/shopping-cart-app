package handson.practice.shoppingcartapp.controller;

import handson.practice.shoppingcartapp.entity.Cart;
import handson.practice.shoppingcartapp.entity.Product;
import handson.practice.shoppingcartapp.model.RequestDTO;
import handson.practice.shoppingcartapp.model.ResponseDTO;
import handson.practice.shoppingcartapp.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
// update case add relevant entries
@SessionAttributes(value = "product")
public class ShoppingCartController {

    private final ShoppingCartService service;

    private final MessageSource messageSource;

    private Cart cart;

    private static Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

    public ShoppingCartController(ShoppingCartService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    @GetMapping("/cart")
    public String home() {
        return "home.jsp";
    }

    @GetMapping("/add")
    public ModelAndView addCart() {
        ModelAndView modelAndView = new ModelAndView("addCart.jsp", "product", new RequestDTO());
        return modelAndView;
    }

    private Boolean validateInput(RequestDTO requestDTO, BindingResult result) {
        boolean error = false;

        if(requestDTO.getProductId().isEmpty()){
            result.rejectValue("productId", "error.productId");
            error = true;
        }

        if(requestDTO.getName().isEmpty()){
            result.rejectValue("name", "error.name");
            error = true;
        }

        if(!Float.isNaN(requestDTO.getPrice()) && requestDTO.getPrice() > 0) {
            result.rejectValue("price", "error.price");
            error = true;
        }
        if (requestDTO.getQuantity() == null || requestDTO.getQuantity() <= 0) {
            result.rejectValue("quantity", "error.quantity");
            String message = messageSource.getMessage("invalidQuantity", null, Locale.ENGLISH);
            LOGGER.error(message);
        }
        return error;
    }

    @PostMapping("/cart/add")
    public ModelAndView addProduct(@ModelAttribute("product") RequestDTO product, BindingResult result,
                                   SessionStatus sessionStatus) {
        ResponseDTO responseDTO = new ResponseDTO();
        Boolean error = this.validateInput(product, result);
        if (error) {
            return new ModelAndView("redirect:/add", "product", new RequestDTO());
        }
        if (null != product && product.getQuantity() >= 0) {
            if (null == cart) {
                List<Product> products = new ArrayList<>();
                Product productObj = service.getProduct(product);
                products.add(productObj);
                try {
                    this.cart = new Cart(0, 0d, products);
                    productObj.setCart(this.cart);
                    this.cart = service.addProduct(this.cart, productObj);
                    sessionStatus.setComplete();
                } catch (Exception e) {
                    LOGGER.error(e.getLocalizedMessage());
                }
            }
            else if (null != cart) {
                try {
                    Product productObj = service.getProduct(product);
                    this.cart = service.addProduct(this.cart, productObj);
                    sessionStatus.setComplete();
                    responseDTO.setMessage(messageSource.getMessage("productAdded", null, Locale.ENGLISH));
                } catch (Exception e) {
                    LOGGER.error(e.getLocalizedMessage());
                    responseDTO.setMessage(messageSource.getMessage("productAddFailed", null, Locale.ENGLISH));
                }
            }
        } else {
            String message = messageSource.getMessage("invalidQuantity", null, Locale.ENGLISH);
            LOGGER.error(message);
            responseDTO.setMessage(message);
        }
        return new ModelAndView("redirect:/view");
    }

    @DeleteMapping("/cart/delete")
    public @ResponseBody ResponseDTO deleteProduct(@RequestBody Product product) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != product && product.getQuantity() > 0) {
            try {
                service.delete(product);
                responseDTO.setMessage(messageSource.getMessage("productDeleted", null, Locale.ENGLISH));
            } catch (Exception e) {
                LOGGER.error(e.getLocalizedMessage());
            }
        } else {
            String message = messageSource.getMessage("invalidQuantity", null, Locale.ENGLISH);
            LOGGER.error(message);
            responseDTO.setMessage(message);
        }
        return responseDTO;
    }

    @PutMapping("/cart/update")
    public @ResponseBody ResponseDTO update(@RequestBody Product product) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != product && product.getQuantity() >= 0) {
            try {
                this.cart = service.update(cart, product);
                responseDTO.setMessage(messageSource.getMessage("productUpdated", null, Locale.ENGLISH));
            } catch (Exception e) {
                LOGGER.error(e.getLocalizedMessage());
            }
        }
        else {
            String message = messageSource.getMessage("invalidQuantity", null, Locale.ENGLISH);
            LOGGER.error(message);
            responseDTO.setMessage(message);
        }
        return responseDTO;
    }

    @GetMapping("/view")
    public ModelAndView viewCart() {
        ResponseDTO responseDTO = new ResponseDTO();
        if (null != cart) {
            List<Product> list = service.viewCart(this.cart.getId());
            responseDTO.setProducts(list);
            responseDTO.setCart(cart);
        } else {
            String message = messageSource.getMessage("cartEmpty", null, Locale.ENGLISH);
            LOGGER.error(message);
            responseDTO.setMessage(message);
        }
        return new ModelAndView("viewCart.jsp", "responseDTO", responseDTO);
    }

    @PostMapping("/checkout")
    public ModelAndView cartCheckout(Boolean checkout) {
        if (checkout) {
            this.cart = null;
        }
        return new ModelAndView("redirect:/add");
    }
}
