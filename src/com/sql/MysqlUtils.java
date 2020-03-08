package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlUtils {
	private static  final String URL="jdbc:mysql://localhost:3306/heting?useSSL=False";
    static {
            try {
                //��8.X��ʼ com.mysql.jdbc.Driver ������ʱ��
                //ȡ����֮����com.mysql.cj.jdbc.Driver
                //����ʹ�õ���mysql-connector-java.5.x.jar
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
    public static Connection createConn() throws SQLException {
            return DriverManager.getConnection(URL,"root","960505");
    }
}
