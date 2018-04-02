package com.inxedu.os.edu.dao.impl.college;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.college.CollegeDao;
import com.inxedu.os.edu.entity.college.College;
import com.inxedu.os.edu.entity.college.CollegeDto;
import com.inxedu.os.edu.entity.college.QueryCollege;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * @author www.inxedu.com
 *
 */
@Repository("CollegeDao")
public class CollegeDaoImpl extends GenericDaoImpl implements CollegeDao {

	
	public int addCollege(College college) {
		this.insert("CollegeMapper.createCollege", college);
		return college.getCollegeId();
	}
	
	public College queryCollegeById(int collegeId) {
		return this.selectOne("CollegeMapper.queryCollegeById", collegeId);
	}
	
	public List<College> queryColleges() {
		return this.selectList("CollegeMapper.queryColleges","");
	}
	
	public int queryCollegesCount() {
		return this.selectOne("CollegeMapper.queryCollegesCount","");
	}
	
	public void updateCollege(College college) {
		this.update("CollegeMapper.updateCollege", college);
	}
	
	public void deleteCollege(int collegeId){
		this.delete("CollegeMapper.deleteCollege", collegeId);
	}
	
	public List<CollegeDto> getCollegeListPage(QueryCollege query,PageEntity page){
		return this.queryForListPage("CollegeMapper.getCollegeListPage", query, page);
	}
}
