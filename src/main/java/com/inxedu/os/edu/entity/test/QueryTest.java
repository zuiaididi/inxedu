package com.inxedu.os.edu.entity.test;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 查询用户
 * @author www.inxedu.com
 *
 */
@Data
public class QueryTest {
	private int testId;
	private int courseId;
	private int teacherId;
	private int status=0;//0全部，1未开始，2正在进行，3已经结束
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date nowTime;//当前时间
	private int userId;
	private int gradeTermId = -1;
	private int termId;
}
