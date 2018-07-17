package cn.dw.oa.demo;

// JVM中 堆、方法区是所有线程共享的区域，而栈是每个线程独享区域
public class Student {

	static {
		System.out.println("-----------------------");
	}

	// 普通的属性每个对象都有一份
	private Integer num;
	// static静态属性,只有一份,而且属于类(存储在类中)
	private static String name;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		// 指向当前调用的对象
		System.out.println("this:" + this);
		this.num = num;
	}

	// 类操作静态方法(静态方法只能操作静态属性)
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Student.name = name;
	}

	// 方法的调用就是入栈的过程
	public static void main(String[] args) {
		int num = 100; // 原始类型,存储的是数据本身
		System.out.println(num);
		// new Student() 才是真正的对象(存储堆中)
		// student是局部变量,在栈中声明,是引用类型存储的是对象的地址
		Student student = new Student(); // 先有类才有对象,Student类先被加载存储到JVM的方法区中
		System.out.println(student);
		Student student2 = student; // 把student变量中的地址赋值student2(此操作没有生成新对象)
		System.out.println(student2);
		student.setNum(200);
		// 静态方法一般由类调用(不建议用对象调用)
		Student.setName("admin");
		// 只要有new,则100%会创建一个新对象
		Student student3 = new Student();
		student3.setNum(300);
		Student.setName("admin3");
		System.out.println(Student.getName() + "," + Student.getName());
	}

}
