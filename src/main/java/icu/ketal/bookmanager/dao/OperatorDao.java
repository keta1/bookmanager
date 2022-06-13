package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Operator;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OperatorDao {
    @Update("create table if not exists operators" +
            "(" +
            "    id            INTEGER primary key," +
            "    name          TEXT    not null," +
            "    sex           BOOLEAN not null," +
            "    age           integer not null," +
            "    IDNum         TEXT    not null," +
            "    workTime INTEGER not null," +
            "    isAdmin       BOOLEAN not null," +
            "    password      text    not null" +
            ");")
    int createTable();

    @Update("drop table operators;")
    int dropTable();

    @Select("select * from operators;")
    List<Operator> selectAll();

    @Select("select * from operators where id = #{id};")
    Operator selectById(int id);

    @Delete("delete from operators where id = #{id};")
    int deleteById(int id);

    @Update("update operators set" +
            "   name     = #{name}," +
            "   sex      = #{sex}," +
            "   age      = #{age}," +
            "   IDNum    = #{IDNum}," +
            "   workTime = #{workTime}," +
            "   isAdmin  = #{isAdmin}," +
            "   password = #{password} " +
            "where id    = #{id}")
    int update(Operator operator);

    @Insert("insert into operators(id, name, sex, age, IDNum, workTime, isAdmin, password) " +
            "values(#{id}, #{name}, #{sex}, #{age}, #{IDNum}, #{workTime}, #{isAdmin}, #{password});")
    int insert(Operator operator);
}
