package sdk.enums;

public enum DoubleuLogicalOperators {
    EQUAL("is"),
    DIFFERENT("is not"),
    AND("and"),
    OR("or"),
    LESS("<"),
    GREATER(">"),
    LESSEQUAL("<="),
    GREATEREQUAL(">="),
    NOT("not");

    private final String text;

    DoubleuLogicalOperators(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
