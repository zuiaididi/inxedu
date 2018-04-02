package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestQuestionGrade implements Serializable{
	private static final long serialVersionUID = 1L;
	/**问题分数ID*/
	private int questionGradeId;
	/**测验分数ID*/
	private int testGradeId;
	/**问题提交ID*/
	private int questionSubmitId;
	/**问题添加分数时间*/
	private Date questionCreateGradeTime;
	/**问题更改分数时间*/
	private Date questionUpdateGradeTime;
	/**问题分数*/
	private int questionGrade;
	/**问题评价*/
	private String questionComment;
	/**问题分值*/
	private int questionScore;
	/**问题序号*/
	private int questionSort;
	/**学员学号*/
	private String studentNumber;
	/**学员姓名*/
	private String userName;
	/**问题ID*/
	private int questionId;
}
