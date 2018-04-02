package com.inxedu.os.edu.dao.impl.test;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestQuestionGradeDao;
import com.inxedu.os.edu.entity.test.TestQuestionGrade;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author www.inxedu.com
 *
 */
@Repository("testQuestionGradeDao")
public class TestQuestionGradeDaoImpl extends GenericDaoImpl implements TestQuestionGradeDao{
	/**
	 * 创建问题成绩
	 * @param testQuestionGrade
	 * @return 返回ID
	 */
	public int createTestQuestionGrade(TestQuestionGrade testQuestionGrade){
		this.insert("TestQuestionGradeMapper.createTestQuestionGrade", testQuestionGrade);
		return testQuestionGrade.getQuestionGradeId();
	}
	
	/**
	 * 通过问题成绩ID查询问题成绩记录
	 * @param testQuestionGradeId 问题成绩ID
	 * @return TestQuestionGrade
	 */
	public TestQuestionGrade queryTestQuestionGradeByQuestionGradeId(int testQuestionGradeId){
	    return this.selectOne("TestQuestionGradeMapper.queryTestQuestionGradeByQuestionGradeId", testQuestionGradeId);	
	}
	
	/**
	 * 通过问题提交ID查询问题成绩记录
	 * @param questionSubmitId 问题提交ID
	 * @return List<TestQuestionGrade>
	 */
	public TestQuestionGrade queryTestQuestionGradeByQuestionSubmitId(int questionSubmitId){
		return this.selectOne("TestQuestionGradeMapper.queryTestQuestionGradeByQuestionSubmitId", questionSubmitId);
	}
	
	/**
	 * 通过测验成绩ID查询问题成绩记录
	 * @param testGradeId 测验成绩ID
	 * @return List<TestQuestionGrade>
	 */
	public List<TestQuestionGrade> queryTestQuestionGradeByTestGradeId(int testGradeId){
		return this.selectList("TestQuestionGradeMapper.queryTestQuestionGradeByTestGradeId", testGradeId);
	}
	
	
	/**
	 * 查询问题成绩记录
	 * @param testQuestionGrade
	 * @return List<TestQuestionGrade>
	 */
	public TestQuestionGrade queryTestQuestionGradeByTestGradeIdAndQuestionSubmitId(TestQuestionGrade testQuestionGrade){
		return this.selectOne("TestQuestionGradeMapper.queryTestQuestionGradeByTestGradeIdAndQuestionSubmitId", testQuestionGrade);
	}
	
	/**
	 * 修改问题成绩记录
	 * @param testQuestionGrade
	 */
	public void updateTestQuestionGrade(TestQuestionGrade testQuestionGrade){
		this.update("TestQuestionGradeMapper.updateTestQuestionGrade", testQuestionGrade);
	}
	
	public void updateTestQuestionGrade2(TestQuestionGrade testQuestionGrade){
		this.update("TestQuestionGradeMapper.updateTestQuestionGrade2", testQuestionGrade);
	}
	
	/**
	 * 分页查询问题成绩记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestQuestionGrade>
	 */
	public List<TestQuestionGrade> queryTestQuestionGradeListPage(TestQuestionGrade query,PageEntity page){
		return this.queryForListPage("TestQuestionGradeMapper.queryTestQuestionGradeListPage", query, page);
	}
	
}