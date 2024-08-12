package ai.shreds.shared;

/**
 * A simple immutable value object that encapsulates a single value.
 * This class is used across different layers of the application to represent
 * and pass around a specific value in a type-safe manner.
 *
 * @param <T> the type of the value
 */
public class SharedValueObject<T> {
    private final T value;

    public SharedValueObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}