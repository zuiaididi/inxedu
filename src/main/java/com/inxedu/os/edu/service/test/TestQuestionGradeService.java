package com.inxedu.os.edu.service.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.test.TestQuestionGrade;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface TestQuestionGradeService {
	/**
	 * 创建问题成绩
	 * @param testQuestionGrade
	 * @return 返回ID
	 */
	public int createTestQuestionGrade(TestQuestionGrade testQuestionGrade);
	
	/**
	 * 通过问题成绩ID查询问题成绩记录
	 * @param testQuestionGradeId 问题成绩ID
	 * @return TestQuestionGrade
	 */
	public TestQuestionGrade queryTestQuestionGradeByQuestionGradeId(int testQuestionGradeId);
	
	/**
	 * 通过问题提交ID查询问题成绩记录
	 * @param questionSubmitId 问题提交ID
	 * @return List<TestQuestionGrade>
	 */
	public TestQuestionGrade queryTestQuestionGradeByQuestionSubmitId(int questionSubmitId);
	
	/**
	 * 通过测验成绩ID查询问题成绩记录
	 * @param testGradeId 测验成绩ID
	 * @return List<TestQuestionGrade>
	 */
	public List<TestQuestionGrade> queryTestQuestionGradeByTestGradeId(int testGradeId);
	
	
	/**
	 * 查询问题成绩记录
	 * @param testQuestionGrade
	 * @return List<TestQuestionGrade>
	 */
	public TestQuestionGrade queryTestQuestionGradeByTestGradeIdAndQuestionSubmitId(TestQuestionGrade testQuestionGrade);
	
	/**
	 * 修改问题成绩记录
	 * @param testQuestionGrade
	 */
	public void updateTestQuestionGrade(TestQuestionGrade testQuestionGrade);
	
	public void updateTestQuestionGrade2(TestQuestionGrade testQuestionGrade);
	
	/**
	 * 分页查询问题成绩记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestionGrade>
	 */
	public List<TestQuestionGrade> queryTestQuestionGradeListPage(TestQuestionGrade query,PageEntity page);
	
}