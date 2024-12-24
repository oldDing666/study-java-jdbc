package com.afuya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        String subMoneySql = "update t_bank set money = money - ? where id = ?";
        String addMoneySql = "update t_bank set money = money + ? where id = ?";

        PreparedStatement subStatement = connection.prepareStatement(subMoneySql);
        PreparedStatement addMoney = connection.prepareStatement(addMoneySql);

        subStatement.setInt(1, 100);
        subStatement.setInt(2, 1);
        addMoney.setInt(1, 100);
        addMoney.setInt(2, 2);

        connection.setAutoCommit(false);
        try {
            subStatement.execute();
            addMoney.execute();
            connection.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            connection.rollback();
            System.out.println("转账失败");
        }finally {
            subStatement.close();
            addMoney.close();
            connection.close();
        }

    }
}
