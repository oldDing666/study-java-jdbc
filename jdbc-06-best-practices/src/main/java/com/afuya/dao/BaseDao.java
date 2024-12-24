package com.afuya.dao;

import com.afuya.util.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public abstract class BaseDao {
    /*
    通用的增、删、改的方法
    String sql：sql
    Object... args：给sql中的?设置的值列表，可以是0~n
     */
    protected int update(String sql, Object... args) throws SQLException {
        // 创建PreparedStatement对象，对sql预编译
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        //设置?占位符的值
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                //?的编号从1开始，不是从0开始，数组的下标是从0开始
                ps.setObject(i + 1, args[i]);
            }
        }

        //执行sql
        int len = ps.executeUpdate();
        ps.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //connection.getAutoCommit()为false，不要在这里回收connection，由开启事务的地方回收
        //connection.getAutoCommit()为true，正常回收连接
        //没有开启事务的话，直接回收关闭即可!
        if (connection.getAutoCommit()) {
            //回收
            JdbcUtil.release();
        }
        return len;
    }

    /*
    通用的查询多个Javabean对象的方法，例如：多个员工对象，多个部门对象等
    这里的clazz接收的是T类型的Class对象，
    如果查询员工信息，clazz代表Employee.class，
    如果查询部门信息，clazz代表Department.class，
    返回List<T> list
     */
    protected <T> ArrayList<T> query(Class<T> clazz, String sql, Object... args) throws Exception {
        // 创建PreparedStatement对象，对sql预编译
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        //设置?的值
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                // ?的编号从1开始，不是从0开始，数组的下标是从0开始
                ps.setObject(i + 1, args[i]);
            }
        }

        ArrayList<T> list = new ArrayList<>();
        ResultSet res = ps.executeQuery();

        /*
        获取结果集的元数据对象。
        元数据对象中有该结果集一共有几列、列名称是什么等信息
         */
        ResultSetMetaData metaData = res.getMetaData();
        // 获取结果集列数
        int columnCount = metaData.getColumnCount();

        //遍历结果集ResultSet，把查询结果中的一条一条记录，变成一个一个T 对象，放到list中。
        while (res.next()) {
            //循环一次代表有一行，代表有一个T对象
            //要求这个类型必须有公共的无参构造
            T t = clazz.newInstance();

            //把这条记录的每一个单元格的值取出来，设置到t对象对应的属性中。
            for (int i = 1; i <= columnCount; i++) {
                //for循环一次，代表取某一行的1个单元格的值
                Object value = res.getObject(i);

                //这个值应该是t对象的某个属性值
                //获取该属性对应的Field对象
                //String columnName = metaData.getColumnName(i);//获取第i列的字段名
                //这里再取别名可能没办法对应上
                //获取第i列的字段名或字段的别名
                String columnName = metaData.getColumnLabel(i);
                Field field = clazz.getDeclaredField(columnName);
                //这么做可以操作private的属性
                field.setAccessible(true);

                field.set(t, value);
            }

            list.add(t);
        }

        res.close();
        ps.close();
        //这里检查下是否开启事务，开启不关闭连接，业务方法关闭!
        //没有开启事务的话，直接回收关闭即可!
        if (connection.getAutoCommit()) {
            //回收
            JdbcUtil.release();
        }
        return list;
    }

    protected <T> T queryBean(Class<T> clazz, String sql, Object... args) throws Exception {
        ArrayList<T> list = query(clazz, sql, args);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}