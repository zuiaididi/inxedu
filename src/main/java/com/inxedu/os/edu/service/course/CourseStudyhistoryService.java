package com.inxedu.os.edu.service.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryDto;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.course.QueryCourseStudyHistory;

import java.util.List;
/**
 * CourseStudyhistory 用户课程学习记录 管理接口
 * @author www.inxedu.com
 */
public interface CourseStudyhistoryService {

    /**
     * 添加CourseStudyhistory
     * @param courseStudyhistory 要添加的CourseStudyhistory
     * @return id
     */
    public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory);
    /**
     * 添加播放记录和播放次数
     */
    public void playertimes(CourseStudyhistory courseStudyhistory);

    /**
     * 根据id删除一个CourseStudyhistory
     * @param id 要删除的id
     */
    public void deleteCourseStudyhistoryById(Long id);
    public void deleteCourseStudyhistoryByIds(String ids);
    public void deleteCourseStudyhistoryByUserId(Long userId);
    public void deleteCourseStudyhistoryByCourseId(Long courseId);
    public void deleteCourseStudyhistoryByKpointId(Long kpointId);
    /**
     * 修改CourseStudyhistory
     * @param courseStudyhistory 要修改的CourseStudyhistory
     */
    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory);



    /**
     * 根据条件获取CourseStudyhistory列表
     * @param courseStudyhistory 查询条件
     * @return List<CourseStudyhistory>
     */
    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory);
	
	/**
	 * 查看 课程下的 所有的学习记录
	 */
	public List<CourseStudyhistory> getCourseStudyhistoryListByCouId(Long courseId);
	/**
	 * 查看 用户下的 所有的学习记录
	 */
	public List<CourseStudyHistoryDto> getCourseStudyhistoryListByUserId(int userId,PageEntity page);
	
	public List<CourseStudyHistoryDto> getCourseStudyhistoryListByCourseId(int courseId,PageEntity page);
	
    public List<CourseStudyHistoryDto> getCourseStudyHistoryTimeListByUserId(QueryCourseStudyHistory query,PageEntity page);
	
	public List<CourseStudyHistoryDto> getCourseStudyHistoryTimeListByCourseId(QueryCourseStudyHistory query,PageEntity page);
	
	/**
	 * 查看 课程下的学习记录 总人数
	 */
	public int getCourseStudyhistoryCountByCouId(Long courseId);
}