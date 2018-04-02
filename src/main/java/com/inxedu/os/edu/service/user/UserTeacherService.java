package com.inxedu.os.edu.service.user;

import java.util.List;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.user.QueryUserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacherDto;


/**
 * 前台用户
 * @author www.inxedu.com
 *
 */
public interface UserTeacherService {
	/**
	 * 创建
	 * @param userTeacher
	 * @return 返回ID
	 */
	public int createUserTeacher(UserTeacher userTeacher);
	
	public int checkUserTeacher(UserTeacher userTeacher);
	public int checkUserTeacher2(UserTeacher userTeacher);
	
	public List<UserTeacherDto> queryUserTeacher(QueryUserTeacher query, PageEntity page);
	
}
