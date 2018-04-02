package com.inxedu.os.edu.entity.user;

import lombok.Data;

import java.io.Serializable;


/**
 * 学员与老师
 * @author www.inxedu.com
 */
@Data
public class QueryUserTeacher implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**学员ID*/
	private int userId;
	
	/**老师ID*/
	private int teacherId;
	
	/**学期ID*/
	private int termId;
	
	/**课程ID*/
	private int courseId;
	
	private int status;//0 其他学期 ，1 当前学期
	
}
