package cn.dw.oa.dao;

import java.nio.channels.ShutdownChannelGroupException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.naming.spi.DirStateFactory.Result;

import cn.dw.oa.model.Product;
import cn.dw.oa.utils.JdbcUtils;

// 抽取访问数据库共性代码,通过继承的方法让所有子类共享
public abstract class BaseDaoImpl<T> {
	// 不同子模块查询需求不同,因此父类没办法抽取.可以定义一个抽取方法子类自己去实现(思考为什么不使用接口)
	protected abstract T getRow(ResultSet rs) throws SQLException; 
	
	// Type: 拥有T的类,必须在类中声明 
	protected List<T> query(String sql, Object... param) {
		List<T> tList = new ArrayList<T>();
		// 1: 创建连接数据库的对象
		JdbcUtils jdbcUtils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		// 2: prepareStatement(操作数据库的对象)
		try {
			conn = jdbcUtils.getConnection();
			// 此处称为预编译SQL语句(目前sql并没有真正执行)
			pre = conn.prepareStatement(sql);
			// 参数的下标从1开始
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i + 1, param[i]);
			}
			// 执行真正的sql语句,executeQuery会返回一个ResultSet(查询结果集)
			rs = pre.executeQuery();
			// 从rs中获取数据(如果有数据的话)
			while (rs.next()) { // 最初,光标被置于第一行之前.next方法将光标移动到下一行
				// this: 永远执行的当前调用的对象
				tList.add(this.getRow(rs));
			}
			return tList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			jdbcUtils.close(conn, pre,rs);
		}

	}

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
