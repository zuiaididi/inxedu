package com.inxedu.os.edu.service.impl.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.grade.GradeDao;
import com.inxedu.os.edu.entity.grade.Grade;
import com.inxedu.os.edu.entity.grade.QueryGrade;
import com.inxedu.os.edu.service.grade.GradeService;
@Service("gradeService")
public class GradeServiceImpl implements GradeService{
	@Autowired
	private GradeDao gradeDao;
	
	public int addGrade(Grade grade){
		return gradeDao.addGrade(grade);
	}
	
	public Grade queryGradeById(int gradeId){
        return gradeDao.queryGradeById(gradeId);	
    }
	
    public List<Grade> queryGrades(){
        return gradeDao.queryGrades();	
    }
    
    public int queryGradesCount(int majorId){
        return gradeDao.queryGradesCount(majorId);	
    }
    
    public void updateGrade(Grade grade){
    	gradeDao.updateGrade(grade);
    }
    
    public void deleteGrade(int gradeId){
    	gradeDao.deleteGrade(gradeId);
    }
    
    public List<Grade> getGradeListPage(QueryGrade query,PageEntity page){
    	return gradeDao.getGradeListPage(query, page);
    }
}
