package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryDao {
    @Update("create table if not exists categories" +
            "(" +
            "    id           INTEGER primary key," +
            "    name         TEXT    not null," +
            "    daysToReturn INTEGER not null," +
            "    finePerDay   REAL    not null" +
            ");")
    int createTable();

    @Update("drop table categories;")
    int dropTable();

    @Select("select * from categories;")
    List<Category> selectAll();

    @Select("select * from categories where id = #{id};")
    Category selectById(int id);

    @Delete("delete from categories where id = #{id};")
    int deleteById(int id);

    @Update("update categories set" +
            "   name         = #{name}," +
            "   daysToReturn = #{daysToReturn}," +
            "   finePerDay   = #{finePerDay} " +
            "where id        = #{id};")
    int update(Category category);

    @Insert("insert into categories(id, name, daysToReturn, finePerDay) " +
            "values(#{id}, #{name}, #{daysToReturn}, #{finePerDay});")
    int insert(Category category);
}
