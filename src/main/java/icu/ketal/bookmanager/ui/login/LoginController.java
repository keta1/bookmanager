package icu.ketal.bookmanager.ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import icu.ketal.bookmanager.dao.impl.OperatorDaoImpl;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KTextField;
import icu.ketal.bookmanager.ui.main.MainApp;
import icu.ketal.bookmanager.ui.register.RegisterController;
import icu.ketal.bookmanager.util.DialogBuilder;
import icu.ketal.bookmanager.util.KeyEventUtil;
import icu.ketal.bookmanager.util.OperatorManager;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;

@ViewController(root = LoginView.class)
public class LoginController {
    @ViewNode
    private KTextField account;
    @ViewNode
    private KPasswordField password;
    @ViewNode
    @ActionTrigger("register")
    private JFXButton register;
    @ViewNode
    @ActionTrigger("forgetPassword")
    private JFXButton forgetPassword;
    @ViewNode
    @ActionTrigger("login")
    private JFXButton login;

    @PostConstruct
    public void init() {
        password.setOnKeyPressed(KeyEventUtil.enterToFire(login));
    }

    @ActionMethod("register")
    private void register() throws Exception {
        var layout = new JFXDialogLayout();
        var flow = new Flow(RegisterController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flow.start(container);
        layout.getChildren().addAll(container.getView());
        new DialogBuilder<>(forgetPassword.getScene().getWindow())
                .setTitle("注册")
                .setOverlayClose(false)
                .setContent(layout)
                .build()
                .show();
    }

    @ActionMethod("forgetPassword")
    private void forgetPassword() {
        new DialogBuilder<>(forgetPassword.getScene().getWindow())
                .setTitle("忘记密码")
                .setMessage("请联系管理员重置密码")
                .setOkButtonText("好的")
                .setOverlayClose(false)
                .build()
                .showAndWait();
    }

    @ActionMethod("login")
    private void login() throws Exception {
        if (!(account.validate() && password.validate())) return;
        var dao = new OperatorDaoImpl();
        var check = dao.selectAll()
                .stream()
                .anyMatch(operator -> operator.getUsername().equals(account.getText())
                        && operator.getPassword().equals(password.getText()));
        if (check) {
            OperatorManager.setCurrentOperator(
                    dao.selectAll().stream()
                            .filter(operator -> operator.getUsername().equals(account.getText()))
                            .findFirst()
                            .get()
            );
            login.getScene().getWindow().hide();
            new MainApp().start(new Stage());
        } else {
            new DialogBuilder<>(forgetPassword.getScene().getWindow())
                    .setMessage("登陆失败,请检查用户名或密码是否正确！")
                    .setOkButtonText("好的")
                    .setOverlayClose(false)
                    .build()
                    .showAndWait();
        }
    }
}