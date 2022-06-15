package icu.ketal.bookmanager.ui.components;

import com.jfoenix.controls.JFXRadioButton;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class KRadioGroup extends HBox {
    final ToggleGroup group = new ToggleGroup();
    private final String label;
    private final List<String> choices;
    private String selected;

    public KRadioGroup(String label, List<String> choices) {
        this.label = label;
        this.choices = choices;
    }

    public void initView(boolean vertical) {
        var rootView = vertical ? new VBox() : new HBox();
        rootView.getChildren().add(new Label(label));
        choices.forEach(str -> {
            var button = createButton(str);
            button.setUserData(str);
            rootView.getChildren().addAll(button);
        });
        group.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                    if (group.getSelectedToggle() != null) {
                        selected = group.getSelectedToggle().getUserData().toString();
                    }
                }
        );
        getChildren().add(rootView);
    }

    public void alignment(Pos alignment) {
        var node = getChildren().get(0);
        try {
            node.getClass().getDeclaredMethod("setAlignment", Pos.class).invoke(node, alignment);
        } catch (Exception ignored) {
        }
    }

    private JFXRadioButton createButton(String text) {
        var radioButton = new JFXRadioButton(text);
        radioButton.setPadding(new Insets(10));
        radioButton.getStyleClass().add("custom-jfx-radizo-button-blue");
        radioButton.setToggleGroup(group);
        return radioButton;
    }

    public String getSelectedChoice() {
        return selected;
    }
}
