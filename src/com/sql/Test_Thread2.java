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
		System.out.println("�ɹ�����");
	}
	

}

class Thread1 extends Thread{
	public void run() {
		try {
		Connection conn = MysqlUtils.createConn();//��ȡ����
		System.out.println("1�������ݿ�ɹ�!!!");

		// ����SQL�����������ڻ���֪�����ã�����
	    //String sql = "INSERT INTO table1(id,user,password,age) VALUES (?,?,?,?)";
		 String sql = "Update merchandise_tbl set price=1 where id=4";
		 // Ԥ����sql���
		 PreparedStatement presta = conn.prepareStatement(sql);
		// ִ��SQL��䣬ʵ������
		 presta.execute();
		 System.out.println("1�ɹ�����");
	}catch (SQLException e) {
        e.printStackTrace();
    }
}
}

class Thread2 extends Thread{
	public void run() {
		try {
			Connection conn = MysqlUtils.createConn();//��ȡ����
			System.out.println("2�������ݿ�ɹ�!!!");

			// ����SQL�����������ڻ���֪�����ã�����
		    //String sql = "INSERT INTO table1(id,user,password,age) VALUES (?,?,?,?)";
			 String sql = "Update merchandise_tbl set price=5 where id=4";
			 // Ԥ����sql���
			 PreparedStatement presta = conn.prepareStatement(sql);
			// ִ��SQL��䣬ʵ������
			 presta.execute();
			 System.out.println("2�ɹ�����");
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}