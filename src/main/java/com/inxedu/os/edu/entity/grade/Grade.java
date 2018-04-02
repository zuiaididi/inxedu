package com.inxedu.os.edu.entity.grade;

import java.io.Serializable;
import lombok.Data;

@Data
public class Grade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int gradeId;
	private String gradeName;
}

