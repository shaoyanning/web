package cn.dw.oa.dao;

import java.nio.channels.ShutdownChannelGroupException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import cn.dw.oa.utils.JdbcUtils;

// 抽取访问数据库共性代码,通过继承的方法让所有子类共享
public class BaseDaoImpl {
	
	public void show(int... num) {
		int sum = 0;
		for(int i=0;i<num.length;i++) {
			sum +=num[i];
		}
		System.out.println(sum);
	}

	public static void main(String[] args) {
		int num = 0;
//		System.out.println(++num); // 1
//		System.out.println(num++); // 1
//		System.out.println(num); // 2
//		System.out.println(num + 1); // 3
//		System.out.println(num); // 2
		
		BaseDaoImpl daoImpl = new BaseDaoImpl();
		daoImpl.show();
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
			// pre.setString(1, product.getName());
			// pre.setDouble(2, product.getPrice());
			// pre.setString(3, product.getRemark());
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
