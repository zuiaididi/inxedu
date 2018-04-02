package com.inxedu.os.edu.service.impl.term;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.term.TermDao;
import com.inxedu.os.edu.entity.term.Term;
import com.inxedu.os.edu.service.term.TermService;
@Service("TermService")
public class TermServiceImpl implements TermService{
	@Autowired
	private TermDao termDao;
	
	public int addTerm(Term term){
		return termDao.addTerm(term);
	}
	
    public Term queryTermById(int termId){
        return termDao.queryTermById(termId);	
    }
    
    public List<Term> queryTerms(int gradeId){
        return termDao.queryTerms(gradeId);	
    }
    
    public int queryTermsCount(int gradeId){
        return termDao.queryTermsCount(gradeId);	
    }

    public int checkTermsCount(Term term){
    	return termDao.checkTermsCount(term);
    }
    
    public Term checkTerm(Date date){
    	Term term = new Term();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		term.setTermStartTime(date);
		term.setTermEndTime(date);
    	return termDao.checkTerm(term);
    }
    public void updateTerm(Term term){
    	termDao.updateTerm(term);
    }
    
    public void deleteTerm(int termId){
    	termDao.deleteTerm(termId);
    }
    
    public List<Term> getTermListPage(Term query,PageEntity page){
    	return termDao.getTermListPage(query, page);
    }
}
