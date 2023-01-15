package com.imooc.jdbc.sample;

import com.imooc.jdbc.common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransectionSample {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "insert into employee (eno,ename,salary,dname) values(?,?,?,?)";
            conn.setAutoCommit(false);

            for(int i = 1000; i<2000; i++){
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,i);
                pstmt.setString(2,"员工"+i);
                pstmt.setFloat(3,4000);
                pstmt.setString(4,"市场部");
                pstmt.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(null, pstmt, conn);
        }
    }
}
