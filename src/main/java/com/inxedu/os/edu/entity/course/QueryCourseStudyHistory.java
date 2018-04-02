package com.inxedu.os.edu.entity.course;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 查询观看历史
 * @author www.inxedu.com
 *
 */
@Data
public class QueryCourseStudyHistory {
	private String keyWord;
	private int courseId;
	private int teacherId;
	private int userId;
	private int collegeId;
	private int majorId;
	private int gradeId;
	private int classId;
	private int gradeTermId = -1;
	private int termId;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginTime;//查询 开始注册时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;//查询 结束注册时间
}
