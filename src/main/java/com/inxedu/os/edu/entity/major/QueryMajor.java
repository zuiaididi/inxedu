package com.inxedu.os.edu.entity.major;

import lombok.Data;

/**
 * 查询用户
 * @author www.inxedu.com
 *
 */
@Data
public class QueryMajor {
	private String keyWord;
	private int collegeId;
	private int majorId;
}
