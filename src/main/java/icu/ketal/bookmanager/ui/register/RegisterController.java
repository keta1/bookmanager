package icu.ketal.bookmanager.ui.register;

import com.jfoenix.controls.JFXButton;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KTextField;
import icu.ketal.bookmanager.ui.event.KeyEventUtil;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;

import javax.annotation.PostConstruct;

@ViewController(root = RegisterView.class)
public class RegisterController {
    @ViewNode
    private KTextField id;
    @ViewNode
    private KTextField account;
    @ViewNode
    private KPasswordField password;
    @ViewNode
    private KPasswordField confirmPassword;
    @ViewNode
    private KTextField age;
    @ViewNode
    private KTextField IDNum;
    @ViewNode
    private KTextField phoneNum;
    @ViewNode
    private JFXButton register;

    @PostConstruct
    public void init() {
        id.setOnKeyPressed(KeyEventUtil.enterToNext(account));
        account.setOnKeyPressed(KeyEventUtil.enterToNext(password));
        password.setOnKeyPressed(KeyEventUtil.enterToNext(confirmPassword));
    }
}
