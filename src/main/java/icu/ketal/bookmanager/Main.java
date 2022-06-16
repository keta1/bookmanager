package icu.ketal.bookmanager;

import icu.ketal.bookmanager.db.DatabaseManager;
import icu.ketal.bookmanager.ui.login.LoginApp;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initTables();
        Application.launch(LoginApp.class, args);
    }
}