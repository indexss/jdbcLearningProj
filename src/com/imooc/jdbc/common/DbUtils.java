package com.imooc.jdbc.common;

import java.sql.*;

public class DbUtils {

    /**
     *
     * @return 新的Connection连接
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true",
                "root", "1941-06-06"
        );
        return conn;
    }

    /**
     * 关闭连接，释放资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void closeConnection(ResultSet rs, Statement stmt, Connection conn){
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
