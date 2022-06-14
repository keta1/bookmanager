package icu.ketal.bookmanager.util;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Window;

public class DialogBuilder<R> {
    private String title;
    private String message;
    private String okButtonText;
    private String cancelButtonText;
    private String iconPath;
    private String style;
    private boolean overlayClose;
    private JFXDialogLayout layout;
    private Window window;

    public DialogBuilder() {
    }

    public DialogBuilder(Window window) {
        this.window = window;
    }

    public DialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public DialogBuilder setOkButtonText(String okButtonText) {
        this.okButtonText = okButtonText;
        return this;
    }

    public DialogBuilder setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
        return this;
    }

    public DialogBuilder setIconPath(String iconPath) {
        this.iconPath = iconPath;
        return this;
    }

    public DialogBuilder setStyle(String style) {
        this.style = style;
        return this;
    }

    public DialogBuilder setWindow(Window window) {
        this.window = window;
        return this;
    }

    public DialogBuilder setOverlayClose(boolean overlayClose) {
        this.overlayClose = overlayClose;
        return this;
    }

    public DialogBuilder setContent(JFXDialogLayout layout) {
        this.layout = layout;
        return this;
    }

    public JFXAlert<R> build() {
        return new JFXAlert<>(window) {{
            setOverlayClose(overlayClose);
            setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
            initModality(Modality.WINDOW_MODAL);
            setTitle(title);
            if (layout == null) {
                layout = new JFXDialogLayout();
                layout.setBody(new Label(message));
            }
            layout.setStyle("-fx-background-color:WHITE;");
            var pane = new ScrollPane();
            pane.setPrefSize(layout.getMinWidth(), layout.getMinHeight());
            pane.setContent(layout);
            layout.setActions(new JFXButton(okButtonText) {{
                getStyleClass().add("dialog-accept");
                setOnAction(event -> hideWithAnimation());
            }});
            setContent(pane);
        }};
    }
}
