package icu.ketal.bookmanager.dao;

import icu.ketal.bookmanager.entry.Reader;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReaderDao {
    @Update("create table if not exists readers" +
            "(" +
            "    id              INTEGER primary key," +
            "    name            TEXT    not null," +
            "    sex             BOOLEAN not null," +
            "    age             INTEGER not null," +
            "    IDNum           TEXT    not null," +
            "    effectiveDate   INTEGER not null," +
            "    maxToBorrow     INTEGER not null," +
            "    phoneNumber     INTEGER not null," +
            "    deposit         REAL    not null," +
            "    certificateType TEXT    not null," +
            "    job             TEXT    not null," +
            "    issueDate       INTEGER not null" +
            ");")
    int createTable();

    @Update("drop table readers;")
    int dropTable();

    @Select("select * from readers;")
    List<Reader> selectAll();

    @Select("select * from readers where id = #{id};")
    Reader selectById(int id);

    @Delete("delete from readers where id = #{id};")
    int deleteById(int id);

    @Update("update readers set" +
            "   name            = #{name}," +
            "   sex             = #{sex}," +
            "   age             = #{age}," +
            "   IDNum           = #{IDNum}," +
            "   effectiveDate   = #{effectiveDate}," +
            "   maxToBorrow     = #{maxToBorrow}," +
            "   phoneNumber     = #{phoneNumber}," +
            "   deposit         = #{deposit}," +
            "   certificateType = #{certificateType}," +
            "   job             = #{job}," +
            "   issueDate       = #{issueDate} " +
            "where id           = #{id};")
    int update(Reader reader);

    @Insert("insert into readers(id, name, sex, age, IDNum, effectiveDate, maxToBorrow, phoneNumber, deposit, certificateType, job, issueDate) " +
            "values(#{id}, #{name}, #{sex}, #{age}, #{IDNum}, #{effectiveDate}, #{maxToBorrow}, #{phoneNumber}, #{deposit}, #{certificateType}, #{job}, #{issueDate});")
    int insert(Reader reader);

    @Update("replace into readers(id, name, sex, age, IDNum, effectiveDate, maxToBorrow, phoneNumber, deposit, certificateType, job, issueDate) " +
            "values(#{id}, #{name}, #{sex}, #{age}, #{IDNum}, #{effectiveDate}, #{maxToBorrow}, #{phoneNumber}, #{deposit}, #{certificateType}, #{job}, #{issueDate});")
    int replace(Reader reader);
}
