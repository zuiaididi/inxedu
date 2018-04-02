package com.inxedu.os.edu.dao.classes;

import java.util.List;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.classes.Class;
import com.inxedu.os.edu.entity.classes.ClassDto;
import com.inxedu.os.edu.entity.classes.QueryClass;

public interface ClassDao {
	
	public int addClass(Class clazz);
	
    public Class queryClassById(int classId);
    
    public ClassDto queryClassDtoById(int classId);
    
    public List<Class> queryClassByGrade(int gradeId);
    
    public int queryClassByGradeCount(int gradeId);
    
    public List<Class> queryClasses(QueryClass query);
    
    public int queryClassesCount(QueryClass query);
    
    public List<Class> queryClassByMajor(int majorId);
    
    public int queryClassByMajorCount(int majorId);
    
    public void updateClass(Class clazz);
    
    public void deleteClass(int classId);
    
    public List<ClassDto> getClassListPage(QueryClass query,PageEntity page);
}
