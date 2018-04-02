package com.inxedu.os.edu.entity.college;

import java.io.Serializable;
import lombok.Data;

@Data
public class College implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int collegeId;
	private String collegeName;
}

