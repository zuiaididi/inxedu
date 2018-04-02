package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestQuestion implements Serializable{
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
	
}
