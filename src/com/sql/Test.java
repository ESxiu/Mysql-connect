package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class Test {
	private static final int THREADS = 100;//模拟100用户并发访问数据库
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS);//倒计时计数器
 
    public static void main(String[] args) {
        for (int i = 0; i < THREADS; i++) {
            new Thread(() -> {//创建100个线程模拟用户
                countDownLatch.countDown();//计数器减一
                try {
                    countDownLatch.await();//当计数器没有减为0时，线程一直等待
                    Connection conn = null;
                    try {
                        //执行到这里，100个线程同时访问数据库
                        conn = MysqlUtils.createConn();//获取连接
                        //执行操作
                        PreparedStatement preparedStatement = conn.prepareStatement("select COUNT(*) c from merchandise_tbl");
                        ResultSet resultSet = preparedStatement.executeQuery();
                        System.out.println(resultSet.next());
                        System.out.println(resultSet.getObject("c"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            conn.close();//关闭连接
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
