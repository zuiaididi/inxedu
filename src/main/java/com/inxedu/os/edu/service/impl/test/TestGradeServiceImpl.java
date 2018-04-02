package com.inxedu.os.edu.service.impl.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestGradeDao;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.QueryTestSubmitGrade;
import com.inxedu.os.edu.entity.test.TestGrade;
import com.inxedu.os.edu.entity.test.TestGradeDto;
import com.inxedu.os.edu.service.test.TestGradeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.inxedu.com
 *
 */
@Service("testGradeService")
public class TestGradeServiceImpl implements TestGradeService{
	@Autowired
	private TestGradeDao testGradeDao;
	/**
	 * 创建测验成绩
	 * @param testGrade
	 * @return 返回ID
	 */
	public int createTestGrade(TestGrade testGrade){
		return testGradeDao.createTestGrade(testGrade);
	}
	
	/**
	 * 通过测验成绩ID查询测验成绩记录
	 * @param testGradeId 测验成绩ID
	 * @return TestGrade
	 */
	public TestGrade queryTestGradeByTestGradeId(int testGradeId){
		return testGradeDao.queryTestGradeByTestGradeId(testGradeId);
	}
	public TestGradeDto queryTestGradeDtoByTestGradeId(int testGradeId){
		return testGradeDao.queryTestGradeDtoByTestGradeId(testGradeId);
	}
	/**
	 * 通过测验提交ID查询测验成绩记录
	 * @param testSubmitId 测验提交ID
	 * @return List<TestGrade>
	 */
	public TestGrade queryTestGradeByTestSubmitId(int testSubmitId){
		return testGradeDao.queryTestGradeByTestSubmitId(testSubmitId);
	}
	
	/**
	 * 修改测验成绩记录
	 * @param testGrade
	 */
	public void updateTestGrade(TestGrade testGrade){
		testGradeDao.updateTestGrade(testGrade);
	}
	
	
	/**
	 * 分页查询测验成绩记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestGrade>
	 */
	public List<TestGradeDto> queryTestGradeDtoListPage(QueryTestSubmitGrade query,PageEntity page){
		return testGradeDao.queryTestGradeDtoListPage(query, page);
	}
	public List<TestGradeDto> queryTestGradeDtoListPage2(QueryTest queryTest,PageEntity page){
		return testGradeDao.queryTestGradeDtoListPage2(queryTest, page);
	}
	
	public List<TestGradeDto> queryTestGradeDtoList(QueryTestSubmitGrade query){
		return testGradeDao.queryTestGradeDtoList(query);
	}
	
}