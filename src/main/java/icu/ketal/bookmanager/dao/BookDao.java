package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {
    @Update("create table if not exists books" +
            "(" +
            "    id          INTEGER not null" +
            "        primary key," +
            "    categoryId  INTEGER not null," +
            "    name        TEXT    not null," +
            "    author      TEXT    not null," +
            "    translator  TEXT    not null," +
            "    publisher   TEXT    not null," +
            "    publishDate INTEGER not null," +
            "    price       DOUBLE  not null" +
            ");")
    Integer createTable();
    @Delete("drop table books;")
    Integer dropTable();
    @Select("select * from books;")
    List<Book> selectAll();
    @Select("select * from books where id = #{id};")
    Book selectById(Integer id);
    @Delete("delete from books where id = #{id};")
    Integer deleteById(Integer id);
    @Update("update books set categoryId = #{categoryId}, name = #{name}, author = #{author}, translator = #{translator}, publisher = #{publisher}, publishDate = #{publishDate}, price = #{price} where id = #{id};")
    Integer update(Book book);
    @Insert("insert into books(id, categoryId, name, author, translator, publisher, publishDate, price) values(#{id}, #{categoryId}, #{name}, #{author}, #{translator}, #{publisher}, #{publishDate}, #{price});")
    Integer insert(Book book);
}
