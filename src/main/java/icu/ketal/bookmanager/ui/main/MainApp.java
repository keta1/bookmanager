package icu.ketal.bookmanager.ui.main;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import icu.ketal.bookmanager.ui.login.LoginApp;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // load font
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(MainApp.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();

        var flow = new Flow(MainController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flow.start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setMaximized(false);
        decorator.setGraphic(new SVGGlyph(""));

        stage.setTitle("图书管理系统");

        double width = 800;
        double height = 600;

        Scene scene = new Scene(decorator, width, height);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                Objects.requireNonNull(LoginApp.class.getResource("/css/jfoenix-components.css")).toExternalForm(),
                Objects.requireNonNull(LoginApp.class.getResource("/css/jfoenix-main-demo.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
