package icu.ketal.bookmanager.ui.main.borrow;

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import icu.ketal.bookmanager.dao.BookDao;
import icu.ketal.bookmanager.dao.BorrowDao;
import icu.ketal.bookmanager.dao.impl.BookDaoImpl;
import icu.ketal.bookmanager.dao.impl.BorrowDaoImpl;
import icu.ketal.bookmanager.entry.Book;
import icu.ketal.bookmanager.entry.Borrow;
import icu.ketal.bookmanager.util.DialogBuilder;
import icu.ketal.bookmanager.util.OperatorManager;
import icu.ketal.bookmanager.util.UIHelper;
import io.datafx.controller.ViewController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import static javafx.animation.Interpolator.EASE_BOTH;

@ViewController(value = "/fxml/ui/borrow/borrow.fxml")
public class BorrowController {
    private final BookDao bookDao = new BookDaoImpl();
    private final BorrowDao borrowDao = new BorrowDaoImpl();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXMasonryPane masonryPane;

    @PostConstruct
    public void init() throws Exception {
        reload();
        JFXScrollPane.smoothScrolling(scrollPane);
    }

    private void borrow(Book book) throws IOException {
        var controller = new InputController(true, (id, readerId, shouldReturnDate) -> {
            var msg = "";
            try {
                var operationId = OperatorManager.getCurrentOperator().getId();
                var borrow = new Borrow(id, book.getId(), operationId, readerId, false, System.currentTimeMillis(), shouldReturnDate);
                borrowDao.insert(borrow);
                msg = "借阅成功";
                reload();
            } catch (Exception e) {
                e.printStackTrace();
                msg = "借阅失败";
            }
            var snackbar = new JFXSnackbar((Pane) scrollPane.getScene().getRoot());
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout(msg, "", null),
                    Duration.millis(3000), null));
        });
        showDialog(controller);
    }

    private void ret(Book book) throws IOException {
        var controller = new InputController(false, (id, userId, shouldReturnDate) -> {
            var msg = "";
            try {
                var borrow = borrowDao.findUnReturnBorrow(book.getId());
                borrow.setReturned(true);
                borrowDao.update(borrow);
                msg = "还书成功";
                reload();
            } catch (Exception e) {
                e.printStackTrace();
                msg = "还书失败";
            }
            var snackbar = new JFXSnackbar((Pane) scrollPane.getScene().getRoot());
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout(msg, "", null),
                    Duration.millis(3000), null));
        });
        showDialog(controller);
    }

    private void showDialog(InputController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/borrow/InputDialog.fxml"));
        loader.setController(controller);
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(scrollPane.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
    }

    private void onClick(Book book) throws Exception {
        var borrowed = isBorrowed(book);
        if (borrowed) {
            ret(book);
        } else {
            borrow(book);
        }
    }

    private void reload() throws Exception {
        masonryPane.getChildren().clear();
        var books = bookDao.selectAll();
        var children = new ArrayList<Node>();
        int index = 1;
        for (var book : books) {
            children.add(createChild(book, index));
        }
        masonryPane.getChildren().addAll(children);
        Platform.runLater(() -> scrollPane.requestLayout());
    }

    private StackPane createChild(Book book, int index) throws Exception {
        StackPane child = new StackPane();
        child.setPrefWidth(120);
        child.setPrefHeight(200);
        JFXDepthManager.setDepth(child, 1);

        // create content
        StackPane header = new StackPane();
        String headerColor = getDefaultColor(index % 12);
        header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: " + headerColor);
        VBox.setVgrow(header, Priority.ALWAYS);
        StackPane body = new StackPane();
        body.setMinHeight(75);
        VBox content = new VBox();
        content.getChildren().addAll(header, body);
        body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");

        var vbox = new VBox();
        vbox.setSpacing(10);
        var title = new Label(book.getName());
        title.setPadding(new Insets(20, 0, 0, 0));
        var author = new Label(book.getAuthor());
        vbox.getChildren().addAll(title, author);
        body.getChildren().add(vbox);

        // create button
        JFXButton button = new JFXButton("");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
        button.setPrefSize(40, 40);
        button.setRipplerFill(Color.valueOf(headerColor));
        button.setScaleX(0);
        button.setScaleY(0);
        button.setGraphic(UIHelper.getSVG(isBorrowed(book) ? "truck" : "credit-card", 20));
        button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
            return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
        }, header.boundsInParentProperty(), button.heightProperty()));
        button.setOnMouseClicked(e -> {
            try {
                onClick(book);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        StackPane.setMargin(button, new Insets(0, 12, 0, 0));
        StackPane.setAlignment(button, Pos.TOP_RIGHT);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(240),
                new KeyValue(button.scaleXProperty(),
                        1,
                        EASE_BOTH),
                new KeyValue(button.scaleYProperty(),
                        1,
                        EASE_BOTH)));
        animation.setDelay(Duration.millis(100 * index + 1000));
        animation.play();
        child.getChildren().addAll(content, button);
        return child;
    }

    private boolean isBorrowed(Book book) {
        return bookDao.findUnReturnBorrow(book.getId()) != null;
    }

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0 -> color = "#8F3F7E";
            case 1 -> color = "#B5305F";
            case 2 -> color = "#CE584A";
            case 3 -> color = "#DB8D5C";
            case 4 -> color = "#DA854E";
            case 5 -> color = "#E9AB44";
            case 6 -> color = "#FEE435";
            case 7 -> color = "#99C286";
            case 8 -> color = "#01A05E";
            case 9 -> color = "#4A8895";
            case 10 -> color = "#16669B";
            case 11 -> color = "#2F65A5";
            case 12 -> color = "#4E6A9C";
            default -> {
            }
        }
        return color;
    }

    public interface OnClickListener {
        void onClick(int id, int readerId, long shouldReturnDate);
    }

    public static final class InputController {
        private final OnClickListener listener;
        private final boolean isBorrow;
        @FXML
        private Label title;
        @FXML
        private JFXTextField id;
        @FXML
        private JFXTextField userId;
        @FXML
        private JFXDatePicker shouldReturnDate;

        public InputController(boolean isBorrow, OnClickListener listener) {
            this.isBorrow = isBorrow;
            this.listener = listener;
        }

        @FXML
        public void initialize() {
            if (!isBorrow) {
                title.setText("还书");
                id.setVisible(false);
                shouldReturnDate.setVisible(false);
            }

        }

        @FXML
        private void onClick() {
            var time = shouldReturnDate.getValue() == null ? 0 : Timestamp.valueOf(shouldReturnDate.getValue().atStartOfDay()).getTime();
            listener.onClick(
                    Integer.parseInt(id.getText().equals("") ? "0" : id.getText()),
                    Integer.parseInt(userId.getText().equals("") ? "0" : userId.getText()),
                    time
            );
            id.getScene().getWindow().hide();
        }
    }
}
