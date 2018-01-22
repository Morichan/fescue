package feature;

import feature.name.Name;
import feature.type.Type;
import feature.visibility.Visibility;

import java.util.NoSuchElementException;

public class Attribute {
    private Name name;
    private Visibility visibility;
    private Type type;

    public void setName(Name name) {
        checkNullPointer(name);
        this.name = name;
    }

    public Name getName() throws NoSuchElementException {
        checkNoSuchElement(name);
        return name;
    }

    public void setVisibility(Visibility visibility) {
        checkNullPointer(visibility);
        this.visibility = visibility;
    }

    public Visibility getVisibility() throws NoSuchElementException {
        checkNoSuchElement(visibility);
        return visibility;
    }

    public void setType(Type type) {
        checkNullPointer(type);
        this.type = type;
    }

    public Type getType() throws NoSuchElementException {
        checkNoSuchElement(type);
        return type;
    }

    private void checkNullPointer(Object object) {
        if (object == null) throw new IllegalStateException();
    }

    private void checkNoSuchElement(Object object) {
        if (object == null) throw new NoSuchElementException();
    }
}