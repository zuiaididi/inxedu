package com.inxedu.os.edu.entity.test;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.inxedu.os.common.util.StringUtils;

/**
 * 测验
 * @author www.inxedu.com
 */
@Data
public class Test implements Serializable{
	private static final long serialVersionUID = 1L;
	/**测验ID*/
	private int testId;
	/**测验题目*/
	private String testTitle;
	/**测验分值*/
	private int testScore;
	/**测验创建时间*/
	private Date testCreateTime;
	/**测验更新时间*/
	private Date testUpdateTime;
	/**测验开始时间*/
	private Date testStartTime;
	/**测验结束时间*/
	private Date testEndTime;
	/**老师ID*/
	private int teacherId;
	/**课程ID*/
	private int courseId;
	/**测验序号*/
	private int testSort;
	/**课程名称*/
	private String courseName;
	/**上传者名称*/
	private String teacherName;
	/**问题数目*/
	private int questionNumber;
	/**学期ID*/
	private int termId;
	/**学期名*/
	private String termName;
	/**学年名*/
	private String gradeName;
	
	private String testStartTimeFormat;//时间 格式化显示
	private String testEndTimeFormat;//时间 格式化显示
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void setTestStartTime(Date date){
        this.testStartTime=date;
        this.testStartTimeFormat= sdf.format(this.getTestStartTime());
    }
	public void setTestEndTime(Date date){
        this.testEndTime=date;
        this.testEndTimeFormat= sdf.format(this.getTestEndTime());
    }
}
