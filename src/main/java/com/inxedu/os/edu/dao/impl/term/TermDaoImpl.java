package com.inxedu.os.edu.dao.impl.term;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.term.TermDao;
import com.inxedu.os.edu.entity.term.Term;

import java.util.List;

import org.springframework.stereotype.Repository;
/**
 * @author www.inxedu.com
 *
 */
@Repository("TermDao")
public class TermDaoImpl extends GenericDaoImpl implements TermDao {

	
	public int addTerm(Term term) {
		this.insert("TermMapper.createTerm", term);
		return term.getTermId();
	}
	
	public Term queryTermById(int termId) {
		return this.selectOne("TermMapper.queryTermById", termId);
	}
	
	public List<Term> queryTerms(int gradeId) {
		return this.selectList("TermMapper.queryTerms", gradeId);
	}
	
	public int queryTermsCount(int gradeId) {
		return this.selectOne("TermMapper.queryTermsCount", gradeId);
	}

    public int checkTermsCount(Term term){
    	return this.selectOne("TermMapper.checkTermsCount", term);
    }
    
    public Term checkTerm(Term term){
    	return this.selectOne("TermMapper.checkTerm", term);
    }
    
	public void updateTerm(Term term) {
		this.update("TermMapper.updateTerm", term);
	}
	
	public void deleteTerm(int termId){
		this.delete("TermMapper.deleteTerm", termId);
	}
	
	public List<Term> getTermListPage(Term query,PageEntity page){
		return this.queryForListPage("TermMapper.getTermListPage", query, page);
	}
}
