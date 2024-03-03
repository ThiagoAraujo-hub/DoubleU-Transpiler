package languages.java.enumerators;

public enum JavaImports {
    SCANNER("java.util.Scanner");

    private final String text;

    JavaImports(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
