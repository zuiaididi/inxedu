package com.inxedu.os.edu.service.course;

import com.inxedu.os.edu.entity.course.CourseStudyHistoryTime;

/**
 * CourseStudyHistoryTime 用户课程学习记录 管理接口
 * @author www.inxedu.com
 */
public interface CourseStudyHistoryTimeService {

    /**
     * 添加CourseStudyHistoryTime
     * @param courseStudyHistoryTime 要添加的CourseStudyHistoryTime
     * @return id
     */
    public java.lang.Long addCourseStudyHistoryTime(CourseStudyHistoryTime courseStudyHistoryTime);
    
    public void deleteCourseStudyHistoryTimeById(Long id);
    public void deleteCourseStudyHistoryTimeByHistory(Long HistoryId);
    public void deleteCourseStudyHistoryTimeByHistories(String ids);
    public void deleteCourseStudyHistoryTimeByUserId(Long userId);
    public void deleteCourseStudyHistoryTimeByCourseId(Long courseId);
    public void deleteCourseStudyHistoryTimeByKpointId(Long kpointId);
    
}