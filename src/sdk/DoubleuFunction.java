package sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoubleuFunction {
    public final String name;
    public final String type;
    public List<DoubleuParameter> parameters = new ArrayList<DoubleuParameter>();

    public DoubleuFunction(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public DoubleuFunction(String name, String type, List<DoubleuParameter> parameters) {
        this.name = name;
        this.type = type;
        this.parameters = parameters;
    }

    public boolean addParameter(DoubleuParameter newParameter){
        if (containsParameter(newParameter)){
            return false;
        }

        parameters.add(newParameter);
        return true;
    }

    public boolean containsParameter(String parameterName){
        boolean alreadyExists = false;
        for (DoubleuParameter parameter : parameters) {
            if (parameter.name.equals(parameterName)){
                alreadyExists = true;
                break;
            }
        }

        return alreadyExists;
    }

    public boolean containsParameter(DoubleuParameter parameter){
        boolean alreadyExists = false;
        for (DoubleuParameter prop : parameters) {
            if (prop.name.equals(parameter.name)){
                alreadyExists = false;
                break;
            }
        }

        return alreadyExists;
    }

    public String getSignatureString(){
        return "function " + name + getParametersSignature();
    }

    public String getParametersSignature(){
        String parametersSignature = "(";
        for (int i = 0; i < parameters.size(); i++) {
            var parameter = parameters.get(i);
            parametersSignature += parameter.type + " " + parameter.name;

            if (i != parameters.size() - 1){
                parametersSignature += ", ";
            }
        }

        parametersSignature += ")";
        return parametersSignature;
    }
}
