package com.inxedu.os.edu.dao.test;

import com.inxedu.os.common.entity.PageEntity;

import com.inxedu.os.edu.entity.test.TestQuestion;
import com.inxedu.os.edu.entity.test.TestQuestionDto;
import com.inxedu.os.edu.entity.test.TestSubmit;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface TestQuestionDao {
	/**
	 * 创建测验问题
	 * @param testQuestion
	 * @return 返回ID
	 */
	public int createTestQuestion(TestQuestion testQuestion);
	
	/**
	 * 通过测验ID查询问题记录
	 * @param testId 测验ID
	 * @return List<TestQuestion>
	 */
	public List<TestQuestion> queryTestQuestionByTestId(int testId);
	public List<TestQuestionDto> queryTestQuestionDtoListBySubmit(TestSubmit testSubmit);
	
	/**
	 * 通过问题ID查询问题记录
	 * @param questionId 问题ID
	 * @return TestQuestion
	 */
	public TestQuestion queryTestQuestionByQuestionId(int questionId);
	
	/**
	 * 通过问题ID删除问题记录
	 * @param questionId 问题ID
	 */
	public void deleteTestQuestion(int questionId);
	
	/**
	 * 修改问题记录
	 * @param testQuestion
	 */
	public void updateTestQuestion(TestQuestion testQuestion);
	
	
	/**
	 * 分页查询问题记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestion>
	 */
	public List<TestQuestion> queryTestQuestionListPage(TestQuestion query,PageEntity page);
	
}