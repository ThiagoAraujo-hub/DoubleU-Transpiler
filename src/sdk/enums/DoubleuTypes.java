package sdk.enums;
public enum DoubleuTypes {
    INTEGER("integer"),
    DECIMAL("decimal"),
    BOOLEAN("boolean"),
    STRING("string"),
    UNKNOWN("unknown");

    private final String text;

    DoubleuTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}