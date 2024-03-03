package languages.java.enumerators;

public enum JavaOperators {
    EQUAL("=="),
    DIFFERENT("!="),
    AND("&&"),
    OR("||");

    private final String text;

    JavaOperators(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}