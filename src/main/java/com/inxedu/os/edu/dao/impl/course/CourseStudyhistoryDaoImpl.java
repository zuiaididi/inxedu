package com.inxedu.os.edu.dao.impl.course;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.course.CourseStudyhistoryDao;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryDto;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.course.QueryCourseStudyHistory;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * CourseStudyhistory 管理接口实现
 * @author www.inxedu.com
 */
 @Repository("courseStudyhistoryDao")
public class CourseStudyhistoryDaoImpl extends GenericDaoImpl implements CourseStudyhistoryDao{

    public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return this.insert("CourseStudyhistoryMapper.createCourseStudyhistory",courseStudyhistory);
    }

    public void deleteCourseStudyhistoryById(Long id){
        this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryById",id);
    }
    
    public void deleteCourseStudyhistoryByIds(String ids){
    	this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryByIds", ids);
    }
    
    public void deleteCourseStudyhistoryByUserId(Long userId){
    	this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryByUserId", userId);
    }
    
    public void deleteCourseStudyhistoryByCourseId(Long courseId){
    	this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryByCourseId", courseId);
    }
    
    public void deleteCourseStudyhistoryByKpointId(Long kpointId){
    	this.delete("CourseStudyhistoryMapper.deleteCourseStudyhistoryByKpointId", kpointId);
    }
    
    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        this.update("CourseStudyhistoryMapper.updateCourseStudyhistory",courseStudyhistory);
    }

    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory) {
        return this.selectList("CourseStudyhistoryMapper.getCourseStudyhistoryList",courseStudyhistory);
    }

	public List<CourseStudyhistory> getCourseStudyhistoryListByCouId(Long courseId) {
		return this.selectList("CourseStudyhistoryMapper.getCourseStudyhistoryListByCouId", courseId);
	}

	public List<CourseStudyHistoryDto> getCourseStudyhistoryListByUserId(int userId,PageEntity page){
		return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyhistoryListByUserId", userId, page);
	}
	
	public List<CourseStudyHistoryDto> getCourseStudyhistoryListByCourseId(int courseId,PageEntity page){
		return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyhistoryListByCourseId", courseId, page);
	}
	
    public List<CourseStudyHistoryDto> getCourseStudyHistoryTimeListByUserId(QueryCourseStudyHistory query,PageEntity page){
    	return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyHistoryTimeListByUserId", query, page);
    }
	
	public List<CourseStudyHistoryDto> getCourseStudyHistoryTimeListByCourseId(QueryCourseStudyHistory query,PageEntity page){
		return this.queryForListPage("CourseStudyhistoryMapper.getCourseStudyHistoryTimeListByCourseId", query, page);
	}
	
	public int getCourseStudyhistoryCountByCouId(Long courseId) {
		return (Integer)this.selectOne("CourseStudyhistoryMapper.getCourseStudyhistoryCountByCouId", courseId);
	}
    
}
