package com.inxedu.os.edu.controller.college;

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
import com.inxedu.os.edu.service.college.CollegeService;
import com.inxedu.os.edu.service.major.MajorService;
import com.inxedu.os.edu.entity.college.College;
import com.inxedu.os.edu.entity.college.CollegeDto;
import com.inxedu.os.edu.entity.college.QueryCollege;

@Controller
@RequestMapping("/admin/college")
public class CollegeController extends BaseController{
	@Autowired
	private CollegeService collegeService; 
	@Autowired
	private MajorService majorService; 
	private Logger logger = LoggerFactory.getLogger(CollegeController.class);
	
	@InitBinder({"queryCollege"})
	public void initQueryUser(WebDataBinder dinder){
		dinder.setFieldDefaultPrefix("queryCollege.");
	}
	@InitBinder({"college"})
	public void initBinderCollege(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("college.");
	}
	
	@RequestMapping("/ajax/seekColleges")
	@ResponseBody
	public  List<College> seekColleges(HttpServletRequest request){
		try {
			List<College> colleges = collegeService.queryColleges();
			return colleges;
		} catch (Exception e) {
			logger.error("CollegeController.seekCollegees()",e);
			setExceptionRequest(request, e);
			return null;
		}
		
	}
	
	@RequestMapping("/getcollegeList")
	public ModelAndView queryCollegeList(HttpServletRequest request,@ModelAttribute("queryCollege")QueryCollege queryCollege,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/organization/college-list"));
			List<CollegeDto> collegeList = collegeService.getCollegeListPage(queryCollege, page);
			model.addObject("collegeList", collegeList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryCollegeList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/delete/{collegeId}")
	@ResponseBody
	public Map<String,Object> deleteCollegeById(HttpServletRequest request,@PathVariable("collegeId") int collegeId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int number = majorService.queryMajorsCount(collegeId);
			if(number > 0){
				json = this.setJson(false, "该学院存在专业！", null);
			}
			else{
				collegeService.deleteCollege(collegeId);
				json = this.setJson(true, "删除成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("deleteMajorById()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/initupdatecollege/{collegeId}")
	@ResponseBody
	public Map<String,Object> initUpdateCollege(@PathVariable("collegeId") int collegeId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			College college= collegeService.queryCollegeById(collegeId);
			json = this.setJson(true, null, college);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateCollege()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/updatecollege")
	@ResponseBody
	public Map<String,Object> updateCollege(HttpServletRequest request,@ModelAttribute("college")College college){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(college.getCollegeId() > 0){
				if(college.getCollegeName() == ""){
					json = this.setJson(false, "名称不能为空！", null);
				}
				else{
					collegeService.updateCollege(college);
					json = this.setJson(true, "修改成功！", null);
				}
			}
			else{
				json = this.setJson(false, "修改失败！", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateCollege()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/createcollege")
	@ResponseBody
	public Map<String,Object> createCollege(HttpServletRequest request,@ModelAttribute("college")College college){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(college.getCollegeName() == ""){
				json = this.setJson(false, "名称不能为空！", null);
			}
			else{
				collegeService.addCollege(college);
				json = this.setJson(true, "添加成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("createCollege()--error",e);
		}
		return json;
	}

}
