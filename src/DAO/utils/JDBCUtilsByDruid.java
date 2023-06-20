package DAO.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {
    private static DataSource ds;
    //在静态代码块中 完成 ds的初始化
       static {
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("src\\Asset\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException{
           return ds.getConnection();
    }
    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws  SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}

