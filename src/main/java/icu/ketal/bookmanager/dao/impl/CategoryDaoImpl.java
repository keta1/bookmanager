package icu.ketal.bookmanager.dao.impl;

import icu.ketal.bookmanager.dao.CategoryDao;
import icu.ketal.bookmanager.db.DatabaseManager;
import icu.ketal.bookmanager.entry.Category;

import java.io.IOException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public int createTable() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(CategoryDao.class);
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
            var mapper = session.getMapper(CategoryDao.class);
            var result = mapper.dropTable();
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Category> selectAll() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(CategoryDao.class);
            return mapper.selectAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category selectById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(CategoryDao.class);
            return mapper.selectById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(CategoryDao.class);
            var result = mapper.deleteById(id);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Category book) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(CategoryDao.class);
            var result = mapper.update(book);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insert(Category book) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(CategoryDao.class);
            var result = mapper.insert(book);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
