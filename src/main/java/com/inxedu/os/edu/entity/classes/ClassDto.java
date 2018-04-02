package com.inxedu.os.edu.entity.classes;

import java.io.Serializable;
import lombok.Data;

@Data
public class ClassDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int classId;
	private String className;
	private int gradeId;
	private String gradeName;
	private int majorId;
	private String majorName;
	private int collegeId;
	private String collegeName;
}

