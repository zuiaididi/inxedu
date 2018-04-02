package com.inxedu.os.edu.dao.impl.test;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestDao;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.Test;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author www.inxedu.com
 *
 */
@Repository("testDao")
public class TestDaoImpl extends GenericDaoImpl implements TestDao {
	/**
	 * 创建测验
	 * @param test
	 * @return 返回ID
	 */
	public int createTest(Test test){
		this.insert("TestMapper.createTest", test);
		return test.getTestId();
	}
	
	/**
	 * 通过测验ID查询测验记录
	 * @param testId 测验ID
	 * @return Test
	 */
	public Test queryTestByTestId(int testId){
		return this.selectOne("TestMapper.queryTestByTestId", testId);
	}
	
	/**
	 * 通过课程ID查询测验记录
	 * @param courseId 课程ID
	 * @return List<Test>
	 */
	public List<Test> queryTestByCourseId(int courseId){
		return this.selectList("TestMapper.queryTestByCourseId", courseId);
	}
	
	/**
	 * 通过老师ID查询测验记录
	 * @param teacherId 老师ID
	 * @return List<Test>
	 */
	public List<Test> queryTestByTeacherId(int teacherId){
		return this.selectList("TestMapper.queryTestByTeacherId", teacherId);
	}
	
	/**
	 * 通过测验ID删除测验记录
	 * @param testId 测验ID
	 */
	public void deleteTest(int testId){
		this.delete("TestMapper.deleteTest", testId);
	}
	
	/**
	 * 修改测验记录
	 * @param test
	 */
	public void updateTest(Test test){
		this.update("TestMapper.updateTest", test);
	}
	
	
	/**
	 * 分页查询测验记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<Test>
	 */
	public List<Test> queryTestListPage(QueryTest query,PageEntity page){
		return this.queryForListPage("TestMapper.queryTestListPage", query, page);
	}
	
}