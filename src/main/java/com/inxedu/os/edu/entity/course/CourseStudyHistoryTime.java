package com.inxedu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseStudyHistoryTime implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Long historyId;
	private java.util.Date time;

}
