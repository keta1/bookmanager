package icu.ketal.bookmanager.ui.components;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class KPasswordField extends JFXPasswordField {
    public KPasswordField() {
        super();
        init();
    }

    private void init() {
        setLabelFloat(true);
        focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                validate();
            }
        });
    }

    public void needInput(String message) {
        getValidators().add(new RequiredFieldValidator(message) {{
            var warnIcon = new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
            warnIcon.getStyleClass().add("error");
            setIcon(warnIcon);
        }});
    }

    public void regex(String regex, String message) {
        getValidators().add(new RegexValidator(message) {{
            setRegexPattern(regex);
            var warnIcon = new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
            warnIcon.getStyleClass().add("error");
            setIcon(warnIcon);
        }});
    }
}
