package com.afuya.study;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        findLines();
//        findOneLine();
//        insert();
//        update();
//        delete();
    }

    private static void delete() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "delete from t_emp where emp_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 6);
        int result = preparedStatement.executeUpdate();
        if (result > 0) {
            System.out
                    .println("删除成功");
        } else {
            System.out.println("删除失败");
        }
        preparedStatement.close();
        connection.close();
    }

    private static void update() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "update t_emp set emp_salary = ? where emp_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDouble(1, 999.99);
        preparedStatement.setInt(2, 6);
        int result = preparedStatement.executeUpdate();
        if (result > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
        preparedStatement.close();
        connection.close();
    }

    private static void insert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        // 创建句柄
        String sql = "insert into t_emp (emp_name,emp_salary,emp_age) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "张三");
        preparedStatement.setDouble(2, 1000.0);
        preparedStatement.setDouble(3, 28);
        int result = preparedStatement.executeUpdate();
        if (result > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        // 关闭资源
        preparedStatement.close();
        connection.close();
    }

    private static void findOneLine() throws ClassNotFoundException, SQLException {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        // 创建句柄
        String sql = "select * from t_emp where emp_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
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

    private static void findLines() throws ClassNotFoundException, SQLException {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取连接
        String url = "jdbc:mysql://localhost:3306/db_test";
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
