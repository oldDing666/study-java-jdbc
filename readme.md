# 建表语句

```sql
create table t_emp
(
    emp_id     int auto_increment comment '员工编号' primary key,
    emp_name   varchar(100)  not null comment '员工姓名',
    emp_salary double(10, 5) not null comment '员工薪资',
    emp_age    int           not null comment '员工年龄'
);

insert into t_emp (emp_name,emp_salary,emp_age)
values  ('andy', 777.77, 32),
        ('大风哥', 666.66, 41),
        ('康师傅',111, 23),
        ('Gavin',123, 26),
        ('小鱼儿', 123, 28);
```

# 1 hello world

# 2 主键回显

# 3 批量插入

# 4 事务控制

# 5 连接池

# 6 最佳实践