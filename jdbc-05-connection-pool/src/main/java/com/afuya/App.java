package com.afuya;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        //1.创建Properties集合，用于存储外部配置文件的key和value值。
        Properties properties = new Properties();

        //2.读取外部配置文件，获取输入流，加载到Properties集合里。
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("db.properties");
        properties.load(inputStream);

        //3.基于Properties集合构建DruidDataSource连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //4.通过连接池获取连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //5.开发CRUD
        String sql = "select * from t_emp";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("name: " + resultSet.getString(2));
        }

        //6.回收连接
        connection.close();
    }
}
