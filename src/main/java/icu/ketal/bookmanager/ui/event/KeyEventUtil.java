package icu.ketal.bookmanager.ui.event;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

public class KeyEventUtil {
    public static EventHandler<? super KeyEvent> enterToNext(Node next) {
        return (KeyEvent event) -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER)) {
                next.requestFocus();
            }
        };
    }

    public static EventHandler<? super KeyEvent> enterToFire(Button button) {
        return (KeyEvent event) -> {
            if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER)) {
                button.fire();
            }
        };
    }
}
