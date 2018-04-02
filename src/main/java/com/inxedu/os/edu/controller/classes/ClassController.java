package com.inxedu.os.edu.controller.classes;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.service.classes.ClassService;
import com.inxedu.os.edu.service.major.MajorService;
import com.inxedu.os.edu.service.system.SysUserService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.user.UserTeacherService;
import com.inxedu.os.edu.entity.classes.Class;
import com.inxedu.os.edu.entity.classes.ClassDto;
import com.inxedu.os.edu.entity.classes.QueryClass;
import com.inxedu.os.edu.entity.major.Major;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.UserTeacher;

@Controller
@RequestMapping("/admin/class")
public class ClassController extends BaseController{
	@Autowired
	private ClassService classService; 
	@Autowired
	private UserService userService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private UserTeacherService userTeacherService;
	private Logger logger = LoggerFactory.getLogger(ClassController.class);
	
	@InitBinder({"queryClass"})
	public void initQueryUser(WebDataBinder dinder){
		dinder.setFieldDefaultPrefix("queryClass.");
	}
	
	@InitBinder({"clazz"})
	public void initBinderClass(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("clazz.");
	}
	
	
	@RequestMapping("/ajax/seekClasses")
	@ResponseBody
	public List<Class> seekClasses(HttpServletRequest request, @ModelAttribute("majorId") int majorId, @ModelAttribute("gradeId") int gradeId){
		try {
			QueryClass query = new QueryClass();
			query.setGradeId(gradeId);
			query.setMajorId(majorId);
			List<Class> classes = classService.queryClasses(query);
			return classes;
		} catch (Exception e) {
			logger.error("ClassController.seekClasses()",e);
			setExceptionRequest(request, e);
			return null;
		}
	}
	
	@RequestMapping("/getclassList")
	public ModelAndView queryClassList(HttpServletRequest request,@ModelAttribute("queryClass")QueryClass queryClass,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/organization/class-list"));
			List<ClassDto> classList = classService.getClassListPage(queryClass, page);
			model.addObject("classList", classList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryClassList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/getClassByMajor/{majorId}")
	public ModelAndView getClassByMajor(HttpServletRequest request,@PathVariable("majorId") int majorId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/organization/class-list"));
			QueryClass queryClass = new QueryClass();
			Major major = majorService.queryMajorById(majorId);
			if(major == null){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			queryClass.setCollegeId(major.getCollegeId());
			queryClass.setMajorId(majorId);
			queryClass.setGradeId(0);
			queryClass.setClassId(0);
			List<ClassDto> classList = classService.getClassListPage(queryClass, page);
			model.addObject("queryClass", queryClass);
			model.addObject("classList", classList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryClassList()--error",e);
		}
		return model;
	}
	
	@RequestMapping("/delete/{clazzId}")
	@ResponseBody
	public Map<String,Object> deleteClassById(HttpServletRequest request,@PathVariable("clazzId") int clazzId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int number = userService.getClassNumber(clazzId);
			if(number > 0){
				json = this.setJson(false, "该班级存在学员！", null);
			}
			else{
				classService.deleteClass(clazzId);
				json = this.setJson(true, "删除成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("deleteClassById()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/initupdateclass/{clazzId}")
	@ResponseBody
	public Map<String,Object> initUpdateClass(@PathVariable("clazzId") int clazzId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Class clazz= classService.queryClassById(clazzId);
			json = this.setJson(true, null, clazz);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateClass()--error",e);
		}
		return json;
	}
	
	@RequestMapping("/updateclass")
	@ResponseBody
	public Map<String,Object> updateClass(HttpServletRequest request,@ModelAttribute("clazz")Class clazz){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(clazz.getClassId() > 0){
				if(clazz.getClassName() == ""){
					json = this.setJson(false, "名称不能为空！", null);
				}
				else{
					classService.updateClass(clazz);
					json = this.setJson(true, "修改成功！", null);
				}
			}
			else{
				json = this.setJson(false, "修改失败！", null);
			}
		}catch (Exception e) {
			
			logger.error("updateClass()--error",e);
		}
		return json;
	}
	@RequestMapping("/createclass")
	@ResponseBody
	public Map<String,Object> createClass(HttpServletRequest request,@ModelAttribute("clazz")Class clazz){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(clazz.getClassName() == ""){
				json = this.setJson(false, "名称不能为空！", null);
			}
			else if(clazz.getMajorId() == 0){
            	json = this.setJson(false, "请选择专业！", null);
			}
			else if(clazz.getGradeId() == 0){
            	json = this.setJson(false, "请选择年级！", null);
			}
			else{
				classService.addClass(clazz);
				json = this.setJson(true, "添加成功！", null);
			}
		}catch (Exception e) {
			
			logger.error("createClass()--error",e);
		}
		return json;
	}
	/**
	 * 班级添加老师
	 */
	@RequestMapping("/addteacher")
	@ResponseBody
	public Map<String,Object> addTeacher(HttpServletRequest request,@RequestParam("sysId") int sysId,
			@RequestParam("classId") int classId,@RequestParam("gradeId") int gradeId,
			@RequestParam("termId") int termId,@RequestParam("courseId") int courseId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(classId == 0){
				json = this.setJson(false, "班级不存在！", null);
		    	return json;
			}
			if(sysId == 0){
				json = this.setJson(false, "请选择老师！", null);
		    	return json;
			}
			if(gradeId == 0){
				json = this.setJson(false, "请选择学年！", null);
		    	return json;
			}
			if(termId == 0){
				json = this.setJson(false, "请选择学期！", null);
		    	return json;
			}
			if(courseId == 0){
				json = this.setJson(false, "请选择课程！", null);
		    	return json;
			}
			QueryUser query = new QueryUser();
			query.setClassId(classId);
			query.setSysId(1);
			List<User> userList = userService.queryUserList(query);
			if(userList == null || userList.size() == 0){
				json = this.setJson(false, "不存在学员！", null);
		    	return json;
			}
			UserTeacher userTeacher = new UserTeacher();
			userTeacher.setTermId(termId);
			userTeacher.setTeacherId(sysId);
			userTeacher.setCourseId(courseId);
			for(User user:userList){
				userTeacher.setUserId(user.getUserId());
				userTeacher.setId(0);
				int flag = userTeacherService.checkUserTeacher2(userTeacher);
				if(flag == 0){
					userTeacherService.createUserTeacher(userTeacher);
				}
			}
			json = this.setJson(true, "设置成功！", null);
		}catch (Exception e) {
			
			logger.error("addUser()--error",e);
		}
		return json;
	}
}
