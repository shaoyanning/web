package cn.dw.oa.dao;

import java.nio.channels.ShutdownChannelGroupException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.management.Query;

import cn.dw.oa.utils.JdbcUtils;

// 抽取访问数据库共性代码,通过继承的方法让所有子类共享
public class BaseDaoImpl {
	
	// insert update delete都会调用此方法
	protected int update(String sql, Object... param) {
		// 1: 创建连接数据库的对象
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		// 2: prepareStatement(操作数据库的对象)
		try {
			conn = jdbcUtils.getConnection();
			// 此处称为预编译SQL语句(目前sql并没有真正执行)
			pre = conn.prepareStatement(sql);
			// 参数的下标从1开始
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			// 执行真正的sql语句, update、delete、insert都统一调用: executeUpdate()
			return pre.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			jdbcUtils.close(conn, pre);
		}

	}

}
