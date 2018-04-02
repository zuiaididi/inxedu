package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.course.CourseStudyhistoryDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryDto;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryTime;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.course.QueryCourseStudyHistory;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyHistoryTimeService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * CourseStudyhistory 用户课程学下记录 service接口实现
 * @author www.inxedu.com
 */
@Service("courseStudyhistoryService")
public class CourseStudyhistoryServiceImpl implements CourseStudyhistoryService {

	@Autowired
	private CourseStudyhistoryDao courseStudyhistoryDao;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private CourseStudyHistoryTimeService courseStudyHistoryTimeService;

	/**
	 * 添加CourseStudyhistory
	 * 
	 * @param courseStudyhistory
	 *            要添加的CourseStudyhistory
	 * @return id
	 */
	public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
		return courseStudyhistoryDao.addCourseStudyhistory(courseStudyhistory);
	}

	/**
	 * 添加播放记录和播放次数
	 */
	public void playertimes(CourseStudyhistory courseStudyhistory) {
		Course course = courseService.queryCourseById(courseStudyhistory.getCourseId().intValue());
		// 判断课程不为空
		if (ObjectUtils.isNull(course)) {
			return;
		}
		// 判断节点不为空
		CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(courseStudyhistory.getKpointId().intValue());
		if (ObjectUtils.isNull(courseKpoint)) {
			return;
		}
		
		//CourseStudyhistory tempHistory =  new CourseStudyhistory();
		//tempHistory.setUserId(courseStudyhistory.getUserId());
		//tempHistory.setCourseId(courseStudyhistory.getCourseId());
		courseKpoint.setPlayCount(courseKpoint.getPlayCount() + 1);
		courseKpointService.updateKpoint(courseKpoint);
		// 查询是否添加过记录
		List<CourseStudyhistory> courseStudyhistoryList = getCourseStudyhistoryList(courseStudyhistory);
		CourseStudyHistoryTime courseStudyHistoryTime = new CourseStudyHistoryTime();
		if (ObjectUtils.isNull(courseStudyhistoryList)) {
			// 如果没有添加过则添加记录
			// 填充数据
			courseStudyhistory.setKpointName(courseKpoint.getName());
			courseStudyhistory.setCourseName(course.getCourseName());
			courseStudyhistory.setUpdateTime(new Date());
			courseStudyHistoryTime.setTime(new Date());
			courseStudyhistory.setPlayercount(1L);
			addCourseStudyhistory(courseStudyhistory);
			courseStudyHistoryTime.setHistoryId(courseStudyhistory.getId());
		} else {
			// 如果添加过则更新记录
			CourseStudyhistory courseStudy = courseStudyhistoryList.get(0);
			courseStudy.setKpointName(courseKpoint.getName());
			courseStudy.setCourseName(course.getCourseName());
			courseStudy.setUpdateTime(new Date());
			// 更新时间记录存字段
			courseStudyHistoryTime.setTime(new Date());
			courseStudy.setPlayercount(courseStudy.getPlayercount() + 1);
			updateCourseStudyhistory(courseStudy);
			courseStudyHistoryTime.setHistoryId(courseStudy.getId());
		}
		courseStudyHistoryTimeService.addCourseStudyHistoryTime(courseStudyHistoryTime);
		
	}

	/**
	 * 根据id删除一个CourseStudyhistory
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCourseStudyhistoryById(Long id) {
		courseStudyHistoryTimeService.deleteCourseStudyHistoryTimeByHistory(id);
		courseStudyhistoryDao.deleteCourseStudyhistoryById(id);
	}
	public void deleteCourseStudyhistoryByIds(String ids) {
		courseStudyHistoryTimeService.deleteCourseStudyHistoryTimeByHistories(ids);
		courseStudyhistoryDao.deleteCourseStudyhistoryByIds(ids);
	}
	public void deleteCourseStudyhistoryByUserId(Long userId) {
		courseStudyHistoryTimeService.deleteCourseStudyHistoryTimeByUserId(userId);
		courseStudyhistoryDao.deleteCourseStudyhistoryByUserId(userId);
	}
	public void deleteCourseStudyhistoryByCourseId(Long courseId) {
		courseStudyHistoryTimeService.deleteCourseStudyHistoryTimeByCourseId(courseId);
		courseStudyhistoryDao.deleteCourseStudyhistoryByUserId(courseId);
	}
	public void deleteCourseStudyhistoryByKpointId(Long kpointId) {
		courseStudyHistoryTimeService.deleteCourseStudyHistoryTimeByKpointId(kpointId);
		courseStudyhistoryDao.deleteCourseStudyhistoryByUserId(kpointId);
	}
	/**
	 * 修改CourseStudyhistory
	 * 
	 * @param courseStudyhistory
	 *            要修改的CourseStudyhistory
	 */
	public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
		courseStudyhistoryDao.updateCourseStudyhistory(courseStudyhistory);
	}

	/**
	 * 根据条件获取CourseStudyhistory列表
	 * 
	 * @param courseStudyhistory
	 *            查询条件
	 * @return List<CourseStudyhistory>
	 */
	public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory) {
		return courseStudyhistoryDao.getCourseStudyhistoryList(courseStudyhistory);
	}

	public List<CourseStudyhistory> getCourseStudyhistoryListByCouId(Long courseId) {
		return courseStudyhistoryDao.getCourseStudyhistoryListByCouId(courseId);
	}

	public List<CourseStudyHistoryDto> getCourseStudyhistoryListByUserId(int userId,PageEntity page){
		return courseStudyhistoryDao.getCourseStudyhistoryListByUserId(userId,page);
	}
	
	public List<CourseStudyHistoryDto> getCourseStudyhistoryListByCourseId(int courseId,PageEntity page){
		return courseStudyhistoryDao.getCourseStudyhistoryListByCourseId(courseId,page);
	}
	
	 public List<CourseStudyHistoryDto> getCourseStudyHistoryTimeListByUserId(QueryCourseStudyHistory query,PageEntity page){
	    return courseStudyhistoryDao.getCourseStudyHistoryTimeListByUserId(query,page);
     }
		
	public List<CourseStudyHistoryDto> getCourseStudyHistoryTimeListByCourseId(QueryCourseStudyHistory query,PageEntity page){
		return courseStudyhistoryDao.getCourseStudyHistoryTimeListByCourseId(query,page);
	}
	
	public int getCourseStudyhistoryCountByCouId(Long courseId) {
		return courseStudyhistoryDao.getCourseStudyhistoryCountByCouId(courseId);
	}
	
}