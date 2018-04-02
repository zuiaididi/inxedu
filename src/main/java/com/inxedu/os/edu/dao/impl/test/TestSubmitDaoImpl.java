package com.inxedu.os.edu.dao.impl.test;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestSubmitDao;
import com.inxedu.os.edu.entity.test.TestSubmit;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author www.inxedu.com
 *
 */
@Repository("testSubmitDao")
public class TestSubmitDaoImpl extends GenericDaoImpl implements TestSubmitDao{
	/**
	 * 创建测验提交记录
	 * @param testSubmit
	 * @return 返回ID
	 */
	public int createTestSubmit(TestSubmit testSubmit){
		this.insert("TestSubmitMapper.createTestSubmit", testSubmit);
		return testSubmit.getTestSubmitId();
	}
	
	/**
	 * 通过测验提交ID查询测验提交记录
	 * @param testSubmitId 测验提交ID
	 * @return TestSubmit
	 */
	public TestSubmit queryTestSubmitBySubmitId(int testSubmitId){
		return this.selectOne("TestSubmitMapper.queryTestSubmitBySubmitId", testSubmitId);
	}
	
	/**
	 * 通过学员ID查询测验提交记录
	 * @param userId 学员ID
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitByUserId(int userId){
		return this.selectList("TestSubmitMapper.queryTestSubmitByUserId", userId);
	}
	
	/**
	 * 通过测验ID查询测验提交记录
	 * @param testId 测验ID
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitByTestId(int testId){
		return this.selectList("TestSubmitMapper.queryTestSubmitByTestId", testId);
	}
	
	/**
	 * 查询测验提交记录
	 * @param testSubmit
	 * @return List<TestSubmit>
	 */
	public TestSubmit queryTestSubmitByTestIdAndUserId(TestSubmit testSubmit){
		return this.selectOne("TestSubmitMapper.queryTestSubmitByTestIdAndUserId", testSubmit);
	}
	
	/**
	 * 更新测验提交记录
	 */
	public void updateTestSubmit(TestSubmit testSubmit){
		this.update("TestSubmitMapper.updateTestSubmit", testSubmit);
	}
	
	/**
	 * 通过测验提交ID删除测验提交记录
	 * @param testSubmitId 测验提交ID
	 */
	public void deleteTestSubmit(int testSubmitId){
		this.delete("TestSubmitMapper.deleteTestSubmit", testSubmitId);
	}
	
	/**
	 * 分页查询测验提交记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestSubmit>
	 */
	public List<TestSubmit> queryTestSubmitListPage(TestSubmit query,PageEntity page){
		return this.queryForListPage("TestSubmitMapper.queryTestSubmitListPage", query, page);
	}
	
}