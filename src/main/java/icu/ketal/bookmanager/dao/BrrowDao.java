package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrrowDao {
    @Update("")
    Integer createTable();
    @Update("")
    Integer dropTable();
    @Select("")
    List<Borrow> selectAll();
    @Select("")
    Borrow selectById(Integer id);
    @Delete("")
    Integer deleteById(Integer id);
    @Update("")
    Integer update(Borrow borrow);
    @Insert("")
    Integer insert(Borrow borrow);
}
