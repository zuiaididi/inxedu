package com.inxedu.os.edu.dao.grade;

import java.util.List;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.grade.Grade;
import com.inxedu.os.edu.entity.grade.QueryGrade;

public interface GradeDao {
	
	public int addGrade(Grade grade);
	
    public Grade queryGradeById(int gradeId);
    
    public List<Grade> queryGrades();
    
    public int queryGradesCount(int majorId);
    
    public void updateGrade(Grade grade);
    
    public void deleteGrade(int gradeId);
    
    public List<Grade> getGradeListPage(QueryGrade query,PageEntity page);
}
