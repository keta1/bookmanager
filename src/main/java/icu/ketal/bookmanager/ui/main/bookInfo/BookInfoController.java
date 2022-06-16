package icu.ketal.bookmanager.ui.main.bookInfo;

import com.jfoenix.controls.*;
import com.jfoenix.effects.JFXDepthManager;
import icu.ketal.bookmanager.dao.BookDao;
import icu.ketal.bookmanager.dao.CategoryDao;
import icu.ketal.bookmanager.dao.impl.BookDaoImpl;
import icu.ketal.bookmanager.dao.impl.CategoryDaoImpl;
import icu.ketal.bookmanager.entry.Book;
import icu.ketal.bookmanager.ui.components.KDatePicker;
import icu.ketal.bookmanager.util.DialogBuilder;
import icu.ketal.bookmanager.util.UIHelper;
import io.datafx.controller.ViewController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

@ViewController(value = "/fxml/ui/bookInfo.fxml")
public class BookInfoController {
    private final BookDao bookDao = new BookDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXMasonryPane masonryPane;

    @PostConstruct
    public void init() throws Exception {
        reload();
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

        JFXScrollPane.smoothScrolling(scrollPane);
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
        button.setGraphic(UIHelper.getSVG("pencil", 20));
        button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
            return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
        }, header.boundsInParentProperty(), button.heightProperty()));
        button.setOnMouseClicked(e -> {
            try {
                onModify(book);
            } catch (IOException ex) {
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

    @FXML
    public void onNewButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/ModifyBookInfo.fxml"));
        loader.setController(new InputController(new Book(), true));
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(masonryPane.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
    }

    private void onModify(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/ModifyBookInfo.fxml"));
        loader.setController(new InputController(book, false));
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(masonryPane.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
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

    public final class InputController {
        private final Book book;
        private final boolean add;
        @FXML
        private Label title;
        @FXML
        private JFXTextField id;
        @FXML
        private JFXComboBox categoryId;
        @FXML
        private JFXTextField name;
        @FXML
        private JFXTextField author;
        @FXML
        private JFXTextField translator;
        @FXML
        private JFXTextField publisher;
        @FXML
        private KDatePicker publishDate;
        @FXML
        private JFXTextField price;

        public InputController(Book book, boolean add) {
            this.book = book;
            this.add = add;
        }

        @FXML
        public void initialize() {
            if (add) {
                title.setText("添加图书");
                id.setDisable(false);
            }
            id.setText("" + book.getId());
            setUpCategory();
            name.setText(book.getName());
            author.setText(book.getAuthor());
            translator.setText(book.getTranslator());
            publisher.setText(book.getPublisher());
            var instance = java.time.Instant.ofEpochMilli(book.getPublishDate());
            var localDateTime = java.time.LocalDateTime
                    .ofInstant(instance, java.time.ZoneId.of("Asia/Kolkata"));
            publishDate.setValue(localDateTime.toLocalDate());
            price.setText(String.valueOf(book.getPrice()));
        }

        @FXML
        private void onClick() {
            String msg;
            try {
                var text = categoryId.getEditor().getText();
                book.setId(Integer.parseInt(id.getText()));
                book.setName(name.getText());
                book.setAuthor(author.getText());
                book.setTranslator(translator.getText());
                book.setPublisher(publisher.getText());
                var localDate = publishDate.getValue();
                Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
                var time = timestamp.getTime();
                book.setPublishDate(time);
                book.setPrice(Double.parseDouble(price.getText()));
                bookDao.replace(book);
                id.getScene().getWindow().hide();
                msg = "修改成功";
            } catch (Exception e) {
                e.printStackTrace();
                msg = "修改失败";
            }
            var snackbar = new JFXSnackbar((Pane) scrollPane.getScene().getRoot());
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout(msg, "", null),
                    Duration.millis(3000), null));
        }

        private void setUpCategory() {
            var categories = categoryDao.selectAll();
            final ObservableList<Label> dummy = FXCollections.observableArrayList();
            categories.stream().map(c -> {
                var label = new Label();
                label.setText(c.getName());
                return label;
            }).forEach(dummy::add);
            categoryId.setItems(dummy);
        }
    }
}
