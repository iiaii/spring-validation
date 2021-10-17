package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    // 지원하는가? (하위 자식클래스도 검증하도록)
    @Override
    public boolean supports(final Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        Item item = (Item) target;

        // 아래 로직과 동일함
//        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");

        if (!item.isValidItemName()) {
            errors.rejectValue("itemName", "required");
        }

        if (!item.isValidPrice()) {
            errors.rejectValue("price", "range", new Object[]{1_000, 1_000_000}, null);
        }

        if (!item.isValidQuantity()) {
            errors.rejectValue("quantity", "max", new Object[]{9_999}, null);
        }

        // 복합 룰 검증
        if (item.isInValidTotalPrice()) {
            errors.reject("totalPriceMin", new Object[]{1_000, item.totalPrice()}, null);
        }
    }

}
