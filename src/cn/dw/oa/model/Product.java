package cn.dw.oa.model;

import java.util.Date;

// 实体类，模型类，POJO 对应的是数据的某张表
// 查询结果集中某一条记录对应是Java中的一个对象
public class Product {

	private Integer id;  // 可以存储null
	private String name;
	private Double price;
	private String remark;
	private Date date;
	private Category category;  // cat_id 依赖就是另外一张表的主键,而知道主键就等于知道对应记录
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", remark=" + remark + ", date=" + date
				+ ", category=" + category + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
