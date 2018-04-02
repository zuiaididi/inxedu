package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestGradeDto implements Serializable{
	private static final long serialVersionUID = 1L;
	/**测验分数ID*/
	private int testGradeId;
	/**测验提交ID*/
	private int testSubmitId;
	/**测验评分添加时间*/
	private Date testCreateGradeTime;
	/**测验评分更新时间*/
	private Date testUpdateGradeTime;
	/**测验分数*/
	private int testGrade;
	/**测验评价*/
	private String testComment;
	/**测验分值*/
	private int testScore;
	/**学员ID*/
	private int userId;
	/**测验ID*/
	private int testId;
	/**测验提交时间*/
	private Date testSubmitTime;
	/**学员学号*/
	private String studentNumber;
	/**学员姓名*/
	private String userName;
	/**测验题目*/
	private String testTitle;
	/**老师姓名*/
	private String teacherName;
	/**课程名称*/
	private String courseName;
	/**问题提交成绩列表*/
	private List<TestQuestionDto> testQuestionDtoList;
}
