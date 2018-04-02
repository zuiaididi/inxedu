package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestQuestionSubmit implements Serializable{
	private static final long serialVersionUID = 1L;
	/**问题提交ID*/
	private int questionSubmitId;
	/**测验提交ID*/
	private int testSubmitId;
	/**问题ID*/
	private int questionId;
	/**问题提交时间*/
	private Date questionSubmitTime;
	/**问题提交答案*/
	private String questionSubmitAnswer;
	/**学员ID*/
	private int userId;
	
}
