package com.inxedu.os.edu.entity.term;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Term implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int termId;
	private String termName;
	private int gradeId;
	private String gradeName;
	/**学期开始时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date termStartTime;
	/**学期结束时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date termEndTime;
	private String termStartTimeFormat;//时间 格式化显示
	private String termEndTimeFormat;//时间 格式化显示
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public void setTermStartTime(Date date){
        this.termStartTime=date;
        this.termStartTimeFormat= sdf.format(this.getTermStartTime());
    }
	public void setTermEndTime(Date date){
        this.termEndTime=date;
        this.termEndTimeFormat= sdf.format(this.getTermEndTime());
    }
}

