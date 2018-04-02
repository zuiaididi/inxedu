package com.inxedu.os.edu.controller.test;

import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.service.email.EmailService;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.letter.MsgReceive;
import com.inxedu.os.edu.entity.letter.QueryMsgReceive;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.Test;
import com.inxedu.os.edu.entity.test.TestQuestion;
import com.inxedu.os.edu.entity.test.TestQuestionGrade;
import com.inxedu.os.edu.entity.test.TestQuestionSubmit;
import com.inxedu.os.edu.entity.test.TestSubmit;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import com.inxedu.os.edu.service.course.CourseFavoritesService;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.test.TestGradeService;
import com.inxedu.os.edu.service.test.TestQuestionGradeService;
import com.inxedu.os.edu.service.test.TestQuestionService;
import com.inxedu.os.edu.service.test.TestQuestionSubmitService;
import com.inxedu.os.edu.service.test.TestService;
import com.inxedu.os.edu.service.test.TestSubmitService;
import com.inxedu.os.edu.service.user.UserLoginLogService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.NumberFormat;
import java.util.*;

/**
 * 前台学员  Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin/testquestion")
public class AdminTestQuestionController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;
	@Autowired
	private TestSubmitService testSubmitService;
	@Autowired
	private TestGradeService testGradeService;
	@Autowired
	private TestQuestionService testQuestionService;
	@Autowired
	private TestQuestionSubmitService testQuestionSubmitService;
	@Autowired
	private TestQuestionGradeService testQuestionGradeService;
	
	@InitBinder("testQuestion")
	public void initBinderTest(WebDataBinder binder){
		binder.setFieldDefaultPrefix("testQuestion.");
	}

	/**
	 * 查看测验问题
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTestQuestionList/{testId}")
	public ModelAndView queryTestQuestionList(HttpServletRequest request,@PathVariable("testId") int testId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Test test = testService.queryTestByTestId(testId);
			if(test == null){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			model.setViewName(getViewPath("/admin/test/test-question-list"));
			TestQuestion query = new TestQuestion();
			query.setTestId(testId);
			List<TestQuestion>testQuestionList = testQuestionService.queryTestQuestionListPage(query, page);
			model.addObject("testId", testId);
			model.addObject("testQuestionList", testQuestionList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryTestQuestionList()---error",e);
		}
		return model;
	}
	
	/**
	 * 添加测验问题
	 * @param request
	 * @return
	 */
	@RequestMapping("/addTestQuestion")
	@ResponseBody
	public Map<String,Object> addTestQuestion(HttpServletRequest request,@ModelAttribute("testQuestion")TestQuestion testQuestion){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Test test = testService.queryTestByTestId(testQuestion.getTestId());
			if(test == null){
				json = this.setJson(false, "添加失败", null);
				return json;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			testQuestion.setQuestionCreateTime(new Date());
			testQuestion.setQuestionUpdateTime(new Date());
			testQuestionService.createTestQuestion(testQuestion);
			json = this.setJson(true, "添加成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("addTestQuestion()---error",e);
		}
		return json;
	}
	
	
	@RequestMapping("/initUpdateTestQuestion/{questionId}")
	@ResponseBody
	public Map<String,Object> initUpdateTestQuestion(HttpServletRequest request,@PathVariable("questionId") int questionId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			TestQuestion testQuestion = testQuestionService.queryTestQuestionByQuestionId(questionId);
			if(testQuestion == null){
				json = this.setJson(false, "修改失败", null);
				return json;
			}
			Test test = testService.queryTestByTestId(testQuestion.getTestId());
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			json = this.setJson(true, null, testQuestion);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateTestQuestion()--error",e);
		}
		return json;
	}
	/**
	 * 修改测验问题
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateTestQuestion")
	@ResponseBody
	public Map<String,Object> updateTestQuestion(HttpServletRequest request,@ModelAttribute("testQuestion")TestQuestion testQuestion){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			TestQuestion testQuestion2 = testQuestionService.queryTestQuestionByQuestionId(testQuestion.getQuestionId());
			if(testQuestion2 == null){
				json = this.setJson(false, "修改失败", null);
				return json;
			}
			Test test = testService.queryTestByTestId(testQuestion2.getTestId());
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			testQuestion.setQuestionUpdateTime(new Date());
			testQuestionService.updateTestQuestion(testQuestion);
			json = this.setJson(true, "修改成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateTestQuestion()--error",e);
		}
		return json;
	}
	/**
	 * 删除测验问题
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteTestQuestion/{questionId}")
	@ResponseBody
	public Map<String,Object> deleteTest(HttpServletRequest request,@PathVariable("questionId") int questionId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			TestQuestion testQuestion = testQuestionService.queryTestQuestionByQuestionId(questionId);
			if(testQuestion == null){
				json = this.setJson(false, "修改失败", null);
				return json;
			}
			Test test = testService.queryTestByTestId(testQuestion.getTestId());
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			testQuestionService.deleteTestQuestion(questionId);
			json = this.setJson(true, "删除成功！", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("deleteTestQuestion()---error",e);
		}
		return json;
	}
	
	
	/**
	 * 查看测验问题成绩情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTestQuestionGradeList/{questionId}")
	public ModelAndView queryTestQuestionGradeList(HttpServletRequest request,@PathVariable("questionId") int questionId,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			TestQuestion testQuestion = testQuestionService.queryTestQuestionByQuestionId(questionId);
			if(testQuestion == null){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			Test test = testService.queryTestByTestId(testQuestion.getTestId());
			if(sysId != 1 && sysId != test.getTeacherId()){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			model.setViewName(getViewPath("/admin/test/question-grade-list"));
			TestQuestionGrade query = new TestQuestionGrade();
			query.setQuestionId(questionId);
			List<TestQuestionGrade>testQuestionGradeList = testQuestionGradeService.queryTestQuestionGradeListPage(query, page);
			model.addObject("question", testQuestion);
			model.addObject("testQuestionGradeList", testQuestionGradeList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryTestQuestionGradeList()---error",e);
		}
		return model;
	}
	
}

