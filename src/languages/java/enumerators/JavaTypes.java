package languages.java.enumerators;

public enum JavaTypes {
    INT("int"),
    FLOAT("float"),
    BOOLEAN("boolean"),
    STRING("String");

    private final String text;

    JavaTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
