package com.inxedu.os.edu.service.impl.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestQuestionGradeDao;
import com.inxedu.os.edu.entity.test.TestQuestionGrade;
import com.inxedu.os.edu.service.test.TestQuestionGradeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.inxedu.com
 *
 */
@Service("testQuestionGradeService")
public class TestQuestionGradeServiceImpl implements TestQuestionGradeService{
	@Autowired
	private TestQuestionGradeDao testQuestionGradeDao;
	/**
	 * 创建问题成绩
	 * @param testQuestionGrade
	 * @return 返回ID
	 */
	public int createTestQuestionGrade(TestQuestionGrade testQuestionGrade){
		return testQuestionGradeDao.createTestQuestionGrade(testQuestionGrade);
	}
	
	/**
	 * 通过问题成绩ID查询问题成绩记录
	 * @param testQuestionGradeId 问题成绩ID
	 * @return TestQuestionGrade
	 */
	public TestQuestionGrade queryTestQuestionGradeByQuestionGradeId(int testQuestionGradeId){
		return testQuestionGradeDao.queryTestQuestionGradeByQuestionGradeId(testQuestionGradeId);
	}
	
	/**
	 * 通过问题提交ID查询问题成绩记录
	 * @param questionSubmitId 问题提交ID
	 * @return List<TestQuestionGrade>
	 */
	public TestQuestionGrade queryTestQuestionGradeByQuestionSubmitId(int questionSubmitId){
		return testQuestionGradeDao.queryTestQuestionGradeByQuestionSubmitId(questionSubmitId);
	}
	
	/**
	 * 通过测验成绩ID查询问题成绩记录
	 * @param testGradeId 测验成绩ID
	 * @return List<TestQuestionGrade>
	 */
	public List<TestQuestionGrade> queryTestQuestionGradeByTestGradeId(int testGradeId){
		return testQuestionGradeDao.queryTestQuestionGradeByTestGradeId(testGradeId);
	}
	
	
	/**
	 * 查询问题成绩记录
	 * @param testQuestionGrade
	 * @return List<TestQuestionGrade>
	 */
	public TestQuestionGrade queryTestQuestionGradeByTestGradeIdAndQuestionSubmitId(TestQuestionGrade testQuestionGrade){
		return testQuestionGradeDao.queryTestQuestionGradeByTestGradeIdAndQuestionSubmitId(testQuestionGrade);
	}
	
	/**
	 * 修改问题成绩记录
	 * @param testQuestionGrade
	 */
	public void updateTestQuestionGrade(TestQuestionGrade testQuestionGrade){
		testQuestionGradeDao.updateTestQuestionGrade(testQuestionGrade);
	}
	
	public void updateTestQuestionGrade2(TestQuestionGrade testQuestionGrade){
		testQuestionGradeDao.updateTestQuestionGrade2(testQuestionGrade);
	}
	
	/**
	 * 分页查询问题成绩记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestionGrade>
	 */
	public List<TestQuestionGrade> queryTestQuestionGradeListPage(TestQuestionGrade query,PageEntity page){
		return testQuestionGradeDao.queryTestQuestionGradeListPage(query, page);
	}
	
}