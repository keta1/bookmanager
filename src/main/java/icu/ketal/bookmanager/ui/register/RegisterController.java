package icu.ketal.bookmanager.ui.register;

import com.jfoenix.controls.JFXButton;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KTextField;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;

@ViewController(root = RegisterView.class)
public class RegisterController {
    @ViewNode
    private KTextField account;
    @ViewNode
    private KPasswordField password;
    @ViewNode
    private JFXButton register;
}
