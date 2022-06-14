package icu.ketal.bookmanager.ui.register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KTextField;
import io.datafx.controller.ViewNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RegisterView extends StackPane {
    @ViewNode
    private final KTextField id;
    @ViewNode
    private final KTextField account;
    @ViewNode
    private final KPasswordField password;
    @ViewNode
    private final JFXButton register;

    public RegisterView() {
        var rootView = new VBox() {{
            setSpacing(40);
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
        }};
        rootView.getChildren().addAll(title, id, account, password);

        rootView.getChildren().addAll(new HBox() {{
            setAlignment(Pos.CENTER);
            setMinWidth(300);
            setPrefWidth(300);
            final var group = new ToggleGroup();
            var male = new JFXRadioButton("男");
            male.setPadding(new Insets(10));
            male.getStyleClass().add("custom-jfx-radio-button-blue");
            male.setToggleGroup(group);
            var female = new JFXRadioButton("女");
            female.setPadding(new Insets(10));
            female.getStyleClass().add("custom-jfx-radio-button-blue");
            female.setToggleGroup(group);
            getChildren().addAll(new Label("性别："), male, female);
        }});

        register = new JFXButton("注册") {{
            getStyleClass().add("button-raised");
            setMinWidth(300);
            setPrefWidth(300);
        }};
        rootView.getChildren().addAll(register);

        getChildren().addAll(rootView);
    }
}
