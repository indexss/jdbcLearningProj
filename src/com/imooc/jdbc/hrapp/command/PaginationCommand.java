package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.common.DbUtils;
import com.imooc.jdbc.hrapp.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaginationCommand implements Command{
    @Override
    public void excute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入页号: ");
        Integer page = sc.nextInt();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList<Employee>();

        try {
            conn = DbUtils.getConnection();
            String sql = "select * from employee limit ?,10";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,(page-1)*10);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Integer eno = rs.getInt("eno");
                String ename = rs.getString("ename");
                Float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                list.add(emp);
            }
            System.out.println(list.size());
            for(Employee emp : list){
                System.out.println(emp);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(rs,pstmt,conn);
        }

    }
}
