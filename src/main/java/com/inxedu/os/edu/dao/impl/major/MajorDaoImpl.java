package com.inxedu.os.edu.dao.impl.major;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.major.MajorDao;
import com.inxedu.os.edu.entity.major.Major;
import com.inxedu.os.edu.entity.major.MajorDto;
import com.inxedu.os.edu.entity.major.QueryMajor;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * @author www.inxedu.com
 *
 */
@Repository("MajorDao")
public class MajorDaoImpl extends GenericDaoImpl implements MajorDao {

	
	public int addMajor(Major major) {
		this.insert("MajorMapper.createMajor", major);
		return major.getMajorId();
	}
	
	public Major queryMajorById(int majorId) {
		return this.selectOne("MajorMapper.queryMajorById", majorId);
	}
	
	public MajorDto queryMajorDtoById(int majorId) {
		return this.selectOne("MajorMapper.queryMajorDtoById", majorId);
	}
	
	public List<Major> queryMajors(int collegeId) {
		return this.selectList("MajorMapper.queryMajors", collegeId);
	}
	
	public int queryMajorsCount(int collegeId) {
		return this.selectOne("MajorMapper.queryMajorsCount", collegeId);
	}
	
	public void updateMajor(Major major) {
		this.update("MajorMapper.updateMajor", major);
	}
	
	public void deleteMajor(int majorId){
		this.delete("MajorMapper.deleteMajor", majorId);
	}
	
	public List<MajorDto> getMajorListPage(QueryMajor query,PageEntity page){
		return this.queryForListPage("MajorMapper.getMajorListPage", query, page);
	}
}
