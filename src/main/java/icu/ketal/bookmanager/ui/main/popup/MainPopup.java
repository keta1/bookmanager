package icu.ketal.bookmanager.ui.main.popup;

import com.jfoenix.controls.JFXListView;

public class MainPopup extends JFXListView<Object> {
    public MainPopup() {
        super();
        setId("toolbarPopupList");
        getStyleClass().add("option-list-view");
    }
}
