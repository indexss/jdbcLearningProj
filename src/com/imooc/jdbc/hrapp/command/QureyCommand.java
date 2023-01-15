package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QureyCommand implements Command{

    @Override
    public void excute() {
        System.out.print("请输入部门名称：");
        Scanner sc = new Scanner(System.in);
        String pdname = sc.next();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true",
                    "root","1941-06-06"
            );
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from employee where dname='"+pdname+"'");
            while(rs.next()){
                int eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                Date hiredate = rs.getDate("hiredate");
                System.out.println(eno + "-" + ename + "-" + salary + "-" + dname + "-" + hiredate);
            }
            System.out.println("--------------------------------");
            System.out.println(rs);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(conn != null && conn.isClosed() == false){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
