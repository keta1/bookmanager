package icu.ketal.bookmanager.ui;

import com.jfoenix.assets.JFoenixResources;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

abstract class BaseApplication extends Application {
    private PrimaryStageInitializer initializer;
    private Stage primaryStage;

    abstract Scene createScene();

    protected void dismiss() {
        primaryStage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initializer.run(primaryStage);
        var scene = createScene();
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                BaseApplication.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPrimaryStageInitializer(PrimaryStageInitializer initializer) {
        this.initializer = initializer;
    }

    public interface PrimaryStageInitializer {
        void run(Stage primaryStage);
    }
}
