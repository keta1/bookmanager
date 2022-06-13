package icu.ketal.bookmanager;

import icu.ketal.bookmanager.db.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.initTables();
    }
}