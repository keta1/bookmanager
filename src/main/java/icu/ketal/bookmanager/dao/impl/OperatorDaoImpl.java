package icu.ketal.bookmanager.dao.impl;

import icu.ketal.bookmanager.dao.OperatorDao;
import icu.ketal.bookmanager.db.DatabaseManager;
import icu.ketal.bookmanager.entry.Operator;

import java.io.IOException;
import java.util.List;

public class OperatorDaoImpl implements OperatorDao {
    @Override
    public int createTable() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
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
            var mapper = session.getMapper(OperatorDao.class);
            var result = mapper.dropTable();
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Operator> selectAll() {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
            return mapper.selectAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Operator selectById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
            return mapper.selectById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteById(int id) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
            var result = mapper.deleteById(id);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Operator operator) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
            var result = mapper.update(operator);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insert(Operator operator) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
            var result = mapper.insert(operator);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int replace(Operator operator) {
        try (var session = DatabaseManager.getSqlSession()) {
            var mapper = session.getMapper(OperatorDao.class);
            var result = mapper.replace(operator);
            session.commit();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
