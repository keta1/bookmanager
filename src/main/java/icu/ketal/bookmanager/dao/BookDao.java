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
            "    id          INTEGER primary key," +
            "    categoryId  INTEGER references categories," +
            "    name        TEXT    not null," +
            "    author      TEXT    not null," +
            "    translator  TEXT    not null," +
            "    publisher   TEXT    not null," +
            "    publishDate INTEGER not null," +
            "    price       REAL    not null" +
            ");")
    int createTable();

    @Delete("drop table books;")
    int dropTable();

    @Select("select * from books;")
    List<Book> selectAll();

    @Select("select * from books where id = #{id};")
    Book selectById(int id);

    @Delete("delete from books where id = #{id};")
    int deleteById(int id);

    @Update("update books set" +
            "   categoryId  = #{categoryId}," +
            "   name        = #{name}," +
            "   author      = #{author}," +
            "   translator  = #{translator}," +
            "   publisher   = #{publisher}," +
            "   publishDate = #{publishDate}," +
            "   price       = #{price} " +
            "where id       = #{id};")
    int update(Book book);

    @Insert("insert into books(id, categoryId, name, author, translator, publisher, publishDate, price) " +
            "values(#{id}, #{categoryId}, #{name}, #{author}, #{translator}, #{publisher}, #{publishDate}, #{price});")
    int insert(Book book);
}
