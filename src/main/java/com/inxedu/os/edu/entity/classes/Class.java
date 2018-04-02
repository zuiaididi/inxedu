package com.inxedu.os.edu.entity.classes;

import java.io.Serializable;
import lombok.Data;

@Data
public class Class implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int classId;
	private String className;
	private int gradeId;
	private int majorId;
}

