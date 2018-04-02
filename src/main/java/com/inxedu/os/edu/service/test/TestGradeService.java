package com.inxedu.os.edu.service.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.QueryTestSubmitGrade;
import com.inxedu.os.edu.entity.test.TestGrade;
import com.inxedu.os.edu.entity.test.TestGradeDto;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface TestGradeService {
	/**
	 * 创建测验成绩
	 * @param testGrade
	 * @return 返回ID
	 */
	public int createTestGrade(TestGrade testGrade);
	
	/**
	 * 通过测验成绩ID查询测验成绩记录
	 * @param testGradeId 测验成绩ID
	 * @return TestGrade
	 */
	public TestGrade queryTestGradeByTestGradeId(int testGradeId);
	public TestGradeDto queryTestGradeDtoByTestGradeId(int testGradeId);
	/**
	 * 通过测验提交ID查询测验成绩记录
	 * @param testSubmitId 测验提交ID
	 * @return List<TestGrade>
	 */
	public TestGrade queryTestGradeByTestSubmitId(int testSubmitId);
	
	/**
	 * 修改测验成绩记录
	 * @param testGrade
	 */
	public void updateTestGrade(TestGrade testGrade);
	
	
	/**
	 * 分页查询测验成绩记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestGrade>
	 */
	public List<TestGradeDto> queryTestGradeDtoListPage(QueryTestSubmitGrade query,PageEntity page);
	public List<TestGradeDto> queryTestGradeDtoListPage2(QueryTest queryTest,PageEntity page);
	
	public List<TestGradeDto> queryTestGradeDtoList(QueryTestSubmitGrade query);
	
}