package com.inxedu.os.edu.entity.user;

import lombok.Data;

import java.io.Serializable;


/**
 * 学员与老师
 * @author www.inxedu.com
 */
@Data
public class UserTeacher implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	/**学员ID*/
	private int userId;
	
	/**老师ID*/
	private int teacherId;
	
	/**学期ID*/
	private int termId;
	
	/**课程ID*/
	private int courseId;
	
}
