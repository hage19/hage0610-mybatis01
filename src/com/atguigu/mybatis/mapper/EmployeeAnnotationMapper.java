package com.atguigu.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.mybatis.bean.Employee;

/**
 * 
 * @author hage 2019年6月10日下午3:06:09
 */
public interface EmployeeAnnotationMapper {
	@Select("select * from tbl_employee where id = #{x}")
	Employee selectEmployeeById(Integer id);
	
	@Insert("insert into tbl_employee(last_name,gender,email) values(#{lastName},#{gender},#{email})")
	void insertEmployee(Employee employee);
	@Update("update tbl_employee set last_name=#{lastName},gender=#{gender},email=#{email} where id=#{id}")
	void updateEmployee(Employee employee);
	@Delete("delete from tbl_employee where id=#{y}")
	void deleteEmployee(Integer id);
}
