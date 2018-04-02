package com.inxedu.os.edu.dao.impl.test;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestQuestionSubmitDao;
import com.inxedu.os.edu.entity.test.TestQuestionSubmit;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author www.inxedu.com
 *
 */
@Repository("testQuestionSubmitDao")
public class TestQuestionSubmitDaoImpl extends GenericDaoImpl implements TestQuestionSubmitDao{
	/**
	 * 创建问题提交记录
	 * @param testQuestionSubmit
	 * @return 返回ID
	 */
	public int createTestQuestionSubmit(TestQuestionSubmit testQuestionSubmit){
		this.insert("TestQuestionSubmitMapper.createTestQuestionSubmit", testQuestionSubmit);
		return testQuestionSubmit.getQuestionSubmitId();
	}
	
	/**
	 * 通过问题提交ID查询问题提交记录
	 * @param questionSubmitId 问题提交ID
	 * @return TestQuestionSubmit
	 */
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionSubmitId(int questionSubmitId){
		return this.selectOne("TestQuestionSubmitMapper.queryTestQuestionSubmitByQuestionSubmitId", questionSubmitId);
	}
	
	/**
	 * 通过测验提交ID查询问题提交记录
	 * @param testSubmitId 测验提交ID
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitByTestSubmitId(int testSubmitId){
		return this.selectList("TestQuestionSubmitMapper.queryTestQuestionSubmitByTestSubmitId", testSubmitId);
	}
	
	/**
	 * 通过问题ID查询问题提交记录
	 * @param questionId 问题ID
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitByQuestionId(int questionId){
		return this.selectList("TestQuestionSubmitMapper.queryTestQuestionSubmitByQuestionId", questionId);
	}
	
	/**
	 * 查询问题提交记录
	 * @param testQuestionSubmit
	 * @return List<TestQuestionSubmit>
	 */
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionIdAndTestSubmitId(TestQuestionSubmit testQuestionSubmit){
		return this.selectOne("TestQuestionSubmitMapper.queryTestQuestionSubmitByQuestionIdAndTestSubmitId", testQuestionSubmit);
	}
	
	public TestQuestionSubmit queryTestQuestionSubmitByQuestionIdAndUserId(TestQuestionSubmit testQuestionSubmit){
		return this.selectOne("TestQuestionSubmitMapper.queryTestQuestionSubmitByQuestionIdAndUserId", testQuestionSubmit);
	}
	/**
	 * 通过问题提交ID删除问题提交记录
	 * @param questionSubmitId 问题提交ID
	 */
	public void deleteTestQuestionSubmit(int questionSubmitId){
		this.delete("TestQuestionSubmitMapper.deleteTestQuestionSubmit", questionSubmitId);
	}
	/**
	 * 更新问题提交记录
	 * @param testQuestionSubmit
	 */
	public void updateTestQuestionSubmit(TestQuestionSubmit testQuestionSubmit){
		this.update("TestQuestionSubmitMapper.updateTestQuestionSubmit", testQuestionSubmit);
	}
	/**
	 * 分页查询问题提交记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestionSubmit>
	 */
	public List<TestQuestionSubmit> queryTestQuestionSubmitListPage(TestQuestionSubmit query,PageEntity page){
		return this.queryForListPage("TestQuestionSubmitMapper.queryTestQuestionSubmitListPage", query, page);
	}
	
}