package com.caipiao.servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.caipiao.pojo.CurrentData;

public class ConnectionFactory {

    private static ConnectionFactory factory;
    
    
    private ConnectionFactory() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (factory == null) {
            factory = new ConnectionFactory();
        }
        return factory;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://43.226.165.74:3306/caipiao?useSSL=true","root","os_mysql");
//        return DriverManager.getConnection("jdbc:mysql://116.62.165.78:3306/caipiao?useSSL=true","root","whui123333");
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/caipiao?useSSL=true","root","root");
    }

    private void close(PreparedStatement psmt, Connection conn) {
        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
            }
            psmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
            conn = null;
        }
    }

    private void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
            conn = null;
        }
    }
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public int insert(CurrentData currentData) {
        String sql = "insert into current_data (expect, open_code, open_time) values (?, ?, ?)";
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, currentData.getExpect());
            psmt.setString(2, currentData.getOpenCode());
            psmt.setString(3, currentData.getOpenTime());
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(psmt, conn);
        }
        return 0;
    }
    
    
    public List<CurrentData> selectLast15() {
        List<CurrentData> current = new ArrayList<>();
        String sql = "select expect, open_code, open_time from current_data order by expect desc limit 15";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	CurrentData currentData = new CurrentData();
            	currentData.setExpect(rs.getString(1));
            	currentData.setOpenCode(rs.getString(2));
            	currentData.setOpenTime(rs.getString(3));
            	current.add(currentData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return current;
    }
    
    public CurrentData selectLastOne() {
    	CurrentData currentData = new CurrentData();
    	String sql = "select expect, open_code, open_time from current_data order by expect desc limit 1";
    	Connection conn = null;
    	Statement stmt = null;
    	ResultSet rs = null;
    	try {
    		conn = getConnection();
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery(sql);
    		while (rs.next()) {
    			currentData.setExpect(rs.getString(1));
    			currentData.setOpenCode(rs.getString(2));
    			currentData.setOpenTime(rs.getString(3));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		close(rs, stmt, conn);
    	}
    	return currentData;
    }

    public int[] insertAppend(List<CurrentData> current) {
        String sql = "insert into current_data (expect, open_code, open_time) values (?, ?, ?)";
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);
            for (CurrentData currentData : current) {
                psmt.setString(1, currentData.getExpect());
                psmt.setString(2, currentData.getOpenCode());
                psmt.setString(3, currentData.getOpenTime());
                psmt.addBatch();
            }
            return psmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(psmt, conn);
        }
        return null;
    }
    
}
