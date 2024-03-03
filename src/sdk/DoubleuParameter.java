package sdk;

import sdk.enums.Scopes;

public class DoubleuParameter extends DoubleuVariable {
    public DoubleuParameter(String type, String name) {
        super(type, name, Scopes.FUNCTION);
    }
}
