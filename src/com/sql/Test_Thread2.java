package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test_Thread2 {
	public static void main(String[] args) {
		Thread1 T1 = new Thread1();
		Thread2 T2 = new Thread2();
		T1.start();
		T2.start();
		System.out.println("成功！！");
	}
	

}

class Thread1 extends Thread{
	public void run() {
		try {
		Connection conn = MysqlUtils.createConn();//获取连接
		System.out.println("1连接数据库成功!!!");

		// 设置SQL规则，数据由于还不知道先用？代替
	    //String sql = "INSERT INTO table1(id,user,password,age) VALUES (?,?,?,?)";
		 String sql = "Update merchandise_tbl set price=1 where id=4";
		 // 预处理sql语句
		 PreparedStatement presta = conn.prepareStatement(sql);
		// 执行SQL语句，实现数据
		 presta.execute();
		 System.out.println("1成功！！");
	}catch (SQLException e) {
        e.printStackTrace();
    }
}
}

class Thread2 extends Thread{
	public void run() {
		try {
			Connection conn = MysqlUtils.createConn();//获取连接
			System.out.println("2连接数据库成功!!!");

			// 设置SQL规则，数据由于还不知道先用？代替
		    //String sql = "INSERT INTO table1(id,user,password,age) VALUES (?,?,?,?)";
			 String sql = "Update merchandise_tbl set price=5 where id=4";
			 // 预处理sql语句
			 PreparedStatement presta = conn.prepareStatement(sql);
			// 执行SQL语句，实现数据
			 presta.execute();
			 System.out.println("2成功！！");
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}