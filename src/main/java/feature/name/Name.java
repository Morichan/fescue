package feature.name;

public class Name {
    private String nameText;
    private boolean isSetName = false;

    public void setNameText(String text) {
        nameText = text;
        isSetName = true;
    }

    public String getNameText() {
        if (! isSetName) throw new IllegalStateException();
        return nameText;
    }
}
