package icu.ketal.bookmanager.ui.components;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

public class KDatePicker extends JFXDatePicker {
    public KDatePicker() {
        super();
        init();
    }

    private void init() {
        getStyleClass().removeAll("combo-box-base");
        ((JFXTextField) getEditor()).setLabelFloat(true);
        focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                validate();
            }
        });
    }
}
