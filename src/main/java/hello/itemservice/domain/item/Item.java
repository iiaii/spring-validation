package hello.itemservice.domain.item;

import hello.itemservice.web.validation.Errors;
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

    public Errors validItem() {
        Errors errors = new Errors();

        if (!isValidItemName()) {
            errors.put("itemName", "상품 이름은 필수입니다.");
        }

        if (!isValidPrice()) {
            errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다.");
        }

        if (!isValidQuantity()) {
            errors.put("quantity", "수량은 최대 9,999 까지 허용합니다.");
        }

        // 복합 룰 검증
        if (Objects.nonNull(price)
                && Objects.nonNull(quantity)
                && !isValidTotalPrice()) {
            errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + totalPrice());
        }

        return errors;
    }

    private int totalPrice() {
        return price * quantity;
    }

    private boolean isValidItemName() {
        return StringUtils.hasText(itemName);
    }

    private boolean isValidPrice() {
        return Objects.nonNull(price)
                && (price >= 1_000 && price <= 1_000_000);
    }

    private boolean isValidQuantity() {
        return Objects.nonNull(quantity)
                && quantity <= 9999;
    }

    private boolean isValidTotalPrice() {
        return totalPrice() >= 10_000;
    }
}
