package cn.dw.oa.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.dw.oa.model.Product;
import cn.dw.oa.utils.JdbcUtils;

// 完成基本product表的相关操作
public class ProductDaoImpl extends BaseDaoImpl {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product = new Product();
		product.setName("Iphone 10");
		product.setPrice(6666.14);
		product.setRemark("我是备注");
		product.setId(4);
		// daoImpl.update(product);
//		daoImpl.delete(4);
		for(Product temp:daoImpl.queryByName("")) {
			System.out.println(temp);
		}
	}
	
	public List<Product> queryByName(String keyword) {
		List<Product> proList = new ArrayList<Product>();
		String sql="select * from product where name like ?";
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
			pre.setString(1, "%" + keyword + "%");
			// 执行真正的sql语句,executeQuery会返回一个ResultSet(查询结果集)
			rs = pre.executeQuery();
			// 从rs中获取数据(如果有数据的话)
			while(rs.next()) {  // 最初,光标被置于第一行之前.next方法将光标移动到下一行
				// true则说明当前行有记录
				Product product = new Product();
				product.setDate(rs.getDate("date"));
				product.setId(rs.getInt("id"));
				product.setRemark(rs.getString("remark"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				proList.add(product);
			}
			return proList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			jdbcUtils.close(conn, pre);
		}
	}
	
	public Product getById(int id) {
		Product product = null;
		String sql="select * from product where id = ?";
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
			pre.setInt(1, id);
			// 执行真正的sql语句,executeQuery会返回一个ResultSet(查询结果集)
			rs = pre.executeQuery();
			// 从rs中获取数据(如果有数据的话)
			if(rs.next()) {  // 最初,光标被置于第一行之前.next方法将光标移动到下一行
				// true则说明当前行有记录
				product = new Product();
				product.setDate(rs.getDate("date"));
				product.setId(rs.getInt("id"));
				product.setRemark(rs.getString("remark"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
			}
			return product;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			jdbcUtils.close(conn, pre);
		}
	}

	// 所有的字段封装到Product中
	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return super.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}

	public int update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		return super.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return super.update(sql, id);
	}

}
