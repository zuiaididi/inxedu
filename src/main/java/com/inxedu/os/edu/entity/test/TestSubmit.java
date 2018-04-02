package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class TestSubmit implements Serializable{
	private static final long serialVersionUID = 1L;
	/**测验提交ID*/
	private int testSubmitId;
	/**学员ID*/
	private int userId;
	/**测验ID*/
	private int testId;
	/**测验提交时间*/
	private Date testSubmitTime;
	
}
