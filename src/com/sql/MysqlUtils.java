package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlUtils {
	private static  final String URL="jdbc:mysql://localhost:3306/heting?useSSL=False";
    static {
            try {
                //从8.X开始 com.mysql.jdbc.Driver 驱动过时了
                //取而代之的是com.mysql.cj.jdbc.Driver
                //这里使用的是mysql-connector-java.5.x.jar
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
    public static Connection createConn() throws SQLException {
            return DriverManager.getConnection(URL,"root","960505");
    }
}
