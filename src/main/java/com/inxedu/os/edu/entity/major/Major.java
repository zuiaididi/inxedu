package com.inxedu.os.edu.entity.major;

import java.io.Serializable;
import lombok.Data;

@Data
public class Major implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int majorId;
	private String majorName;
	private int collegeId;
}

