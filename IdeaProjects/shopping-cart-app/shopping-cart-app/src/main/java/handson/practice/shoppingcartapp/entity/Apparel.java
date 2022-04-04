package handson.practice.shoppingcartapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DiscriminatorValue("Apparel")
public class Apparel extends Product implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type;
    private String brand;
    private String design;

    public Apparel() {
    }

    public Apparel(String productId, String name, float price, Integer quantity, Cart cart, String type, String brand, String design) {
        super(productId, name, price, quantity, cart);
        this.type = type;
        this.brand = brand;
        this.design = design;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Apparel apparel = (Apparel) o;
        return Objects.equals(type, apparel.type) &&
                Objects.equals(brand, apparel.brand) &&
                Objects.equals(design, apparel.design);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, brand, design);
    }
}
