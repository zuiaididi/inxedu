package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestGrade implements Serializable{
	private static final long serialVersionUID = 1L;
	/**测验分数ID*/
	private int testGradeId;
	/**测验提交ID*/
	private int testSubmitId;
	/**测验添加评分时间*/
	private Date testCreateGradeTime;
	/**测验更新评分时间*/
	private Date testUpdateGradeTime;
	/**测验分数*/
	private int testGrade;
	/**测验评价*/
	private String testComment;
	/**测验分值*/
	private int testScore;
}
