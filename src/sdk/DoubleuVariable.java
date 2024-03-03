package sdk;

import sdk.enums.Scopes;

public class DoubleuVariable {
    public final String type;
    public final String name;
    public final Scopes scope;

    public DoubleuVariable(String type, String name, Scopes scope) {
        this.type = type;
        this.name = name;
        this.scope = scope;
    }
}