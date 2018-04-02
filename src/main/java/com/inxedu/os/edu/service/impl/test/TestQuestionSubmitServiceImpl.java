package com.inxedu.os.edu.service.impl.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestQuestionSubmitDao;
import com.inxedu.os.edu.entity.test.TestQuestionSubmit;
import com.inxedu.os.edu.service.test.TestQuestionSubmitService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.inxedu.com
 *
 */
@Service("testQuestionSubmitService")
public class TestQuestionSubmitServiceImpl implements TestQuestionSubmitService{
	@Autowired
	private TestQuestionSubmitDao testQuestionSubmitDao;
	/**
	 * 创建问题提交记录
	 * @param testQuestionSubmit
	 * @return 返回ID
	 */
	public int createTestQuestionSubmit(TestQuestionSubmit testQuestionSubmit){
		return testQuestionSubmitDao.createTestQuestionSubmit(testQuestionSubmit);
	}
	
	/**
	 * 通过问题提交ID查询问题提交记录
	 * @param questionSubmitId 问题提交ID
	 * @return TestQuestionSubmit
	 */
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionSubmitId(int questionSubmitId){
		return testQuestionSubmitDao.queryTestQuestionSubmitByQuestionSubmitId(questionSubmitId);
	}
	
	/**
	 * 通过测验提交ID查询问题提交记录
	 * @param testSubmitId 测验提交ID
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitByTestSubmitId(int testSubmitId){
		return testQuestionSubmitDao.queryTestQuestionSubmitByTestSubmitId(testSubmitId);
	}
	
	/**
	 * 通过问题ID查询问题提交记录
	 * @param questionId 问题ID
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitByQuestionId(int questionId){
		return testQuestionSubmitDao.queryTestQuestionSubmitByQuestionId(questionId);
	}
	
	/**
	 * 查询问题提交记录
	 * @param testQuestionSubmit
	 * @return List<TestQuestionSubmit>
	 */
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionIdAndTestSubmitId(TestQuestionSubmit testQuestionSubmit){
		return testQuestionSubmitDao.queryTestQuestionSubmitByQuestionIdAndTestSubmitId(testQuestionSubmit);
	}
	
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionIdAndUserId(TestQuestionSubmit testQuestionSubmit){
		return testQuestionSubmitDao.queryTestQuestionSubmitByQuestionIdAndUserId(testQuestionSubmit);
	}
	/**
	 * 通过问题提交ID删除问题提交记录
	 * @param questionSubmitId 问题提交ID
	 */
	public void deleteTestQuestionSubmit(int questionSubmitId){
		testQuestionSubmitDao.deleteTestQuestionSubmit(questionSubmitId);
	}
	/**
	 * 更新问题提交记录
	 * @param testQuestionSubmit
	 */
	public void updateTestQuestionSubmit(TestQuestionSubmit testQuestionSubmit){
		testQuestionSubmitDao.updateTestQuestionSubmit(testQuestionSubmit);
	}
	/**
	 * 分页查询问题提交记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitListPage(TestQuestionSubmit query,PageEntity page){
		return testQuestionSubmitDao.queryTestQuestionSubmitListPage(query, page);
	}
	
}