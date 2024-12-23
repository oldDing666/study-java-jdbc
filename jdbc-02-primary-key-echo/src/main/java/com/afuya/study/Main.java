package com.afuya.study;

import com.afuya.study.entity.Employee;

import java.sql.*;

/**
 * @author: afuya
 * @program: study-java-jdbc
 * @date: 2024/12/23 22:25
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "insert into t_emp (emp_name,emp_salary,emp_age) values (?, ?, ?)";
        // 创建preparedStatement对象，传入需要主键回显参数Statement.RETURN_GENERATED_KEYS
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        Employee employee = Employee.builder()
                .empName("老铁")
                .empAge(28)
                .empSalary(8888.88)
                .build();
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setDouble(2, employee.getEmpSalary());
        preparedStatement.setDouble(3, employee.getEmpAge());
        int update = preparedStatement.executeUpdate();
        if (update > 0) {
            System.out.println("插入成功");
            // 获取生成的主键列值，返回的是resultSet，在结果集中获取主键列值
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                employee.setEmpId(id);
                System.out.println(employee);
            }
            resultSet.close();
        } else {
            System.out.println("插入失败");
        }

        preparedStatement.close();
        connection.close();
    }
}
