package com.inxedu.os.edu.dao.impl.test;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.test.TestGradeDao;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.QueryTestSubmitGrade;
import com.inxedu.os.edu.entity.test.TestGrade;
import com.inxedu.os.edu.entity.test.TestGradeDto;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author www.inxedu.com
 *
 */
@Repository("testGradeDao")
public class TestGradeDaoImpl extends GenericDaoImpl implements TestGradeDao{
	/**
	 * 创建测验成绩
	 * @param testGrade
	 * @return 返回ID
	 */
	public int createTestGrade(TestGrade testGrade){
		this.insert("TestGradeMapper.createTestGrade", testGrade);
		return testGrade.getTestGradeId();
	}
	
	/**
	 * 通过测验成绩ID查询测验成绩记录
	 * @param testGradeId 测验成绩ID
	 * @return TestGrade
	 */
	public TestGrade queryTestGradeByTestGradeId(int testGradeId){
		return this.selectOne("TestGradeMapper.queryTestGradeByTestGradeId", testGradeId);
	}
	public TestGradeDto queryTestGradeDtoByTestGradeId(int testGradeId){
		return this.selectOne("TestGradeMapper.queryTestGradeDtoByTestGradeId", testGradeId);
	}
	/**
	 * 通过测验提交ID查询测验成绩记录
	 * @param testSubmitId 测验提交ID
	 * @return List<TestGrade>
	 */
	public TestGrade queryTestGradeByTestSubmitId(int testSubmitId){
		return this.selectOne("TestGradeMapper.queryTestGradeByTestSubmitId", testSubmitId);
	}
	
	/**
	 * 修改测验成绩记录
	 * @param testGrade
	 */
	public void updateTestGrade(TestGrade testGrade){
		this.update("TestGradeMapper.updateTestGrade", testGrade);
	}
	
	
	/**
	 * 分页查询测验成绩记录
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<TestGrade>
	 */
	public List<TestGradeDto> queryTestGradeDtoListPage(QueryTestSubmitGrade query,PageEntity page){
		return this.queryForListPage("TestGradeMapper.queryTestGradeDtoListPage", query, page);
	}
	public List<TestGradeDto> queryTestGradeDtoListPage2(QueryTest queryTest,PageEntity page){
		return this.queryForListPage("TestGradeMapper.queryTestGradeDtoListPage2", queryTest, page);
	}
	
	public List<TestGradeDto> queryTestGradeDtoList(QueryTestSubmitGrade query){
		return this.selectList("TestGradeMapper.queryTestGradeDtoList",query);
	}
}