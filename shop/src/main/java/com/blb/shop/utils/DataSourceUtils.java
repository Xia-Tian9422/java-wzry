package com.blb.shop.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSourceUtils {


    //定义一个数据源成员变量
    private  static DruidDataSource dataSource;

    //使用静态代码块创建数据源
    static {

        //从配置文件中读取到四要素
        ResourceBundle bundle = ResourceBundle.getBundle("druid");
        //创建数据源
        dataSource = new DruidDataSource();
        //设置四要素
        dataSource.setDriverClassName(bundle.getString("driverClassName"));
        dataSource.setUrl(bundle.getString("url"));
        dataSource.setUsername(bundle.getString("username"));
        dataSource.setPassword(bundle.getString("password"));

    }

    //定义一个方法 获取数据源
    public  static DataSource getDataSource(){

        return  dataSource;
    }

    //定义一个方法获取连接对象
    public  static Connection getConnection() throws SQLException {

        return  dataSource.getConnection();
    }
}
