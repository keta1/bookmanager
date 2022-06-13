package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Brrow;

import java.util.List;

public interface BrrowDao {
    Integer createTable();
    Integer dropTable();
    List<Brrow> selectAll();
    Brrow selectById(Integer id);
    Integer deleteById(Integer id);
    Integer update(Brrow brrow);
    Integer insert(Brrow brrow);
}
