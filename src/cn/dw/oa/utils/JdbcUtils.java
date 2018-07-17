package cn.dw.oa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 完成数据库的连接操作,负责创建和销毁资源
public class JdbcUtils {

	public static void main(String[] args) {
		JdbcUtils jdbcUtils = new JdbcUtils();
		System.out.println(jdbcUtils.getConnection());
		System.out.println(jdbcUtils.getConnection());
		System.out.println(jdbcUtils.getConnection());
	}

	// 项目开发中,XML properties 配置文件，驱动一般都加载一次
	static { // 静态块,在当前JdbcUtils.class文件加载到JVM中会执行且执行一次
		System.out.println("------JdbcUtils static---------");
		// 1: 加载jdbc数据库驱动
		try {
			// 根据类全名,把驱动类加载的内存中(驱动的入口)
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 编写一个方法,每次调用此方法获取一个Connection对象
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void close(Connection conn, Statement pre) {
		this.close(conn, pre, null);
	}

	// 编写一个方法,关闭Connection对象
	public void close(Connection conn, Statement pre, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (pre != null && !pre.isClosed()) {
					pre.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}

}
