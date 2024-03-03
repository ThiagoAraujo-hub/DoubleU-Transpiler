package sdk.enums;

public enum DoubleuMathOperators {
    PLUS("+"),
    MINUS("-"),
    MULT("*"),
    DIV("/"),
    MOD("%");

    private final String text;

    DoubleuMathOperators(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
