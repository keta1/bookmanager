package icu.ketal.bookmanager.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Map;
import java.util.Set;

public class LoginApp extends BaseApplication {
    private Map map = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    Scene createScene() {
        Text name = new Text("用户名：");
        Text password = new Text("密码：");

        TextField e_name = new TextField();
        e_name.setPrefWidth(150);

        TextField e_password = new TextField();

        Button clear = new Button("清除");
        Button login = new Button("登录");

        GridPane gridPane = new GridPane();
        gridPane.add(name, 0, 0);
        gridPane.add(e_name, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(e_password, 1, 1);
        gridPane.add(clear, 0, 2);
        gridPane.add(login, 1, 2);
        GridPane.setMargin(login, new Insets(0, 0, 0, 115));
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setVgap(10);
        gridPane.setHgap(5);

        Scene scene = new Scene(gridPane);
        clear.setOnAction(event -> {
            e_name.setText("");
            e_password.setText("");
        });
        login.setOnAction(event -> {
            String username = null;
            String password1 = null;
            Set set = map.entrySet();
            for (Object o : set) {
                String[] strs = o.toString().split("=");
                username = strs[0];
                password1 = strs[1];
            }

            if (e_name.getText().equals(username) && e_password.getText().equals(password1)) {
                dismiss();

//                    try {
//                        MainApp mainApp = new MainApp();
//                        mainApp.showWindow();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

            } else {
                System.out.println("密码输入错误");
            }
        });
        return scene;
    }
}
