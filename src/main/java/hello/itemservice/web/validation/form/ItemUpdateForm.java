package hello.itemservice.web.validation.form;

import hello.itemservice.domain.item.Item;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    private Integer price;

    // 수정시에 수량은 자유롭게 변경 가능
    private Integer quantity;

    public int totalPrice() {
        return price * quantity;
    }

    public boolean isInValidTotalPrice() {
        return Objects.nonNull(price)
                && Objects.nonNull(quantity)
                && totalPrice() < 10_000;
    }

    public Item toEntity() {
        return new Item(itemName, price, quantity);
    }
}
