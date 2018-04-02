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
@RequestMapping("/testquestion")
public class TestQuestionController extends BaseController{
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
	public void initBinderTestQuestion(WebDataBinder binder){
		binder.setFieldDefaultPrefix("testQuestion.");
	}


	
}

