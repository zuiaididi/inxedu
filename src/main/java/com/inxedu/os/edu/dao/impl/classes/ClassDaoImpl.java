package com.inxedu.os.edu.dao.impl.classes;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.classes.ClassDao;
import com.inxedu.os.edu.entity.classes.Class;
import com.inxedu.os.edu.entity.classes.ClassDto;
import com.inxedu.os.edu.entity.classes.QueryClass;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * @author www.inxedu.com
 *
 */
@Repository("classDao")
public class ClassDaoImpl extends GenericDaoImpl implements ClassDao {

	
	public int addClass(Class clazz) {
		this.insert("ClassMapper.createClass", clazz);
		return clazz.getClassId();
	}
	
	public Class queryClassById(int classId) {
		return this.selectOne("ClassMapper.queryClassById", classId);
	}
	
	public ClassDto queryClassDtoById(int classId) {
		return this.selectOne("ClassMapper.queryClassDtoById", classId);
	}
	
	public List<Class> queryClassByGrade(int gradeId) {
		return this.selectList("ClassMapper.queryClassByGrade", gradeId);
	}
	
	public int queryClassByGradeCount(int gradeId) {
		return this.selectOne("ClassMapper.queryClassByGradeCount", gradeId);
	}
	
    public List<Class> queryClassByMajor(int majorId){
    	return this.selectList("ClassMapper.queryClassByMajor", majorId);
    }
    
    public int queryClassByMajorCount(int majorId){
    	return this.selectOne("ClassMapper.queryClassByMajorCount", majorId);
    }
    
    public List<Class> queryClasses(QueryClass query){
    	return this.selectList("ClassMapper.queryClasses", query);
    }
    
    public int queryClassesCount(QueryClass query){
    	return this.selectOne("ClassMapper.queryClassesCount", query);
    }
    
	public void updateClass(Class clazz) {
		this.update("ClassMapper.updateClass", clazz);
	}
	
	public void deleteClass(int classId){
		this.delete("ClassMapper.deleteClass", classId);
	}
	
	public List<ClassDto> getClassListPage(QueryClass query,PageEntity page){
		return this.queryForListPage("ClassMapper.getClassListPage", query, page);
	}
	
}
