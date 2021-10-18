package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BeanValidationTest {

    @Test
    @DisplayName("beanValidation")
    public void beanValidation() {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Item item = new Item();
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000);

        // when
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        // then
        assertThat(messages)
                .contains("공백일 수 없습니다", "9999 이하여야 합니다", "1000에서 1000000 사이여야 합니다");
    }

}
