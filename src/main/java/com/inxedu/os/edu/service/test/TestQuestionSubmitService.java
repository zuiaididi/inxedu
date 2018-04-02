package com.inxedu.os.edu.service.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.test.TestQuestionSubmit;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface TestQuestionSubmitService {
	/**
	 * 创建问题提交记录
	 * @param testQuestionSubmit
	 * @return 返回ID
	 */
	public int createTestQuestionSubmit(TestQuestionSubmit testQuestionSubmit);
	
	/**
	 * 通过问题提交ID查询问题提交记录
	 * @param questionSubmitId 问题提交ID
	 * @return TestQuestionSubmit
	 */
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionSubmitId(int questionSubmitId);
	
	/**
	 * 通过测验提交ID查询问题提交记录
	 * @param testSubmitId 测验提交ID
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitByTestSubmitId(int testSubmitId);
	
	/**
	 * 通过问题ID查询问题提交记录
	 * @param questionId 问题ID
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitByQuestionId(int questionId);
	
	/**
	 * 查询问题提交记录
	 * @param testQuestionSubmit
	 * @return List<TestQuestionSubmit>
	 */
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionIdAndTestSubmitId(TestQuestionSubmit testQuestionSubmit);
	
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionIdAndUserId(TestQuestionSubmit testQuestionSubmit);
	/**
	 * 通过问题提交ID删除问题提交记录
	 * @param questionSubmitId 问题提交ID
	 */
	public void deleteTestQuestionSubmit(int questionSubmitId);
	/**
	 * 更新问题提交记录
	 * @param testQuestionSubmit
	 */
	public void updateTestQuestionSubmit(TestQuestionSubmit testQuestionSubmit);
	/**
	 * 分页查询问题提交记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitListPage(TestQuestionSubmit query,PageEntity page);
	
}