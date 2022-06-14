package icu.ketal.bookmanager.ui.components;

import com.jfoenix.controls.JFXRadioButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class KRadioGroup {
    final ToggleGroup group = new ToggleGroup();
    private String label;
    private List<String> choices;
    private int selectedIndex = -1;

    public KRadioGroup(String label, List<String> choices) {
        this.label = label;
        this.choices = choices;
    }

    public Pane getView(boolean vertical) {
        var rootView = vertical ? new VBox() : new HBox();
        rootView.getChildren().add(new Label(label));
        choices.forEach(str -> {
            var button = createButton(str);
            rootView.getChildren().addAll(button);
        });
        return rootView;
    }

    private JFXRadioButton createButton(String text) {
        var radioButton = new JFXRadioButton(text);
        radioButton.setPadding(new Insets(10));
        radioButton.getStyleClass().add("custom-jfx-radio-button-blue");
        radioButton.setToggleGroup(group);
        return radioButton;
    }

    public String getSelectedChoice() {
        if (selectedIndex == -1) {
            return null;
        }
        return choices.get(selectedIndex);
    }
}
