package com.inxedu.os.edu.controller.major;

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
import com.inxedu.os.edu.service.major.MajorService;
import com.inxedu.os.edu.entity.major.MajorDto;
import com.inxedu.os.edu.entity.major.QueryMajor;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.grade.Grade;
import com.inxedu.os.edu.entity.major.Major;

@Controller
@RequestMapping("/admin/major")
public class MajorController extends BaseController{
	@Autowired
	private MajorService majorService; 
	@Autowired
	private GradeService gradeService;
	@Autowired
	private ClassService classService; 
	private Logger logger = LoggerFactory.getLogger(MajorController.class);
	
	@InitBinder({"queryMajor"})
	public void initQueryUser(WebDataBinder dinder){
		dinder.setFieldDefaultPrefix("queryMajor.");
	}
	@InitBinder({"major"})
	public void initBinderMajor(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("major.");
	}
	
	@RequestMapping("/ajax/seekMajors")
	@ResponseBody
	public List<Major> seekMajors(HttpServletRequest request,@ModelAttribute("collegeId") int collegeId){
		try {
			List<Major> majors = majorService.queryMajors(collegeId);
			return majors;
		} catch (Exception e) {
			logger.error("majorController.seekMajors()",e);
			setExceptionRequest(request, e);
			return null;
		}
	}
	
	@RequestMapping("/getmajorList")
	public ModelAndView queryMajorList(HttpServletRequest request,@ModelAttribute("queryMajor")QueryMajor queryMajor,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/organization/major-list"));
			List<MajorDto> majorList = majorService.getMajorListPage(queryMajor, page);
			List<Grade> gradeList = gradeService.queryGrades();
			model.addObject("gradeList", gradeList);
			model.addObject("majorList", majorList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryMajorList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/getMajorByCollege/{collegeId}")
	public ModelAndView queryMajorList(HttpServletRequest request,@PathVariable("collegeId") int collegeId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/organization/major-list"));
			QueryMajor queryMajor = new QueryMajor();
			queryMajor.setCollegeId(collegeId);
			queryMajor.setMajorId(0);
			List<MajorDto> majorList = majorService.getMajorListPage(queryMajor, page);
			List<Grade> gradeList = gradeService.queryGrades();
			model.addObject("gradeList", gradeList);
			model.addObject("queryMajor", queryMajor);
			model.addObject("majorList", majorList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryMajorList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/delete/{majorId}")
	@ResponseBody
	public Map<String,Object> deleteMajorById(HttpServletRequest request,@PathVariable("majorId") int majorId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int number = classService.queryClassByMajorCount(majorId);
			if(number > 0){
				json = this.setJson(false, "该专业存在班级！", null);
			}
			else{
				majorService.deleteMajor(majorId);
				json = this.setJson(true, "删除成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("deleteMajorById()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/initupdatemajor/{majorId}")
	@ResponseBody
	public Map<String,Object> initUpdateMajor(@PathVariable("majorId") int majorId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Major major= majorService.queryMajorById(majorId);
			json = this.setJson(true, null, major);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateMajor()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/updatemajor")
	@ResponseBody
	public Map<String,Object> updateMajor(HttpServletRequest request,@ModelAttribute("major")Major major){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(major.getMajorId() > 0){
				if(major.getMajorName() == ""){
					json = this.setJson(false, "名称不能为空！", null);
				}
				else{
					majorService.updateMajor(major);
					json = this.setJson(true, "修改成功！", null);
				}
			}
			else{
				json = this.setJson(false, "修改失败！", null);
			}
		}catch (Exception e) {
			
			logger.error("updateMajor()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/createmajor")
	@ResponseBody
	public Map<String,Object> createMajor(HttpServletRequest request,@ModelAttribute("major")Major major){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(major.getMajorName() == ""){
				json = this.setJson(false, "名称不能为空！", null);
			}
            else if(major.getCollegeId() == 0){
            	json = this.setJson(false, "请选择学院！", null);
			}
			else{
				majorService.addMajor(major);
				json = this.setJson(true, "添加成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("createMajor()--error",e);
		}
		return json;
	}

}
