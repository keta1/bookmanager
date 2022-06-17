package icu.ketal.bookmanager.ui.main.system;

import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import icu.ketal.bookmanager.dao.OperatorDao;
import icu.ketal.bookmanager.dao.impl.OperatorDaoImpl;
import icu.ketal.bookmanager.ui.components.KDatePicker;
import icu.ketal.bookmanager.ui.components.KTextField;
import icu.ketal.bookmanager.util.DialogBuilder;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Timestamp;

@ViewController(value = "/fxml/ui/system/system.fxml")
public class SystemController {
    @FXML
    private VBox root;
    private OperatorDao dao = new OperatorDaoImpl();

    @FXML
    public void onModifyPassword() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/system/ModifyPass.fxml"));
        loader.setController(new PassController());
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(root.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
    }

    @FXML
    public void onModifyInfo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/system/ModifyInfo.fxml"));
        loader.setController(new PassController());
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(root.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
    }

    public final class PassController {
        @FXML
        KTextField id;
        @FXML
        KTextField password;

        @FXML
        private void onClick() {
            String msg;
            try {
                var id = Integer.parseInt(this.id.getText());
                var op = dao.selectById(id);
                op.setPassword(password.getText());
                dao.update(op);
                password.getScene().getWindow().hide();
                msg = "修改成功";
            } catch (Exception e) {
                e.printStackTrace();
                msg = "修改失败";
            }
            var snackbar = new JFXSnackbar((Pane) root.getScene().getRoot());
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout(msg, "", null),
                    Duration.millis(3000), null));
        }
    }

    public final class InfoController {
        @FXML
        private Label title;
        @FXML
        private JFXTextField id;
        @FXML
        private JFXTextField username;
        @FXML
        private JFXTextField sex;
        @FXML
        private JFXTextField age;
        @FXML
        private JFXTextField IDNum;
        @FXML
        private KDatePicker effectiveDate;
        @FXML
        private JFXTextField maxToBorrow;
        @FXML
        private JFXTextField phoneNum;
        @FXML
        private JFXTextField password;

        @FXML
        private void onClick() {
            String msg;
            try {
                var opId = Integer.parseInt(id.getText());
                var operator = dao.selectById(opId);
                operator.setUsername(username.getText());
                operator.setSex(sex.getText().equals("男"));
                operator.setAge(Integer.parseInt(age.getText()));
                operator.setIDNum(IDNum.getText());
                var workTime = Timestamp.valueOf(effectiveDate.getValue().atStartOfDay()).getTime();
                operator.setWorkTime(workTime);
                operator.setPhoneNum(Long.parseLong(phoneNum.getText()));
                operator.setPassword(password.getText());
                dao.replace(operator);
                id.getScene().getWindow().hide();
                msg = "修改成功";
            } catch (Exception e) {
                e.printStackTrace();
                msg = "修改失败";
            }
            var snackbar = new JFXSnackbar((Pane) root.getScene().getRoot());
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout(msg, "", null),
                    Duration.millis(3000), null));
        }
    }
}
