package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.edu.dao.course.CourseStudyHistoryTimeDao;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryTime;
import com.inxedu.os.edu.service.course.CourseStudyHistoryTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * CourseStudyHistoryTime 用户课程学下记录 service接口实现
 * @author www.inxedu.com
 */
@Service("courseStudyHistoryTimeService")
public class CourseStudyHistoryTimeServiceImpl implements CourseStudyHistoryTimeService {

	@Autowired
	private CourseStudyHistoryTimeDao courseStudyHistoryTimeDao;

	/**
	 * 添加CourseStudyHistoryTime
	 * 
	 * @param courseStudyHistoryTime
	 *            要添加的CourseStudyHistoryTime
	 * @return id
	 */
	public java.lang.Long addCourseStudyHistoryTime(CourseStudyHistoryTime courseStudyHistoryTime) {
		return courseStudyHistoryTimeDao.addCourseStudyHistoryTime(courseStudyHistoryTime);
	}

	

	/**
	 * 根据id删除一个CourseStudyHistoryTime
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseStudyHistoryTimeById(Long id) {
		courseStudyHistoryTimeDao.deleteCourseStudyHistoryTimeById(id);
	}
	public void deleteCourseStudyHistoryTimeByHistory(Long historyId) {
		courseStudyHistoryTimeDao.deleteCourseStudyHistoryTimeByHistory(historyId);
	}
	
	public void deleteCourseStudyHistoryTimeByHistories(String ids) {
		courseStudyHistoryTimeDao.deleteCourseStudyHistoryTimeByHistories(ids);
	}
	public void deleteCourseStudyHistoryTimeByUserId(Long userId){
		courseStudyHistoryTimeDao.deleteCourseStudyHistoryTimeByUserId(userId);
    }
    
    public void deleteCourseStudyHistoryTimeByCourseId(Long courseId){
    	courseStudyHistoryTimeDao.deleteCourseStudyHistoryTimeByCourseId(courseId);
    }
    
    public void deleteCourseStudyHistoryTimeByKpointId(Long kpointId){
    	courseStudyHistoryTimeDao.deleteCourseStudyHistoryTimeByKpointId(kpointId);
    }
}