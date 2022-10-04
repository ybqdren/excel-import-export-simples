package com.github.ybqdren;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据生成
 * created by ybqdren
 */
public class Data {
    Connection conn ;
    public void init() throws SQLException {
        DriverManager.registerDriver(new Driver());
        String url = "";
        String username = "";
        String password = "";
        Connection conn = DriverManager.getConnection(url, username, password);
    }


    public void generator() {
        try {
            init();

            // 插入3000000条数据
            String baseSql = "insert into demo(arg_1 , arg_2 , arg_3 , arg_4) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(baseSql);
            for(int i = 0 ;i<3000000;i++){
                ps.setObject(1 , i);
                ps.setObject(2 , i);
                ps.setObject(3 , i);
                ps.setObject(4 , i);
                ps.addBatch();
                if(i % 10000 == 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            ps.executeBatch();
            ps.clearBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
