package com.inxedu.os.edu.dao.term;

import java.util.List;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.term.Term;

public interface TermDao {
	
	public int addTerm(Term term);
	
    public Term queryTermById(int termId);
    
    public List<Term> queryTerms(int gradeId);
    
    public int queryTermsCount(int gradeId);
    
    public int checkTermsCount(Term term);
    
    public Term checkTerm(Term term);
    
    public void updateTerm(Term term);
    
    public void deleteTerm(int termId);
    
    public List<Term> getTermListPage(Term query,PageEntity page);
}
