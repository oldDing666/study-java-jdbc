package com.afuya;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/db-test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        // 创建句柄
        String sql = "select * from t_emp";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 解析结果
        while (resultSet.next()) {
            System.out.println("emp_id: " + resultSet.getInt("emp_id")
                    + ", emp_name: " + resultSet.getString("emp_name")
                    + ", emp_salary: " + resultSet.getDouble("emp_salary")
                    + ", emp_age: " + resultSet.getString("emp_age"));
        }

        // 关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
