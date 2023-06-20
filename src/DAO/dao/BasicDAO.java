package DAO.dao;
import DAO.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<T>{
    private final QueryRunner qr = new QueryRunner();

    //增删改
    public int update(String sql, Object... parameters){
        Connection connection = null;
        try{
            connection = JDBCUtilsByDruid.getConnection();
            return qr.update(connection, sql, parameters);  //返回更改行数
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            try {
                JDBCUtilsByDruid.close(null, null, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<T> multiQuery(String sql ,Class<T> clazz, Object... parameters){
        Connection connection = null;
        try{
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql , new BeanListHandler<>(clazz), parameters);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtilsByDruid.close(null, null, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //查询单行
    public T singleQuery(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;
        try{
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql ,new BeanHandler<T>(clazz), parameters);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtilsByDruid.close(null, null, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //返回单值
    public Object scalarQuery(String sql, Object...parameters){
        Connection connection = null;
        try{
            connection = JDBCUtilsByDruid.getConnection();
            return qr.query(connection, sql ,new ScalarHandler<>(), parameters);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtilsByDruid.close(null, null, connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
