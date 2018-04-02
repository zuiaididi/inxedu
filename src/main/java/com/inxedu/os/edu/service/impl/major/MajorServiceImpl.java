package com.inxedu.os.edu.service.impl.major;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.major.MajorDao;
import com.inxedu.os.edu.entity.major.Major;
import com.inxedu.os.edu.entity.major.MajorDto;
import com.inxedu.os.edu.entity.major.QueryMajor;
import com.inxedu.os.edu.service.major.MajorService;
@Service("MajorService")
public class MajorServiceImpl implements MajorService{
	@Autowired
	private MajorDao majorDao;
	
	public int addMajor(Major major){
		return majorDao.addMajor(major);
	}
	
    public Major queryMajorById(int majorId){
        return majorDao.queryMajorById(majorId);	
    }
    
    public MajorDto queryMajorDtoById(int majorId){
        return majorDao.queryMajorDtoById(majorId);	
    }
    
    public List<Major> queryMajors(int collegeId){
        return majorDao.queryMajors(collegeId);	
    }
    
    public int queryMajorsCount(int collegeId){
        return majorDao.queryMajorsCount(collegeId);	
    }
    
    public void updateMajor(Major major){
    	majorDao.updateMajor(major);
    }
    
    public void deleteMajor(int majorId){
    	majorDao.deleteMajor(majorId);
    }
    
    public List<MajorDto> getMajorListPage(QueryMajor query,PageEntity page){
    	return majorDao.getMajorListPage(query, page);
    }
}
