package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StandardJDBCSample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc", "root", "1941-06-06");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employee");
            while (rs.next()) {
                int eno = rs.getInt(1);
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(eno + "-" + ename + "-" + salary + "-" + dname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed() == false) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
