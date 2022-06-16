package icu.ketal.bookmanager.ui.register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbarLayout;
import icu.ketal.bookmanager.dao.impl.OperatorDaoImpl;
import icu.ketal.bookmanager.entry.Operator;
import icu.ketal.bookmanager.ui.components.KDatePicker;
import icu.ketal.bookmanager.ui.components.KPasswordField;
import icu.ketal.bookmanager.ui.components.KRadioGroup;
import icu.ketal.bookmanager.ui.components.KTextField;
import icu.ketal.bookmanager.util.DialogBuilder;
import icu.ketal.bookmanager.util.KeyEventUtil;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewNode;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;

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
    private KRadioGroup sex;
    @ViewNode
    private KTextField age;
    @ViewNode
    private KTextField IDNum;
    @ViewNode
    private KTextField phoneNum;
    @ViewNode
    private KDatePicker datePicker;
    @ViewNode
    @ActionTrigger("register")
    private JFXButton register;

    @PostConstruct
    public void init() {
        password.setOnKeyPressed(KeyEventUtil.enterToNext(confirmPassword));
    }

    @ActionMethod("register")
    private void register() throws Exception {
        var root = (DialogPane) register.getScene().getRoot();

        var fDialog = DialogPane.class.getDeclaredField("dialog");
        fDialog.setAccessible(true);
        var dialog = (Dialog<?>) fDialog.get(root);

        // use & to validate all fields
        if (!(id.validate() & account.validate() & password.validate()
                & confirmPassword.validate() & age.validate() & IDNum.validate() & phoneNum.validate())) return;
        var sex = this.sex.getSelectedChoice();
        if (sex == null) {
            var snackbar = new JFXSnackbar(root);
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout("请选择性别", "", null),
                    Duration.millis(3000), null));
            return;
        }
        LocalDate localDate = datePicker.getValue();
        if (localDate == null) {
            var snackbar = new JFXSnackbar(root);
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout("请选择入职日期", "", null),
                    Duration.millis(3000), null));
            return;
        }

        var id = this.id.getText();
        var account = this.account.getText();
        var password = this.password.getText();
        var age = this.age.getText();
        var IDNum = this.IDNum.getText();
        var time = new Date(localDate.toEpochDay()).getTime();
        var phoneNum = this.phoneNum.getText();

        var operator = new Operator() {{
            setId(Integer.parseInt(id));
            setUsername(account);
            setPassword(password);
            setSex(sex.equals("男"));
            setAge(Integer.parseInt(age));
            setIDNum(IDNum);
            setWorkTime(time);
            setPhoneNum(Long.parseLong(phoneNum));
            setAdmin(false);
        }};

        var operatorDao = new OperatorDaoImpl();
        var snackbar = new JFXSnackbar(root);
        snackbar.setPrefWidth(300);
        try {
            operatorDao.insert(operator);
            var successdialog = new DialogBuilder<>(root.getScene().getWindow())
                    .setMessage("注册成功")
                    .setOkButton("确定", dialog1 -> {
                        dialog1.close();
                        dialog.close();
                    })
                    .build();
            successdialog.show();
        } catch (Exception e) {
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout("注册失败", "确定", null),
                    Duration.millis(3000), null));
            e.printStackTrace();
        }
    }
}
