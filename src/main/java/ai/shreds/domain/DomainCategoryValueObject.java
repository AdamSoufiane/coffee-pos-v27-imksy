package ai.shreds.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public final class DomainCategoryValueObject {
    private final String value;

    public DomainCategoryValueObject(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
        this.value = value;
    }
}