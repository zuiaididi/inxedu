package com.inxedu.os.edu.service.impl.user;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.user.UserTeacherDao;
import com.inxedu.os.edu.entity.user.QueryUserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacherDto;
import com.inxedu.os.edu.service.user.UserTeacherService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 前台学员
 * @author www.inxedu.com
 *
 */
@Service("userTeacherService")
public class UserTeacherServiceImpl implements UserTeacherService{

	@Autowired
	private UserTeacherDao userTeacherDao;
	

	public int createUserTeacher(UserTeacher userTeacher) {
		return userTeacherDao.createUserTeacher(userTeacher);
	}

	public int checkUserTeacher(UserTeacher userTeacher){
		return userTeacherDao.checkUserTeacher(userTeacher);
	}
	public int checkUserTeacher2(UserTeacher userTeacher){
		return userTeacherDao.checkUserTeacher2(userTeacher);
	}
	
	public List<UserTeacherDto> queryUserTeacher(QueryUserTeacher query, PageEntity page){
		return userTeacherDao.queryUserTeacher(query, page);
	}
}
