package ai.shreds.domain;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value object representing the contact information of a supplier.
 * This class is immutable and ensures that the phone number and email are valid.
 */
public final class DomainContactInfoValue {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9. ()-]{7,25}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private final String phone;
    private final String email;

    /**
     * Constructs a new DomainContactInfoValue with the specified phone number and email address.
     *
     * @param phone the phone number of the supplier
     * @param email the email address of the supplier
     * @throws IllegalArgumentException if the phone number or email address is invalid
     */
    public DomainContactInfoValue(String phone, String email) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address format");
        }
        this.phone = phone;
        this.email = email;
    }

    /**
     * Returns the phone number of the supplier.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the email address of the supplier.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    private boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainContactInfoValue that = (DomainContactInfoValue) o;
        return Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }

    @Override
    public String toString() {
        return "DomainContactInfoValue{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}