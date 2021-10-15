package hello.itemservice.web.validation;

import java.util.HashMap;
import java.util.Map;

public class Errors {

    private final Map<String, String> errors;

    public Errors() {
        this.errors = new HashMap<>();
    }

    public Map<String, String> errors() {
        return errors;
    }

    public void put(final String key, final String value) {
        errors.put(key, value);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
