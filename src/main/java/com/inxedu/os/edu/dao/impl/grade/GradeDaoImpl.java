package com.inxedu.os.edu.dao.impl.grade;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.grade.GradeDao;
import com.inxedu.os.edu.entity.grade.Grade;
import com.inxedu.os.edu.entity.grade.QueryGrade;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * @author www.inxedu.com
 *
 */
@Repository("GradeDao")
public class GradeDaoImpl extends GenericDaoImpl implements GradeDao {

	
	public int addGrade(Grade grade) {
		this.insert("GradeMapper.createGrade", grade);
		return grade.getGradeId();
	}
	
	public Grade queryGradeById(int gradeId) {
		return this.selectOne("GradeMapper.queryGradeById", gradeId);
	}
	
	public List<Grade> queryGrades() {
		return this.selectList("GradeMapper.queryGrades",null);
	}
	
	public int queryGradesCount(int majorId) {
		return this.selectOne("GradeMapper.queryGradesCount", majorId);
	}
	
	public void updateGrade(Grade grade) {
		this.update("GradeMapper.updateGrade", grade);
	}
	
	public void deleteGrade(int gradeId){
		this.delete("GradeMapper.deleteGrade", gradeId);
	}
	
	public List<Grade> getGradeListPage(QueryGrade query,PageEntity page){
		return this.queryForListPage("GradeMapper.getGradeListPage", query, page);
	}
}
