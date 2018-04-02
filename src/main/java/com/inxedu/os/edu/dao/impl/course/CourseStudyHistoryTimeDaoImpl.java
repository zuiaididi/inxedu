package com.inxedu.os.edu.dao.impl.course;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.course.CourseStudyHistoryTimeDao;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryTime;
import org.springframework.stereotype.Repository;

/**
 *
 * CourseStudyHistoryTime 管理接口实现
 * @author www.inxedu.com
 */
 @Repository("courseStudyHistoryTimeDao")
public class CourseStudyHistoryTimeDaoImpl extends GenericDaoImpl implements CourseStudyHistoryTimeDao{

    public java.lang.Long addCourseStudyHistoryTime(CourseStudyHistoryTime courseStudyHistoryTime) {
        return this.insert("CourseStudyHistoryTimeMapper.createCourseStudyHistoryTime",courseStudyHistoryTime);
    }

    public void deleteCourseStudyHistoryTimeById(Long id){
        this.delete("CourseStudyHistoryTimeMapper.deleteCourseStudyHistoryTimeById",id);
    }
    
    public void deleteCourseStudyHistoryTimeByHistory(Long historyId){
    	this.delete("CourseStudyHistoryTimeMapper.deleteCourseStudyHistoryTimeByHistory", historyId);
    }
    
    public void deleteCourseStudyHistoryTimeByHistories(String ids){
    	this.delete("CourseStudyHistoryTimeMapper.deleteCourseStudyHistoryTimeByHistories", ids);
    }
    
    public void deleteCourseStudyHistoryTimeByUserId(Long userId){
    	this.delete("CourseStudyHistoryTimeMapper.deleteCourseStudyHistoryTimeByUserId", userId);
    }
    
    public void deleteCourseStudyHistoryTimeByCourseId(Long courseId){
    	this.delete("CourseStudyHistoryTimeMapper.deleteCourseStudyHistoryTimeByCourseId", courseId);
    }
    
    public void deleteCourseStudyHistoryTimeByKpointId(Long kpointId){
    	this.delete("CourseStudyHistoryTimeMapper.deleteCourseStudyHistoryTimeByKpointId", kpointId);
    }
   
}
