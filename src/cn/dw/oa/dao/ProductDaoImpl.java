package cn.dw.oa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dw.oa.model.Product;
import cn.dw.oa.utils.JdbcUtils;

// 完成基本product表的相关操作
public class ProductDaoImpl extends BaseDaoImpl<Product> {

	public static void main(String[] args) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product = new Product();
		product.setName("Iphone 10");
		product.setPrice(6666.14);
		product.setRemark("我是备注");
		product.setId(4);
		// daoImpl.update(product);
		// daoImpl.delete(4);
		for (Product temp : daoImpl.queryByName("")) {
			System.out.println(temp);
		}
		System.out.println(daoImpl.getById(1));
	}

	public List<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
		return super.query(sql, "%" + keyword + "%");
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		List<Product> proList = super.query(sql, id);
		return proList.size() == 0 ? null : proList.get(0);
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

	@Override
	protected Product getRow(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setDate(rs.getDate("date"));
		product.setId(rs.getInt("id"));
		product.setRemark(rs.getString("remark"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getDouble("price"));
		return product;
	}

}
