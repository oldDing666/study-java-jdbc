package com.afuya.dao.impl;

import com.afuya.dao.BaseDao;
import com.afuya.dao.EmployeeDao;
import com.afuya.entity.Employee;

import java.util.List;


/**
 * @author: afuya
 * @program: study-java-jdbc
 * @date: 2024/12/24 23:16
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
    @Override
    public List<Employee> selectAll() {
        try {
            String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge FROM t_emp";
            return query(Employee.class, sql, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee selectByEmpId(Integer empId) {
        try {
            String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge FROM t_emp where emp_id = ?";
            return queryBean(Employee.class, sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Employee employee) {
        try {
            String sql = "INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES (?,?,?)";
            return update(sql, employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee employee) {
        try {
            String sql = "UPDATE t_emp SET emp_salary = ? WHERE emp_id = ?";
            return update(sql, employee.getEmpSalary(), employee.getEmpId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Integer empId) {
        try {
            String sql = "delete from t_emp where emp_id = ?";
            return update(sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
