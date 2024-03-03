package sdk;

import java.util.ArrayList;
import java.util.List;

public class DoubleuObject {
    public final String name;
    public List<DoubleuProperty> properties = new ArrayList<DoubleuProperty>();

    public DoubleuObject(String name) {
        this.name = name;
    }

    public DoubleuObject(String name, List<DoubleuProperty> properties) {
        this.name = name;
        this.properties = properties;
    }

    public boolean addProperty(DoubleuProperty newProperty){
        if (containsProperty(newProperty)){
            return false;
        }

        properties.add(newProperty);
        return true;
    }

    public DoubleuProperty getProperty(String propertyName){
        for (DoubleuProperty property : properties) {
            if (property.name.equals(propertyName)){
                return property;
            }
        }

        return null;
    }

    public boolean containsProperty(String propertyName){
        boolean alreadyExists = false;
        for (DoubleuProperty property : properties) {
            if (property.name.equals(propertyName)){
                alreadyExists = true;
                break;
            }
        }

        return alreadyExists;
    }

    public boolean containsProperty(DoubleuProperty property){
        boolean alreadyExists = false;
        for (DoubleuProperty prop : properties) {
            if (prop.name.equals(property.name)){
                alreadyExists = false;
                break;
            }
        }

        return alreadyExists;
    }
}
