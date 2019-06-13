package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.mapper.EmployeeAnnotationMapper;

//import com.atguigu.mybatis.bean.Employee;
//import com.atguigu.mybatis.mapper.EmployeeAnnotationMapper;

public class TestAnnotationHelloWorld {

	private SqlSessionFactory sqlSessionFactory;
	

	

	// 在每次执行 @Test标注的方法前，都会执行此方法
	@Before
	public void init() throws IOException {

		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println(sqlSessionFactory);
	}
	
	@Test
	public void test() {
		SqlSession session = sqlSessionFactory.openSession();
		Object result = session.selectOne("com.atguigu.mybatis.mapper.EmployeeAnnotationMapper.selectEmployeeById", 1);
		System.out.println(result);
		session.close();
	}
	
	//接口式编程
		@Test
		public void testMapper() {
			
			SqlSession session = sqlSessionFactory.openSession();
			
			try {
				
				//创建接口的实现类
				EmployeeAnnotationMapper mapper = session.getMapper(EmployeeAnnotationMapper.class); //com.sun.proxy.$Proxy5
				
				System.out.println(mapper.getClass().getName());
				
				Employee employee = mapper.selectEmployeeById(1);
				
				System.out.println(employee);
			}finally {
				session.close();
			}
			
		}
		
		@Test
		public void testInsert() {

			SqlSession session = sqlSessionFactory.openSession(true);

			try {

				//创建接口的实现类
				EmployeeAnnotationMapper mapper = session.getMapper(EmployeeAnnotationMapper.class);
				
				Employee employee = new Employee(null, "jack", "male", "jack@qq.com");
				
				mapper.insertEmployee(employee);
				
				//session.commit();

			} finally {
				session.close();
			}

		}
		
		@Test
		public void testUpdate() {

			SqlSession session = sqlSessionFactory.openSession(true);

			try {

				//创建接口的实现类
				EmployeeAnnotationMapper mapper = session.getMapper(EmployeeAnnotationMapper.class);
				
				Employee employee = new Employee(6, "jack", "female", "jack@qq.com");
				
				mapper.updateEmployee(employee);

			} finally {
				session.close();
			}

		}
		
		@Test
		public void testDelete() {

			SqlSession session = sqlSessionFactory.openSession(true);

			try {

				//创建接口的实现类
				EmployeeAnnotationMapper mapper = session.getMapper(EmployeeAnnotationMapper.class);
				
				mapper.deleteEmployee(6);

			} finally {
				session.close();
			}

		}
	

	/*
	 * 1. 建工程，导入jar包 mybatis-3.4.1.jar mysql-connector-java-5.1.7-bin.jar
	 * 为了调试方便，引入日志jar包（可选）： log4j log4j-1.2.17.jar 2. 配置框架的配置文件： ①log4J日志输出样式文件
	 * ②mybatis的配置文件
	 * 
	 * 3. Helloworld: a): 创建 SqlSessionFactory ①每个Mybatis应用都需要一个sqlSessionFactory对象
	 * ②此对象通过SqlSessionFactoryBuilder创建
	 * ③SqlSessionFactoryBuilder通过加载指定的配置创建相应的sqlSessionFactory b):准备Mybaits的配置文件;
	 * c): 创建SqlSession，调用其中的方法，执行sql b): 编写sql语句，写在xxx.xml文件中；
	 * 创建一个xxx.xml文件，在mybatis中，这个文件习惯以xxxMapper.xml来命名 d):
	 * 在全局的配置文件中，注册写好的xxxMapper.xml
	 * 
	 * 2. 存在的问题： 查询的结果需要手动转换，不方便； 参数没有严格的要求和检查，容易出错； 原先习惯于MVC分层开发，Dao层习惯使用
	 * XXXDao---->XXXDaoImpl,管理混乱；
	 * 
	 * 3. 引入接口式编程： ①编写接口 ，在mybatis中，习惯将Dao层接口命名为 xxxMapper.java
	 * ②不需要创建实现类，mybatis对自动为所有扫描到的接口创建代理对象实现 SqlSesson.getMapper(T);
	 * ③将接口的方法和方法要执行的sql绑定 注意： 接口全类名和xxxMapper.xml的namespace一致； 接口方法名和sql的id一致；
	 * 
	 * 接口式编程实际还是调用本地方法来完成！
	 * 
	 * 4. 增删改涉及到事务操作，一定要提交事务！ session.commit();
	 * 
	 * 或者可以设置session为自动提交事务： sqlSessionFactory.openSession(boolean autoCommit);
	 * 
	 * 5. sqlSession: 代表和数据库的一次会话，其中包含了对数据库操作的方法！ 涉及事务操作，需要提交事务； 也是消耗资源的，所以要及时关闭！
	 * 
	 * 不是线程安全的，所以同一个sqlSession不能被共享； 最好是放在请求中，或是方法中！
	 * 一次请求，创建一个sqlSession，或者一个方法使用一个sqlsesion 不能将sqlSession作为静态属性或者是实例属性！
	 * 
	 * 
	 */
	/*
	 * @Test public void test() throws IOException {
	 * 
	 * SqlSession session = sqlSessionFactory.openSession();
	 * 
	 * try { // Blog blog = (Blog)
	 * session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
	 * 
	 * //statement: sql语句的id: namespace.id // parameter： sql中的参数 Object result =
	 * session.selectOne("helloworld.selectEmp", "adgaerxcadfa");
	 * 
	 * System.out.println(result);
	 * 
	 * } finally { session.close(); } }
	 * 
	 */

}
