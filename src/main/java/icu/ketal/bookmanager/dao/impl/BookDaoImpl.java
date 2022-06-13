package icu.ketal.bookmanager.dao.impl;

import icu.ketal.bookmanager.dao.BookDao;
import icu.ketal.bookmanager.db.DatabaseManager;
import icu.ketal.bookmanager.entry.Book;

import java.io.IOException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public int createTable() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            int result = mapper.createTable();
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int dropTable() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            var result = mapper.dropTable();
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Book> selectAll() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            return mapper.selectAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book selectById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            return mapper.selectById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            var result = mapper.deleteById(id);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Book book) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            var result = mapper.update(book);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insert(Book book) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BookDao.class);
            var result = mapper.insert(book);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
