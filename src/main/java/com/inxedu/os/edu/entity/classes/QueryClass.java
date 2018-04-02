package com.inxedu.os.edu.entity.classes;

import lombok.Data;

/**
 * 查询用户
 * @author www.inxedu.com
 *
 */
@Data
public class QueryClass {
	private String keyWord;
	private int collegeId;
	private int majorId;
	private int gradeId;
	private int classId;
}
