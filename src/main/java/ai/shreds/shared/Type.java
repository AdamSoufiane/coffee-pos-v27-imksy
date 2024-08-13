package ai.shreds.shared;

import java.io.Serializable;

/**
 * The Type class is a simple value object that encapsulates a single string value representing the type name.
 * This class is immutable to ensure its integrity and thread-safety.
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String typeName;

    /**
     * Constructor to initialize the typeName.
     *
     * @param typeName the name of the type
     */
    public Type(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Gets the name of the type.
     *
     * @return the type name
     */
    public String getTypeName() {
        return typeName;
    }
}