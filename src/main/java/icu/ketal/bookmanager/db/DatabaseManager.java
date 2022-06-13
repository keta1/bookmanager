package icu.ketal.bookmanager.db;

import icu.ketal.bookmanager.dao.impl.*;
import icu.ketal.bookmanager.util.ClazzUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

public class DatabaseManager {
    private static final String driver = "org.sqlite.JDBC";
    private static final String url = "jdbc:sqlite:bookmanager.db";
    private static final String id = "development";
    private static final String[] typeAliases = {"icu.ketal.bookmanager.entry"};
    private static final String[] mappers = {"icu.ketal.bookmanager.dao"};
    private static SqlSessionFactory factory = null;

    static {
        try {
            var configure = new Configuration() {{
                setLogImpl(StdOutImpl.class);
                var environmentBuilder = new Environment.Builder(id)
                        .transactionFactory(new JdbcTransactionFactory())
                        .dataSource(new PooledDataSource() {{
                            setDriver(driver);
                            setUrl(url);
                        }});
                setEnvironment(environmentBuilder.build());

                // set type aliases
                Arrays.stream(typeAliases).forEach(it -> getTypeAliasRegistry().registerAliases(it));
                // add mappers
                Arrays.stream(mappers).forEach(it -> ClazzUtils.getClazzName(it).forEach(i -> {
                    try {
                        this.addMapper(Class.forName(i));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }));
            }};
            factory = new SqlSessionFactoryBuilder().build(configure);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() throws IOException {
        return factory.openSession();
    }

    public static void initTables() {
        // info : Because of the outer code constraint, the table is initialized manually here.
        // todo : Automatically initialize tables in a more logical way.
        var tables = Set.of(
                OperatorDaoImpl.class,
                ReaderDaoImpl.class,
                CategoryDaoImpl.class,
                BookDaoImpl.class,
                BorrowDaoImpl.class
        );
        tables.forEach(it -> {
            try {
                var dao = it.getConstructor().newInstance();
                it.getDeclaredMethod("createTable").invoke(dao);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
