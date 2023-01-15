package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCommand implements Command{
    @Override
    public void excute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入员工编号: ");
        int eno = sc.nextInt();
        System.out.print("请输入员工姓名: ");
        String ename = sc.next();
        System.out.print("请输入员工薪资: ");
        float salary = sc.nextFloat();
        System.out.print("请输入隶属部门: ");
        String dname = sc.next();

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into employee (eno,ename,salary,dname) values (?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,eno);
            pstmt.setString(2,ename);
            pstmt.setFloat(3, salary);
            pstmt.setString(4,dname);
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt + "条数据收到影响");
            System.out.println(ename + "员工入职办理成功！");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
          DbUtils.closeConnection(null, pstmt, conn);
        }

    }
}
