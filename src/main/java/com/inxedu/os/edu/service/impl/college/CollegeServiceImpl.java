package com.inxedu.os.edu.service.impl.college;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.college.CollegeDao;
import com.inxedu.os.edu.entity.college.College;
import com.inxedu.os.edu.entity.college.CollegeDto;
import com.inxedu.os.edu.entity.college.QueryCollege;
import com.inxedu.os.edu.service.college.CollegeService;
@Service("collegeService")
public class CollegeServiceImpl implements CollegeService{
	@Autowired
	private CollegeDao collegeDao;
	
	public int addCollege(College college){
		return collegeDao.addCollege(college);
	}
	
    public College queryCollegeById(int collegeId){
        return collegeDao.queryCollegeById(collegeId);	
    }
    
    public List<College> queryColleges(){
        return collegeDao.queryColleges();	
    }
    
    public int queryCollegesCount(){
        return collegeDao.queryCollegesCount();	
    }
    
    public void updateCollege(College college){
    	collegeDao.updateCollege(college);
    }
    
    public void deleteCollege(int collegeId){
    	collegeDao.deleteCollege(collegeId);
    }
    
    public List<CollegeDto> getCollegeListPage(QueryCollege query,PageEntity page){
    	return collegeDao.getCollegeListPage(query, page);
    }
}
