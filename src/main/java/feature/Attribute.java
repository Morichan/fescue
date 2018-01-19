package feature;

import feature.name.Name;
import feature.visibility.Visibility;

public class Attribute {
    private Name name;
    private Visibility visibility;

    public void setName(Name name) {
        checkNullPointer(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setVisibility(Visibility visibility) {
        checkNullPointer(visibility);
        this.visibility = visibility;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    private void checkNullPointer(Object object) {
        if (object == null) throw new IllegalStateException();
    }
}