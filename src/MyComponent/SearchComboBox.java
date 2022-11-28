package MyComponent;

import javax.swing.*;
import java.util.regex.Pattern;

public  class SearchComboBox extends JComboBox {
    public final ComboBoxModel allItems;

    public SearchComboBox(String[] items) {
        super(items);
        allItems = getModel();
        setEditable(true);
    }

    public SearchComboBox(JComboBox jcb) {
        super((ComboBoxModel) jcb);
        allItems = getModel();
        setEditable(true);
    }

    public String getText() {
        return ((JTextField) (getEditor().getEditorComponent())).getText();
    }

    public void setText(String s) {
        ((JTextField) (getEditor().getEditorComponent())).setText(s);
    }

    public Boolean isInItems(String input) {
        String toSearch = ".*" + input + ".*";
        for (int i = 0; i < getModel().getSize(); i++) {
            if (Pattern.matches(toSearch, (String) getModel().getElementAt(i))) {
                return true;
            }
        }
        return false;
    }
}
