package com.inxedu.os.edu.controller.course;


import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.classes.Class;
import com.inxedu.os.edu.entity.classes.QueryClass;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.CourseStudyHistoryDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.course.QueryCourseStudyHistory;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.term.Term;
import com.inxedu.os.edu.entity.website.WebsiteCourse;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import com.inxedu.os.edu.service.course.CourseTeacherService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.term.TermService;
import com.inxedu.os.edu.service.website.WebsiteCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台课程管理
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);
 
	private static final String showCourseList = getViewPath("/admin/course/course_list");//课程列表
	private static final String toAddCourse = getViewPath("/admin/course/add_course");//添加课程
    private static final String showRecommendCourseList = getViewPath("/admin/course/course_recommend_list");//课程列表(推荐课程)
	private static final String update_course=getViewPath("/admin/course/update_course");//更新课程

	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private SubjectService subjectService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private WebsiteCourseService websiteCourseService;
    @Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
    @Autowired
	private TermService termService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryCourse")
	public void initBinderQueryCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCourse.");
	}
	@InitBinder("course")
	public void initBinderCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("course.");
	}
	@InitBinder("queryCourseStudyHistory")
	public void initBinderQueryCourseStudyHistory(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryCourseStudyHistory.");
	}
	
	
	@RequestMapping("/ajax/seekCourse")
	@ResponseBody
	public List<Course> seekCourse(HttpServletRequest request, @ModelAttribute("sysUserId") int sysUserId){
		try {
			List<Course> courseList = courseService.queryCourseBySysUser(sysUserId);
			return courseList;
		} catch (Exception e) {
			logger.error("AdminCourseController.seekCourse()",e);
			setExceptionRequest(request, e);
			return null;
		}
	}
	/**
	 * 课程列表展示
	 */
	@RequestMapping("/cou/list")
	public ModelAndView showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(showCourseList);
			//queryCourse.setIsavaliable(1);//上架
			//查询课程
			List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
			SysUser currentSysUser = SingletonLoginUtils.getLoginSysUser(request);
			model.addObject("currentSysUser",currentSysUser);
			model.addObject("page", page);
			model.addObject("courseList", courseList);
			model.addObject("queryCourse", queryCourse);
			//查询专业
            QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			model.addObject("subjectList", gson.toJson(subjectList));
			//保存 当前请求到session ，下次返回
			request.getSession().setAttribute("courseListUri", WebUtils.getServletRequestUriParms(request));
		} catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("CourseController.showCourseList", e);
		}
		return model;
	}

    /**
     * 到添加课程页面
     */
    @RequestMapping("/cou/toAddCourse")
    public ModelAndView toAddCourse(HttpServletRequest request) {
    	ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(toAddCourse);
            //查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            model.addObject("subjectList", gson.toJson(subjectList));
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("CourseController.toAddCourse", e);
        }
        return model;
    }
    
    /**
     * 添加课程
     * @param request
     * @param course 课程对象
     * @return ModelAndView
     */
    @RequestMapping("/cou/addCourse")
    public ModelAndView addCourse(HttpServletRequest request,@ModelAttribute("course") Course course){
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName("redirect:/admin/cou/list");
    		course.setUserId(Integer.valueOf(SingletonLoginUtils.getLoginSysUserId(request)));
    		course.setAddTime(new Date());
    		course.setUpdateTime(new Date());
    		//course.setIsavaliable(1);//上架
    		courseService.addCourse(course);
    		//添加课程与讲师的关联数据
    		this.addCourseTeacher(request, course);
    	}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
		}
    	return model;
    }
    
    /**
     * 复刻课程
     * @param request
     * @param courseId
     * @param userId
     * @return Map<String,Object>
     */
    @RequestMapping("/cou/copyCourse/{courseId}")
    @ResponseBody
    public Map<String,Object> copyCourse(HttpServletRequest request,@PathVariable("courseId") int courseId){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		Course course = courseService.queryCourseById(courseId);
    		if(course == null){
    			json = this.setJson(false, "课程不存在！", null);
    			return json;
    		}
    		int userId = Integer.valueOf(SingletonLoginUtils.getLoginSysUserId(request));
    		course.setCourseId(0);
    		course.setUserId(userId);
    		course.setAddTime(new Date());
    		course.setUpdateTime(new Date());
    		course.setPageBuycount(0);
    		course.setPageViewcount(0);
    		courseService.addCourse(course);
    		int courseId2 = course.getCourseId();
    		List<CourseKpoint> courseKpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
    		for(CourseKpoint courseKpoint:courseKpointList){
    			if(courseKpoint.getParentId() == 0){
    				int kpointId = courseKpoint.getKpointId();
    				courseKpoint.setKpointId(0);
    				courseKpoint.setCourseId(courseId2);
    				courseKpoint.setAddTime(new Date());
    				courseKpoint.setPlayCount(0);
    				courseKpointService.addCourseKpoint(courseKpoint);
    				int kpointId2 = courseKpoint.getKpointId();
    				for(CourseKpoint _courseKpoint:courseKpointList){
    					if(_courseKpoint.getParentId() == kpointId){
    						_courseKpoint.setKpointId(0);
    						_courseKpoint.setParentId(kpointId2);
    						_courseKpoint.setCourseId(courseId2);
    	    				_courseKpoint.setAddTime(new Date());
    	    				_courseKpoint.setPlayCount(0);
    	    				courseKpointService.addCourseKpoint(_courseKpoint);
    					}
    				}
    			}
    		}
    		json = this.setJson(true, "复刻成功！", null);
    	}catch (Exception e) {
    		this.setAjaxException(json);
			logger.error("copyCourse()--error",e);
		}
    	return json;
    }

    /**
     * 添加课程与讲师的关联数据
     * @param request
     * @param course 课程
     */
	private void addCourseTeacher(HttpServletRequest request, Course course) {
		//先清除之前的数据，再添加
		courseTeacherService.deleteCourseTeacher(course.getCourseId());
		String teacherIds = request.getParameter("teacherIdArr");
		if(teacherIds!=null && teacherIds.trim().length()>0){
			String[] tcIdArr = teacherIds.split(",");
			StringBuffer val = new StringBuffer();
			for(int i=0;i<tcIdArr.length;i++){
				if(i<tcIdArr.length-1){
					val.append("("+course.getCourseId()+","+tcIdArr[i]+"),");
				}else{
					val.append("("+course.getCourseId()+","+tcIdArr[i]+")");
				}
			}
			courseTeacherService.addCourseTeacher(val.toString());
		}
	}
    
    /**
     * 初始化修改页面
     * @param request
     * @param courseId 课程ID
     * @return ModelAndView
     */
    @RequestMapping("/cou/initUpdate/{courseId}")
    public ModelAndView initUpdatePage(HttpServletRequest request,@PathVariable("courseId") int courseId){
    	ModelAndView model = new ModelAndView();
    	try{
    		Course course = courseService.queryCourseById(courseId);
    		int userId = Integer.valueOf(SingletonLoginUtils.getLoginSysUserId(request));
    		if(course.getUserId() == userId || userId == 1){
    			model.setViewName(update_course);
        		model.addObject("course", course);
        		 //查询专业
                QuerySubject querySubject = new QuerySubject();
                List<Subject> subjectList = subjectService.getSubjectList(querySubject);
                model.addObject("subjectList", gson.toJson(subjectList));
                
                //查询课程所属老师
                List<Map<String,Object>> teacherList = teacherService.queryCourseTeacerList(course.getCourseId());
                model.addObject("teacherList", gson.toJson(teacherList));
    		}
    		else{
    			model.setViewName("redirect:/admin/cou/list");
    		}
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("initUpdatePage()---error",e);
		}
    	return model;
    }
    
    /**
     * 修改课程
     * @param request
     * @param course 课程数据
     * @return ModelAndView
     */
    @RequestMapping("/cou/updateCourse")
    public ModelAndView updateCourse(HttpServletRequest request,@ModelAttribute("course") Course course){
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName("redirect:/admin/cou/list");
    		int userId = Integer.valueOf(SingletonLoginUtils.getLoginSysUserId(request));
    		if(course.getUserId() == userId || userId == 1){
    			course.setUpdateTime(new Date());
        		courseService.updateCourse(course);
    			this.addCourseTeacher(request, course);
    		}
    		//获得历史请求 ， 跳转
    		Object uri = request.getSession().getAttribute("courseListUri");
    		if(uri!=null){
    			model.setViewName("redirect:"+uri.toString());
    		}
    		//修改课程与讲师的关联数
    	}catch (Exception e) {
    		model.setViewName(this.setExceptionRequest(request, e));
			logger.error("updateCourse()--error",e);
		}
    	return model;
    }
    
    /**
     * 删除课程
     * @param courseId 课程ID
     * @param type 1正常（上架） 2删除（下架）
     * @return Map<String,Object>
     */
    @RequestMapping("/cou/deleteCourse/{courseId}")
    @ResponseBody
    public Map<String,Object> deleteCourse(HttpServletRequest request,@PathVariable("courseId") int courseId){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		Course course = courseService.queryCourseById(courseId);
    		int userId = Integer.valueOf(SingletonLoginUtils.getLoginSysUserId(request));
    		if(course.getUserId() == userId || userId == 1){
    			List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
    			if(kpointList != null && kpointList.size()>0){
    				json = this.setJson(false, "存在子节点！", null);
    			}
    			else{
    				courseService.deleteCourse(courseId);
        			json = this.setJson(true, "删除成功！", null);
    			}
    		}
    		else{
    			json = this.setJson(false, "没有此权限！", null);
    		}
    	}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("deleteCourse()--error",e);
		}
    	return json;
    }

    /**
	 * 查询课程观看历史
	 * @param request
	 * @param courseID 
	 * @param page 分页条件
	 * @return
	 */
	@RequestMapping("/cou/lookcoursehistory/{courseId1}")
	public ModelAndView lookCourseHistory(HttpServletRequest request,@PathVariable("courseId1") int courseId1,@ModelAttribute("queryCourseStudyHistory") QueryCourseStudyHistory query,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
    		int userId = Integer.valueOf(SingletonLoginUtils.getLoginSysUserId(request));
    		Course course = null;
    		if(courseId1 != 0){
    			course = courseService.queryCourseById(courseId1);
    			if(course != null){
    				query.setTeacherId(course.getUserId());
    				query.setCourseId(courseId1);
    			}				
			}
    		else{
    			courseId1 = query.getCourseId();
    			course = courseService.queryCourseById(courseId1);
    		}
    		if(course == null){
    			model.setViewName(getViewPath("/admin/course/course-history"));
    			model.addObject("historyList", null);
				model.addObject("page", page);
				return model;
    		}
    		if(course.getUserId() == userId || userId == 1){
				model.setViewName(getViewPath("/admin/course/course-history"));
				if(query.getGradeTermId() == -1){
					Term term = termService.checkTerm(new Date());
					if(term != null){
						query.setTermId(term.getTermId());
						query.setGradeTermId(term.getGradeId());
					}
					else{
						query.setTermId(0);
						query.setGradeTermId(0);
					}
				}
				List<CourseStudyHistoryDto> historyList = courseStudyhistoryService.getCourseStudyHistoryTimeListByCourseId(query, page);
				model.addObject("historyList", historyList);
				model.addObject("page", page);
			}
			else{
				model.setViewName("redirect:/admin/user/getuserList");
			}
			
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("lookHistory()---error",e);
		}
		return model;
	}
    
    /**
     * 推荐选择课程列表
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/cou/showrecommendList")
    public ModelAndView showRecommendCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse){
    	ModelAndView model = new ModelAndView();
    	try{
    		model.setViewName(showRecommendCourseList);
    		//查询课程
    		//queryCourse.setIsavaliable(1);//上架
			List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
			model.addObject("page", page);
			model.addObject("courseList", courseList);
			model.addObject("queryCourse", queryCourse);
			//查询专业
            QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			model.addObject("subjectList", gson.toJson(subjectList));
			
			//查询推荐分类
			List<WebsiteCourse> webstieList = websiteCourseService.queryWebsiteCourseList();
			model.addObject("webstieList", webstieList);
    	}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showRecommendCourseList()--error",e);
		}
    	return model;
    }
}