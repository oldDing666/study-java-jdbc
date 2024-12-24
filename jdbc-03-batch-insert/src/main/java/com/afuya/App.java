package com.afuya;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "afuya" + i);
            preparedStatement.setDouble(2, 10000 + i);
            preparedStatement.setInt(3, 18 + i);
            preparedStatement.addBatch();
        }
        int[] executed = preparedStatement.executeBatch();
        System.out.println("批量插入行数："+executed.length);

        preparedStatement.close();
        connection.close();
    }
}
