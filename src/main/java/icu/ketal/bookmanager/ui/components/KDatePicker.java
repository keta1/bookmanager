package icu.ketal.bookmanager.ui.components;

import com.jfoenix.controls.JFXDatePicker;

public class KDatePicker extends JFXDatePicker {
    public KDatePicker() {
        super();
        init();
    }

    private void init() {
        getStyleClass().removeAll("combo-box-base");
        focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                validate();
            }
        });
    }
}
