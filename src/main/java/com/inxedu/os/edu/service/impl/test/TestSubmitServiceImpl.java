package com.inxedu.os.edu.service.impl.test;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestSubmitDao;
import com.inxedu.os.edu.entity.test.TestSubmit;
import com.inxedu.os.edu.service.test.TestSubmitService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author www.inxedu.com
 *
 */
@Service("testSubmitService")
public class TestSubmitServiceImpl implements TestSubmitService{
	@Autowired
	private TestSubmitDao testSubmitDao;
	/**
	 * 创建测验提交记录
	 * @param testSubmit
	 * @return 返回ID
	 */
	public int createTestSubmit(TestSubmit testSubmit){
		return testSubmitDao.createTestSubmit(testSubmit);
	}
	
	/**
	 * 通过测验提交ID查询测验提交记录
	 * @param testSubmitId 测验提交ID
	 * @return TestSubmit
	 */
	public TestSubmit queryTestSubmitBySubmitId(int testSubmitId){
		return testSubmitDao.queryTestSubmitBySubmitId(testSubmitId);
	}
	
	/**
	 * 通过学员ID查询测验提交记录
	 * @param userId 学员ID
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitByUserId(int userId){
		return testSubmitDao.queryTestSubmitByUserId(userId);
	}
	
	/**
	 * 通过测验ID查询测验提交记录
	 * @param testId 测验ID
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitByTestId(int testId){
		return testSubmitDao.queryTestSubmitByTestId(testId);
	}
	
	/**
	 * 查询测验提交记录
	 * @param testSubmit
	 * @return List<TestSubmit>
	 */
	public TestSubmit queryTestSubmitByTestIdAndUserId(TestSubmit testSubmit){
		return testSubmitDao.queryTestSubmitByTestIdAndUserId(testSubmit);
	}
	
	public void updateTestSubmit(TestSubmit testSubmit){
		testSubmitDao.updateTestSubmit(testSubmit);
	}
	
	/**
	 * 通过测验提交ID删除测验提交记录
	 * @param testSubmitId 测验提交ID
	 */
	public void deleteTestSubmit(int testSubmitId){
		testSubmitDao.deleteTestSubmit(testSubmitId);
	}
	
	/**
	 * 分页查询测验提交记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitListPage(TestSubmit query,PageEntity page){
		return testSubmitDao.queryTestSubmitListPage(query, page);
	}
	
}