package com.inxedu.os.edu.service.major;

import java.util.List;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.major.Major;
import com.inxedu.os.edu.entity.major.MajorDto;
import com.inxedu.os.edu.entity.major.QueryMajor;

public interface MajorService {
	
	public int addMajor(Major major);
	
    public Major queryMajorById(int majorId);
    
    public MajorDto queryMajorDtoById(int majorId);
    
    public List<Major> queryMajors(int collegeId);
    
    public int queryMajorsCount(int collegeId);
    
    public void updateMajor(Major major);
    
    public void deleteMajor(int majorId);
    
    public List<MajorDto> getMajorListPage(QueryMajor query,PageEntity page);
}
