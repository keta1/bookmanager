package icu.ketal.bookmanager.ui.login;

import com.jfoenix.controls.JFXButton;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KTextField;
import io.datafx.controller.ViewNode;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginView extends StackPane {
    @ViewNode
    private final KTextField account;
    @ViewNode
    private final KPasswordField password;
    @ViewNode
    private final JFXButton register;
    @ViewNode
    private final JFXButton forgetPassword;
    @ViewNode
    private final JFXButton login;

    public LoginView() {
        final var pane = new VBox() {{
            setSpacing(30);
            setStyle("-fx-background-color:WHITE;-fx-padding:40;");
            setAlignment(javafx.geometry.Pos.TOP_CENTER);
        }};

        var title = new Label("图书管理系统") {{
            getStyleClass().add("title-label");
            setFont(Font.font(300));
            setPadding(new Insets(100, 0, 0, 0));
        }};

        account = new KTextField() {{
            setPadding(new Insets(30, 0, 0, 0));
            setPromptText("用户名：");
            needInput("请输入用户号");
            regex("^[a-zA-Z0-9_]{5,16}$", "用户名格式错误，请输入5-16位拉丁字母或数字");
        }};

        password = new KPasswordField() {{
            setPromptText("密码：");
            needInput("请输入密码");
            regex("^[a-zA-Z0-9_]{6,16}$", "密码格式错误，请输入6-16位拉丁字母或数字");
        }};
        pane.getChildren().addAll(title, account, password);

        var hBox = new BorderPane();
        hBox.setMaxWidth(300);
        register = new JFXButton("注册帐号");
        hBox.setLeft(register);
        forgetPassword = new JFXButton("忘记密码");
        hBox.setRight(forgetPassword);
        pane.getChildren().addAll(hBox);

        login = new JFXButton("登录");
        login.getStyleClass().add("button-raised");
        login.setMinWidth(300);
        login.setPrefWidth(300);
        pane.getChildren().addAll(login);

        getChildren().addAll(pane);
    }
}
