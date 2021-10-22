package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Data
// 권장하는 방식이 아님
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10_000", message = "최소 총합은 10,000 입니다")
public class Item {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1_000, max = 1_000_000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9_999, groups = {SaveCheck.class})
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
