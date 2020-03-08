package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class Test {
	private static final int THREADS = 100;//ģ��100�û������������ݿ�
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS);//����ʱ������
 
    public static void main(String[] args) {
        for (int i = 0; i < THREADS; i++) {
            new Thread(() -> {//����100���߳�ģ���û�
                countDownLatch.countDown();//��������һ
                try {
                    countDownLatch.await();//��������û�м�Ϊ0ʱ���߳�һֱ�ȴ�
                    Connection conn = null;
                    try {
                        //ִ�е����100���߳�ͬʱ�������ݿ�
                        conn = MysqlUtils.createConn();//��ȡ����
                        //ִ�в���
                        PreparedStatement preparedStatement = conn.prepareStatement("select COUNT(*) c from merchandise_tbl");
                        ResultSet resultSet = preparedStatement.executeQuery();
                        System.out.println(resultSet.next());
                        System.out.println(resultSet.getObject("c"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            conn.close();//�ر�����
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
   }
}
