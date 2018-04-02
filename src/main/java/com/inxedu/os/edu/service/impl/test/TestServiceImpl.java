package com.inxedu.os.edu.service.impl.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestDao;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.Test;
import com.inxedu.os.edu.service.test.TestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.inxedu.com
 *
 */
@Service("testService")
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;
	/**
	 * 创建测验
	 * @param test
	 * @return 返回ID
	 */
	public int createTest(Test test){
		return testDao.createTest(test);
	}
	
	/**
	 * 通过测验ID查询测验记录
	 * @param testId 测验ID
	 * @return Test
	 */
	public Test queryTestByTestId(int testId){
		return testDao.queryTestByTestId(testId);
	}
	
	/**
	 * 通过课程ID查询测验记录
	 * @param courseId 课程ID
	 * @return List<Test>
	 */
	public List<Test> queryTestByCourseId(int courseId){
		return testDao.queryTestByCourseId(courseId);
	}
	
	/**
	 * 通过老师ID查询测验记录
	 * @param teacherId 老师ID
	 * @return List<Test>
	 */
	public List<Test> queryTestByTeacherId(int teacherId){
		return testDao.queryTestByTeacherId(teacherId);
	}
	
	/**
	 * 通过测验ID删除测验记录
	 * @param testId 测验ID
	 */
	public void deleteTest(int testId){
		testDao.deleteTest(testId);
	}
	
	/**
	 * 修改测验记录
	 * @param test
	 */
	public void updateTest(Test test){
		testDao.updateTest(test);
	}
	
	
	/**
	 * 分页查询测验记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<Test>
	 */
	public List<Test> queryTestListPage(QueryTest query,PageEntity page){
		return testDao.queryTestListPage(query, page);
	}
	
}