package cn.dw.oa.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.dw.oa.model.Product;
import cn.dw.oa.utils.JdbcUtils;

// 完成基本product表的相关操作
public class ProductDaoImpl {
	
	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product = new Product();
		product.setName("Iphone X");
		product.setPrice(3.14);
		product.setRemark("我是备注");
		daoImpl.save(product);
	}
	
	// 所有的字段封装到Product中
    public int save(Product product) {
    	String sql = "insert into product (name,price,remark) values (?,?,?)";
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
			pre.setString(1, product.getName());
			pre.setDouble(2, product.getPrice());
			pre.setString(3, product.getRemark());
			// 执行真正的sql语句, update、delete、insert都统一调用: executeUpdate()
			return pre.executeUpdate(); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			jdbcUtils.close(conn, pre);
		}
    }	

}
