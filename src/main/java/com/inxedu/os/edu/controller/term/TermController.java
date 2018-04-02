package com.inxedu.os.edu.controller.term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.service.grade.GradeService;
import com.inxedu.os.edu.service.term.TermService;
import com.inxedu.os.edu.entity.term.Term;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.grade.Grade;

@Controller
@RequestMapping("/admin/term")
public class TermController extends BaseController{
	@Autowired
	private TermService termService; 
	@Autowired
	private GradeService gradeService; 
	private Logger logger = LoggerFactory.getLogger(TermController.class);
	
	@InitBinder({"term"})
	public void initBinderTerm(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("term.");
	}
	@InitBinder({"queryTerm"})
	public void initBinderQueryTerm(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTerm.");
	}
	
	@RequestMapping("/ajax/seekTerms")
	@ResponseBody
	public List<Term> seekTerms(HttpServletRequest request,@ModelAttribute("gradeId") int gradeId){
		try {
			List<Term> terms = termService.queryTerms(gradeId);
			return terms;
		} catch (Exception e) {
			logger.error("termController.seekTerms()",e);
			setExceptionRequest(request, e);
			return null;
		}
	}
	
	@RequestMapping("/gettermList")
	public ModelAndView queryTermList(HttpServletRequest request,@ModelAttribute("queryTerm")Term queryTerm,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/grade/term-list"));
			List<Term> termList = termService.getTermListPage(queryTerm, page);
			model.addObject("termList", termList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryTermList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/getTermByGrade/{gradeId}")
	public ModelAndView queryTermList(HttpServletRequest request,@PathVariable("gradeId") int gradeId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/grade/term-list"));
			Term queryTerm = new Term();
			queryTerm.setGradeId(gradeId);
			queryTerm.setTermId(0);
			List<Term> termList = termService.getTermListPage(queryTerm, page);
			model.addObject("queryTerm", queryTerm);
			model.addObject("termList", termList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryTermList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/delete/{termId}")
	@ResponseBody
	public Map<String,Object> deleteTermById(HttpServletRequest request,@PathVariable("termId") int termId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			termService.deleteTerm(termId);
			json = this.setJson(true, "删除成功！", null);
		}catch (Exception e) {
			
			logger.error("deleteTermById()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/initupdateterm/{termId}")
	@ResponseBody
	public Map<String,Object> initUpdateTerm(@PathVariable("termId") int termId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Term term= termService.queryTermById(termId);
			json = this.setJson(true, null, term);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateTerm()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/updateterm")
	@ResponseBody
	public Map<String,Object> updateTerm(HttpServletRequest request,@ModelAttribute("term")Term term){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(term.getTermId() > 0){
				if(term.getTermName() == ""){
					json = this.setJson(false, "名称不能为空！", null);
				}
				else if(term.getTermStartTime() == null){
					json = this.setJson(false, "开始时间称不能为空！", null);
				}
				else if(term.getTermEndTime() == null){
					json = this.setJson(false, "结束时间不能为空！", null);
				}
				else if(term.getTermEndTime().compareTo(term.getTermStartTime()) < 0){
					json = this.setJson(false, "开始时间不能大于结束时间！", null);
				}
				else if(termService.checkTermsCount(term) > 0){
					json = this.setJson(false, "时间段与其他学期重复！", null);
				}
				else{
					termService.updateTerm(term);
					json = this.setJson(true, "修改成功！", null);
				}
			}
			else{
				json = this.setJson(false, "修改失败！", null);
			}
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("updateTerm()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/createterm")
	@ResponseBody
	public Map<String,Object> createTerm(HttpServletRequest request,@ModelAttribute("term")Term term){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(term.getTermName() == ""){
				json = this.setJson(false, "名称不能为空！", null);
			}
            else if(term.getGradeId() == 0){
            	json = this.setJson(false, "请选择学年！", null);
			}
            else if(term.getTermStartTime() == null){
				json = this.setJson(false, "开始时间称不能为空！", null);
			}
			else if(term.getTermEndTime() == null){
				json = this.setJson(false, "结束时间不能为空！", null);
			}
			else if(term.getTermEndTime().compareTo(term.getTermStartTime()) < 0){
				json = this.setJson(false, "开始时间不能大于结束时间！", null);
			}
			else if(termService.checkTermsCount(term) > 0){
				json = this.setJson(false, "时间段与其他学期重复！", null);
			}
			else{
				termService.addTerm(term);
				json = this.setJson(true, "添加成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("createTerm()--error",e);
		}
		return json;
	}

}
