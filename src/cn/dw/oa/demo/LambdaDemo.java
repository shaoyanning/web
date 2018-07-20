package cn.dw.oa.demo;

//以下是lambda表达式的重要特征:
//
//    可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
//    可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
//    可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
//    可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

class R implements Runnable {

	@Override
	public void run() {
		System.out.println("线程运行的业务逻辑代码1......");
	}

}

public class LambdaDemo {
	public static void main(String[] args) {
		R r = new R();
		// 方案1: 创建一个线程对象,并且启动
		new Thread(r).start();
		// 方案2：采用匿名对象
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程运行的业务逻辑代码2......");
			}
		}).start();

		new Thread(() ->{
			System.out.println("线程运行的业务逻辑代码3......");
			System.out.println("线程运行的业务逻辑代码3......");
		} ).start();

	}
}
