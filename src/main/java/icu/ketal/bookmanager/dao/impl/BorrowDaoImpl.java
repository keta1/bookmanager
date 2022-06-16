package icu.ketal.bookmanager.dao.impl;

import icu.ketal.bookmanager.dao.BorrowDao;
import icu.ketal.bookmanager.db.DatabaseManager;
import icu.ketal.bookmanager.entry.Borrow;

import java.io.IOException;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao {
    @Override
    public int createTable() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
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
            var mapper = session.getMapper(BorrowDao.class);
            var result = mapper.dropTable();
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Borrow> selectAll() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
            return mapper.selectAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Borrow selectById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
            return mapper.selectById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Borrow findUnReturnBorrow(int bookId) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
            return mapper.findUnReturnBorrow(bookId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
            var result = mapper.deleteById(id);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Borrow borrow) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
            var result = mapper.update(borrow);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insert(Borrow borrow) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(BorrowDao.class);
            var result = mapper.insert(borrow);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
