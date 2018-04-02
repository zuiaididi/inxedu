package com.inxedu.os.edu.service.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.test.TestSubmit;

import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
public interface TestSubmitService {
	/**
	 * 创建测验提交记录
	 * @param testSubmit
	 * @return 返回ID
	 */
	public int createTestSubmit(TestSubmit testSubmit);
	
	/**
	 * 通过测验提交ID查询测验提交记录
	 * @param testSubmitId 测验提交ID
	 * @return TestSubmit
	 */
	public TestSubmit queryTestSubmitBySubmitId(int testSubmitId);
	
	/**
	 * 通过学员ID查询测验提交记录
	 * @param userId 学员ID
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitByUserId(int userId);
	
	/**
	 * 通过测验ID查询测验提交记录
	 * @param testId 测验ID
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitByTestId(int testId);
	
	/**
	 * 查询测验提交记录
	 * @param testSubmit
	 * @return List<TestSubmit>
	 */
	public TestSubmit queryTestSubmitByTestIdAndUserId(TestSubmit testSubmit);
	
	public void updateTestSubmit(TestSubmit testSubmit);
	
	/**
	 * 通过测验提交ID删除测验提交记录
	 * @param testSubmitId 测验提交ID
	 */
	public void deleteTestSubmit(int testSubmitId);
	
	/**
	 * 分页查询测验提交记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitListPage(TestSubmit query,PageEntity page);
	
}