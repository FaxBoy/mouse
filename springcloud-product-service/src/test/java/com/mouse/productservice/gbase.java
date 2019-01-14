package com.mouse.productservice;

import java.sql.*;

/**
 * @author shil <sl166199@163.com>
 * @version v1.0
 * @ClassName gbase
 * @Description TODO
 * @date 2018/9/14 11:02
 */
public class gbase {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.gbasedbt.jdbc.IfxDriver").newInstance();
        String url = "jdbc:gbasedbt-sqli://172.18.0.73:9088/test:GBASEDBTSERVER=gbaseserver;user=gbasedbt;password=password!@#1;DB_LOCALE=zh_cn.utf8;CLIENT_LOCALE=zh_cn.utf8;NEWCODESET=UTF8,zh_cn.UTF8,57372;";
        Connection conn = DriverManager.getConnection(url);
        int i = 0;
        Statement stmt = conn.createStatement();
//        stmt.executeUpdate("create table customer ( num int, name char(16), address char(150))");
        stmt.executeUpdate("insert into customer values(123032,  '王亮',  '北京朝阳区朝外大街232号联盟大厦7层716室')");
        stmt.executeUpdate("insert into customer values(123033,  '郑雨', '上海市奉贤区青春镇金海路552号')");
        ResultSet rs = stmt.executeQuery("select * from customer");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println("");
        }
        PreparedStatement pstmt1 = conn.prepareStatement("insert into customer values(?, ?, ?)");
        pstmt1.setInt(1, 123034);
        pstmt1.setString(2, "谢立宇");
        pstmt1.setString(3, "北京市海淀区中关村大街983号天豪大厦239室");
        pstmt1.executeUpdate();
        stmt.close();
        conn.close();

    }

}
