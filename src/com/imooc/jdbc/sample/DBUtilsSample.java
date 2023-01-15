package com.imooc.jdbc.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.entity.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBUtilsSample {
    private static void query() {
        Properties properties = new Properties();
        String propertyFile = DBUtilsSample.class.getResource("/druid-config.properties").getPath();
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            QueryRunner qr = new QueryRunner(dataSource);
            List<Employee> list = qr.query("select * from employee limit ?,2",
                    new BeanListHandler<>(Employee.class),
                    new Object[]{1});
            //不用再去关闭，因为DBUtils自动帮我们做了这件事
            for (Employee emp : list) {
                System.out.println(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() throws SQLException {
        Properties properties = new Properties();
        String propertyFile = DBUtilsSample.class.getResource("/druid-config.properties").getPath();
        Connection conn = null;
        try {
            propertyFile = URLDecoder.decode(propertyFile, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "update employee set salary = salary+1000 where eno=?";
            String sql2 = "update employee set salary = salary-500 where eno=?";
            QueryRunner qr = new QueryRunner();
            qr.update(conn, sql1, new Object[]{3308});
            qr.update(conn, sql2, new Object[]{8208});
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(conn != null && !conn.isClosed()){
                conn.rollback();
            }
        } finally {
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        query();
        update();
    }
}
