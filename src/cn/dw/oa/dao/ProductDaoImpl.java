package cn.dw.oa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cn.dw.oa.model.Product;

// 完成基本product表的相关操作
public class ProductDaoImpl implements ProductDao {
	// ProductDaoImpl 依赖 jdbcTemplate,如果要在配置文件注入进来则必须要有set方法
	private JdbcTemplate jdbcTemplate;

	// 有setJdbcTemplate方法xml中才可以写: property
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.dao.ProductDao#queryByName(java.lang.String)
	 */
	@Override
	public List<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
//		// return super.query(sql, "%" + keyword + "%");
//		return jdbcTemplate.query(sql, (rs, num) -> {
//			Product product = new Product();
//			product.setId(rs.getInt("id"));
//			product.setName(rs.getString("name"));
//			product.setPrice(rs.getDouble("price"));
//			return product;
//		}, "%" + keyword + "%");
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), "%" + keyword + "%");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.dao.ProductDao#getById(int)
	 */
	@Override
	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			return product;
		}, id);
	}

	// 所有的字段封装到Product中
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.dao.ProductDao#save(cn.dw.oa.model.Product)
	 */
	@Override
	public int save(Product product) {
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.dao.ProductDao#update(cn.dw.oa.model.Product)
	 */
	@Override
	public int update(Product product) {
		String sql = "update product set name=?,price=?,remark=? where id = ?";
		return jdbcTemplate.update(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dw.oa.dao.ProductDao#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from product where id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
