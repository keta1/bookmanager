package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BorrowDao {
    @Update("create table if not exists borrows" +
            "(" +
            "    id               INTEGER primary key," +
            "    bookId           INTEGER references books," +
            "    operatorId       INTEGER references operators," +
            "    readerId         INTEGER references readers," +
            "    returned         BOOLEAN not null," +
            "    borrowDate       INTEGER not null," +
            "    shouldReturnDate INTEGER not null" +
            ");")
    int createTable();

    @Update("drop table borrows;")
    int dropTable();

    @Select("select * from borrows;")
    List<Borrow> selectAll();

    @Select("select * from borrows where id = #{id};")
    Borrow selectById(int id);

    @Delete("delete from borrows where id = #{id};")
    int deleteById(int id);

    @Update("update borrows set" +
            "   bookId           = #{bookId}," +
            "   operatorId       = #{operatorId}," +
            "   readerId         = #{readerId}," +
            "   returned         = #{returned}," +
            "   borrowDate       = #{borrowDate}," +
            "   shouldReturnDate = #{shouldReturnDate} " +
            "where id            = #{id};")
    int update(Borrow borrow);

    @Insert("insert into borrows(id, bookId, operatorId, readerId, returned, borrowDate, shouldReturnDate) " +
            "values(#{id}, #{bookId}, #{operatorId}, #{readerId}, #{returned}, #{borrowDate}, #{shouldReturnDate});")
    int insert(Borrow borrow);
}
