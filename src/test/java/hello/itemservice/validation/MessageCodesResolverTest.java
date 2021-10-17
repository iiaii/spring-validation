package hello.itemservice.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
    
    @Test
    @DisplayName("messageCodesResolverObject")        
    public void messageCodesResolverObject() {
        // given

        // when
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        
        // then
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    @DisplayName("messageCodesResolverField")
    public void messageCodesResolverField() {
        // given

        // when
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        // then
        assertThat(messageCodes)
                .containsExactly(
                        "required.item.itemName",
                        "required.itemName",
                        "required.java.lang.String",
                        "required");
    }
    
}
