package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestQuestionDto implements Serializable{
	private static final long serialVersionUID = 1L;
	/**问题ID*/
	private int questionId;
	/**问题内容*/
	private String questionContent;
	/**测验ID*/
	private int testId;
	/**问题分值*/
	private int questionScore;
	/**问题创建时间*/
	private Date questionCreateTime;
	/**问题更新时间*/
	private Date questionUpdateTime;
	/**问题序号*/
	private int questionSort;
	/**问题提交ID*/
	private int questionSubmitId;
	/**测验提交ID*/
	private int testSubmitId;
	/**问题提交时间*/
	private Date questionSubmitTime;
	/**问题提交答案*/
	private String questionSubmitAnswer;
	/**问题分数ID*/
	private int questionGradeId;
	/**测验分数ID*/
	private int testGradeId;
	/**问题添加分数时间*/
	private Date questionCreateGradeTime;
	/**问题更改分数时间*/
	private Date questionUpdateGradeTime;
	/**问题分数*/
	private int questionGrade;
	/**问题评价*/
	private String questionComment;
	/**学员学号*/
	private String studentNumber;
	/**学员姓名*/
	private String userName;
}
