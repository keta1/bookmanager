package icu.ketal.bookmanager.dao.impl;

import icu.ketal.bookmanager.dao.ReaderDao;
import icu.ketal.bookmanager.db.DatabaseManager;
import icu.ketal.bookmanager.entry.Reader;

import java.io.IOException;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {
    @Override
    public int createTable() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(ReaderDao.class);
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
            var mapper = session.getMapper(ReaderDao.class);
            var result = mapper.dropTable();
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Reader> selectAll() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(ReaderDao.class);
            return mapper.selectAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reader selectById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(ReaderDao.class);
            return mapper.selectById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(ReaderDao.class);
            var result = mapper.deleteById(id);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Reader reader) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(ReaderDao.class);
            var result = mapper.update(reader);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insert(Reader reader) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(ReaderDao.class);
            var result = mapper.insert(reader);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
