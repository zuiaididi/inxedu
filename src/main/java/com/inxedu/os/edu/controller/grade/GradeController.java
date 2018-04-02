package com.inxedu.os.edu.controller.grade;

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
import com.inxedu.os.edu.service.classes.ClassService;
import com.inxedu.os.edu.service.grade.GradeService;
import com.inxedu.os.edu.service.term.TermService;
import com.inxedu.os.edu.entity.grade.Grade;
import com.inxedu.os.edu.entity.grade.QueryGrade;

@Controller
@RequestMapping("/admin/grade")
public class GradeController extends BaseController{
	@Autowired
	private GradeService gradeService; 
	@Autowired
	private ClassService classService; 
	@Autowired
	private TermService termService; 
	private Logger logger = LoggerFactory.getLogger(GradeController.class);
	
	@InitBinder({"queryGrade"})
	public void initQueryUser(WebDataBinder dinder){
		dinder.setFieldDefaultPrefix("queryGrade.");
	}
	@InitBinder({"grade"})
	public void initBinderGrade(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("grade.");
	}
	
	@RequestMapping("/ajax/seekGrades")
	@ResponseBody
	public List<Grade> seekGrades(HttpServletRequest request){
		try {
			List<Grade> grades = gradeService.queryGrades();
			return grades;
		} catch (Exception e) {
			logger.error("gradeController.seekGrades()",e);
		    setExceptionRequest(request, e);
			return null;
		}
	}
	
	@RequestMapping("/getgradeList")
	public ModelAndView queryGradeList(HttpServletRequest request,@ModelAttribute("queryGrade")QueryGrade queryGrade,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/grade/grade-list"));
			List<Grade> gradeList = gradeService.getGradeListPage(queryGrade, page);
			model.addObject("gradeList", gradeList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryGradeList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/delete/{gradeId}")
	@ResponseBody
	public Map<String,Object> deleteGradeById(HttpServletRequest request,@PathVariable("gradeId") int gradeId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int number = classService.queryClassByGradeCount(gradeId);
			int number2 = termService.queryTermsCount(gradeId);
			if(number > 0){
				json = this.setJson(false, "该年级存在班级！", null);
			}
			else if(number2 > 0){
				json = this.setJson(false, "该学年存在学期！", null);
			}
			else{
				gradeService.deleteGrade(gradeId);
				json = this.setJson(true, "删除成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("deleteGradeById()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/initupdategrade/{gradeId}")
	@ResponseBody
	public Map<String,Object> initUpdateGrade(@PathVariable("gradeId") int gradeId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Grade grade= gradeService.queryGradeById(gradeId);
			json = this.setJson(true, null, grade);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateGrade()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/updategrade")
	@ResponseBody
	public Map<String,Object> updateGrade(HttpServletRequest request,@ModelAttribute("grade")Grade grade){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(grade.getGradeId() > 0){
				if(grade.getGradeName() == ""){
					json = this.setJson(false, "名称不能为空！", null);
				}
				else{
					gradeService.updateGrade(grade);
					json = this.setJson(true, "修改成功！", null);
				}
			}
			else{
				json = this.setJson(false, "修改失败！", null);
			}
		}catch (Exception e) {
			
			logger.error("updateGrade()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/creategrade")
	@ResponseBody
	public Map<String,Object> createGrade(HttpServletRequest request,@ModelAttribute("grade")Grade grade){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(grade.getGradeName() == ""){
				json = this.setJson(false, "名称不能为空！", null);
			}
			else{
				gradeService.addGrade(grade);
				json = this.setJson(true, "添加成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("createGrade()--error",e);
		}
		return json;
	}

}
