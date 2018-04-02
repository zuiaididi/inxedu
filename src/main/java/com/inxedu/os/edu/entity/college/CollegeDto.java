package com.inxedu.os.edu.entity.college;

import java.io.Serializable;
import lombok.Data;

@Data
public class CollegeDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int collegeId;
	private String collegeName;
}

