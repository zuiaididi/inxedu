package com.inxedu.os.edu.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;



/**
 * 学员与老师
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserTeacherDto extends UserTeacher implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**老师名*/
	private String teacherName;
	
	/**学年名*/
	private String gradeName;
	
	/**学期名*/
	private String termName;
	
	/**课程名*/
	private String courseName;
	
	
	
}
