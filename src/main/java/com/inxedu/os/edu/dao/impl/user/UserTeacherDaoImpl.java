package com.inxedu.os.edu.dao.impl.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.user.UserTeacherDao;
import com.inxedu.os.edu.entity.user.QueryUserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacherDto;

/**
 * @author www.inxedu.com
 *
 */
@Repository("userTeacherDao")
public class UserTeacherDaoImpl extends GenericDaoImpl implements UserTeacherDao{
	/**
	 * 创建
	 * @param userTeacher
	 * @return 返回ID
	 */
	public int createUserTeacher(UserTeacher userTeacher){
		this.insert("UserTeacherMapper.createUserTeacher", userTeacher);
		return userTeacher.getId();
	}
	
	public int checkUserTeacher(UserTeacher userTeacher){
		return this.selectOne("UserTeacherMapper.checkUserTeacher", userTeacher);
	}
	public int checkUserTeacher2(UserTeacher userTeacher){
		return this.selectOne("UserTeacherMapper.checkUserTeacher2", userTeacher);
	}
	
	public List<UserTeacherDto> queryUserTeacher(QueryUserTeacher query, PageEntity page){
		return this.queryForListPage("UserTeacherMapper.queryUserTeacher", query, page);
	}
	
}
