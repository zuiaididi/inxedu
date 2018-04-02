package com.inxedu.os.edu.service.college;

import java.util.List;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.college.College;
import com.inxedu.os.edu.entity.college.CollegeDto;
import com.inxedu.os.edu.entity.college.QueryCollege;

public interface CollegeService {
	
	public int addCollege(College college);
	
    public College queryCollegeById(int collegeId);
    
    public List<College> queryColleges();
    
    public int queryCollegesCount();
    
    public void updateCollege(College college);
    
    public void deleteCollege(int collegeId);
    
    public List<CollegeDto> getCollegeListPage(QueryCollege query,PageEntity page);
}
