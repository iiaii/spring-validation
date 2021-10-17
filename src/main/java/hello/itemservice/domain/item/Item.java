package hello.itemservice.domain.item;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public int totalPrice() {
        return price * quantity;
    }

    public boolean isValidItemName() {
        return StringUtils.hasText(itemName);
    }

    public boolean isValidPrice() {
        return Objects.nonNull(price)
                && (price >= 1_000 && price <= 1_000_000);
    }

    public boolean isValidQuantity() {
        return Objects.nonNull(quantity)
                && quantity <= 9999;
    }

    public boolean isInValidTotalPrice() {
        return Objects.nonNull(price)
                && Objects.nonNull(quantity)
                && totalPrice() < 10_000;
    }
}
