package com.inxedu.os.edu.service.impl.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.classes.ClassDao;
import com.inxedu.os.edu.entity.classes.Class;
import com.inxedu.os.edu.entity.classes.ClassDto;
import com.inxedu.os.edu.entity.classes.QueryClass;
import com.inxedu.os.edu.service.classes.ClassService;
@Service("classService")
public class ClassServiceImpl implements ClassService{
	@Autowired
	private ClassDao classDao;
	
	public int addClass(Class clazz){
		return classDao.addClass(clazz);
	}
	
    public Class queryClassById(int classId){
        return classDao.queryClassById(classId);	
    }
    
    public ClassDto queryClassDtoById(int classId){
        return classDao.queryClassDtoById(classId);	
    }
    
    public List<Class> queryClassByGrade(int gradeId){
        return classDao.queryClassByGrade(gradeId);	
    }
    
    public int queryClassByGradeCount(int gradeId){
        return classDao.queryClassByGradeCount(gradeId);	
    }
    
    public List<Class> queryClassByMajor(int majorId){
    	return classDao.queryClassByMajor(majorId);
    }
    
    public int queryClassByMajorCount(int majorId){
    	return classDao.queryClassByMajorCount(majorId);
    }
    
    public List<Class> queryClasses(QueryClass query){
    	return classDao.queryClasses(query);
    }
    
    public int queryClassesCount(QueryClass query){
    	return classDao.queryClassesCount(query);
    }
    
    public void updateClass(Class clazz){
    	classDao.updateClass(clazz);
    }
    
    public void deleteClass(int classId){
    	classDao.deleteClass(classId);
    }
    
    public List<ClassDto> getClassListPage(QueryClass query,PageEntity page){
    	return classDao.getClassListPage(query, page);
    }
}
