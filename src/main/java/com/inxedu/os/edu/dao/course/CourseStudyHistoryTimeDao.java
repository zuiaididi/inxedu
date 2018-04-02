package com.inxedu.os.edu.dao.course;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryTime;



/**
 * CourseStudyHistoryTime管理接口
 * @author www.inxedu.com
 */
public interface CourseStudyHistoryTimeDao {

    /**
     * 添加CourseStudyHistoryTime
     * @param courseStudyHistoryTime 要添加的CourseStudyHistoryTime
     * @return id
     */
    public java.lang.Long addCourseStudyHistoryTime(CourseStudyHistoryTime courseStudyHistoryTime);


    /**
     * 根据id删除一个CourseStudyHistoryTime
     * @param id 要删除的id
     */
    public void deleteCourseStudyHistoryTimeById(Long id);
    public void deleteCourseStudyHistoryTimeByHistory(Long HistoryId);
    public void deleteCourseStudyHistoryTimeByHistories(String ids);
    public void deleteCourseStudyHistoryTimeByUserId(Long userId);
    public void deleteCourseStudyHistoryTimeByCourseId(Long courseId);
    public void deleteCourseStudyHistoryTimeByKpointId(Long kpointId);
   
    
}