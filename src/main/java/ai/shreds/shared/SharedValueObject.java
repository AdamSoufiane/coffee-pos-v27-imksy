package ai.shreds.shared;

import lombok.Value;

/**
 * A simple immutable value object that encapsulates a single value.
 * This class is used across different layers of the application to represent
 * and pass around a specific value in a type-safe manner.
 *
 * @param <T> the type of the value
 */
@Value
public class SharedValueObject<T> {
    private final T value;

    public SharedValueObject(T value) {
        this.value = value;
    }
}