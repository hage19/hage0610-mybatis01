package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.bean.Employee;

/**
 * 
 * @author hage 2019年6月10日下午3:06:09
 */
public interface EmployeeMapper {
	
	Employee selectEmployeeById(Integer id);
	

	void insertEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Integer id);
}
