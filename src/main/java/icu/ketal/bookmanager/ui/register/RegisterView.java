package icu.ketal.bookmanager.ui.register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.validation.base.ValidatorBase;
import icu.ketal.bookmanager.ui.components.KDatePicker;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KRadioGroup;
import icu.ketal.bookmanager.ui.components.KTextField;
import io.datafx.controller.ViewNode;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class RegisterView extends StackPane {
    @ViewNode
    private final KTextField id;
    @ViewNode
    private final KTextField account;
    @ViewNode
    private final KPasswordField password;
    @ViewNode
    private final KPasswordField confirmPassword;
    @ViewNode
    private final KTextField age;
    @ViewNode
    private final KTextField IDNum;
    @ViewNode
    private final KTextField phoneNum;
    @ViewNode
    private final JFXButton register;

    public RegisterView() {
        var rootView = new VBox() {{
            setSpacing(35);
            setStyle("-fx-background-color:WHITE;-fx-padding:10;");
            setAlignment(Pos.TOP_CENTER);
        }};

        var title = new Label("注册") {{
            getStyleClass().add("title-label-lite");
            setFont(Font.font(300));
        }};

        id = new KTextField() {{
            setPromptText("员工编号：");
            needInput("请输入员工编号");
            regex("^[0-9_]{5,16}$", "员工编号格式错误，请输入5-16位数字");
        }};

        account = new KTextField() {{
            setPromptText("用户名：");
            needInput("请输入用户名");
            regex("^[a-zA-Z0-9_]{5,16}$", "用户名格式错误，请输入5-16位拉丁字母或数字");
        }};

        password = new KPasswordField() {{
            setPromptText("密码：");
            needInput("请输入密码");
            regex("^[a-zA-Z0-9_]{6,16}$", "密码格式错误，请输入6-16位拉丁字母或数字");
            getValidators().add(new ValidatorBase("两次密码输入不一致") {
                @Override
                protected void eval() {
                    hasErrors.set(!password.getText().equals(confirmPassword.getText()));
                }
            });
        }};

        confirmPassword = new KPasswordField() {{
            setPromptText("确认密码：");
            needInput("请再次输入密码");
            regex("^[a-zA-Z0-9_]{6,16}$", "密码格式错误，请输入6-16位拉丁字母或数字");
            getValidators().add(new ValidatorBase("两次密码输入不一致") {
                @Override
                protected void eval() {
                    hasErrors.set(!password.getText().equals(confirmPassword.getText()));
                }
            });
        }};
        rootView.getChildren().addAll(title, id, account, password, confirmPassword);

        var chose = new KRadioGroup("性别：", List.of("男", "女"));
        var choseView = chose.getView(false);
        try {
            choseView.getClass().getDeclaredMethod("setAlignment", Pos.class).invoke(choseView, Pos.CENTER_LEFT);
        } catch (Exception ignored) {
        }
        choseView.setMaxWidth(300);
        choseView.setPrefWidth(300);
        rootView.getChildren().addAll(choseView);

        age = new KTextField() {{
            setPromptText("年龄：");
            needInput("请输入年龄");
            regex("^[0-9_]{1,3}$", "年龄输入错误");
        }};

        IDNum = new KTextField() {{
            setPromptText("证件号码：");
            needInput("请输入年龄");
            regex("^[0-9_]{1,3}$", "年龄输入错误");
        }};

        phoneNum = new KTextField() {{
            setPromptText("电话号码：");
            needInput("请输入电话号码");
            regex("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", "电话号码输入错误");
        }};
        rootView.getChildren().addAll(age, IDNum, phoneNum);

        var datePicker = new KDatePicker() {{
            setPromptText("入职日期：");
            getStyleClass().addAll("custom-color-picker");
            setMaxWidth(300);
        }};
        rootView.getChildren().addAll(datePicker);

        register = new JFXButton("注册") {{
            getStyleClass().add("button-raised");
            setMinWidth(300);
            setPrefWidth(300);
        }};
        rootView.getChildren().addAll(register);

        getChildren().addAll(rootView);
    }
}
