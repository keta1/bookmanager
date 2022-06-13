package icu.ketal.bookmanager;

import icu.ketal.bookmanager.dao.impl.BookDaoImpl;

public class Main {
    public static void main(String[] args) {
        var dao = new BookDaoImpl();
        dao.createTable();
    }
}