package com.inxedu.os.edu.entity.test;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 查询用户
 * @author www.inxedu.com
 *
 */
@Data
public class QueryTestSubmitGrade {
	private int testId;
	private int courseId;
	private int teacherId;
	private int status=0;//0全部，1已经打分，2已经提交未打分，3未提交
	private int collegeId;
	private int majorId;
	private int gradeId;
	private int classId;
	private int gradeTermId;
	private int termId;
}
