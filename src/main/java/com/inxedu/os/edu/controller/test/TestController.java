package com.inxedu.os.edu.controller.test;

import com.inxedu.os.common.cache.EHCacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.service.email.EmailService;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FileUploadUtils;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.letter.MsgReceive;
import com.inxedu.os.edu.entity.letter.QueryMsgReceive;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.Test;
import com.inxedu.os.edu.entity.test.TestGrade;
import com.inxedu.os.edu.entity.test.TestQuestion;
import com.inxedu.os.edu.entity.test.TestQuestionDto;
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

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

/**
 * 前台学员  Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/front/test")
public class TestController extends BaseController{
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
	
	@InitBinder("test")
	public void initBinderTest(WebDataBinder binder){
		binder.setFieldDefaultPrefix("test.");
	}
	@InitBinder("queryTest")
	public void initBinderQueryTest(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryTest.");
	}
    
	/**
     * 测验列表展示
     */
    @RequestMapping("/testlist")
    public ModelAndView testList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryTest") QueryTest queryTest) {
        ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(getViewPath("/web/test/test-list"));
            // 页面传来的数据放到page中
        	page.setPageSize(12);
        	int userId = SingletonLoginUtils.getLoginUserId(request);
        	if(userId == 0){
        		model.addObject("testList", null);
                model.addObject("page",page);
                model.addObject("queryTest", queryTest);
        		return model;
        	}
            queryTest.setUserId(userId);
            queryTest.setNowTime(new Date());
            queryTest.setTeacherId(-1);
            queryTest.setCourseId(-1);
            if(queryTest.getStatus() == 0 || queryTest.getStatus() == 1){
            	queryTest.setStatus(2);
            }
            // 搜索课程列表
            List<Test> testList = testService.queryTestListPage(queryTest, page);
            model.addObject("currentTime", new Date());
            model.addObject("testList", testList);
            model.addObject("page",page);
            model.addObject("queryTest", queryTest);
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("testList()--error", e);
        }
        return model;
    }

    /**
     * 测验详情
     */
    @RequestMapping("/testinfo/{testId}")
    public ModelAndView testInfo(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable("testId") int testId) {
        ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(getViewPath("/web/test/test-info"));
            // 页面传来的数据放到page中
        	//page.setPageSize(12);
        	//int userId = SingletonLoginUtils.getLoginUserId(request);
            // 搜索课程列表
        	int userId = SingletonLoginUtils.getLoginUserId(request);
        	if(userId == 0){
        		model.setViewName("redirect:/front/test/testlist");
        		return model;
        	}
        	QueryTest query = new QueryTest();
        	query.setUserId(userId);
        	query.setTestId(testId);
        	query.setTeacherId(-1);
        	query.setCourseId(-1);
        	List<Test> testList = testService.queryTestListPage(query, page);
        	if(testList == null || testList.size() == 0){
        		model.setViewName("redirect:/front/test/testlist");
        		return model;
        	}
        	Test test = testList.get(0);
        	if(test.getTestStartTime().compareTo(new Date()) > 0){
        		model.setViewName("redirect:/front/test/testlist");
        		return model;
        	}
        	else if(test.getTestEndTime().compareTo(new Date()) < 0){
        		model.addObject("isok", 0);
        	}
        	else{
        		model.addObject("isok", 1);
        	}
        	TestSubmit queryTestSubmit = new TestSubmit();
        	
        	queryTestSubmit.setTestId(testId);
        	queryTestSubmit.setUserId(userId);
        	TestSubmit testSubmit = testSubmitService.queryTestSubmitByTestIdAndUserId(queryTestSubmit);
			if(testSubmit == null){
				model.addObject("testSubmit", null);
				model.addObject("testGrade", null);
				testSubmit = new TestSubmit();
				testSubmit.setTestId(testId);
				testSubmit.setTestSubmitId(0);
			}
			else{
				model.addObject("testSubmit", testSubmit);
				TestGrade testGrade = testGradeService.queryTestGradeByTestSubmitId(testSubmit.getTestSubmitId());
				if(testGrade == null){
					model.addObject("testGrade", null);
				}
				else{
					model.addObject("testGrade", testGrade);
				}
			}
            List<TestQuestionDto> testQuestionDtoList = testQuestionService.queryTestQuestionDtoListBySubmit(testSubmit);
            model.addObject("testQuestionDtoList", testQuestionDtoList);
            model.addObject("test", test);
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("testInfo()--error", e);
        }
        return model;
    }
    /**
     * 测验成绩
     */
    @RequestMapping("/testgrade/{testId}")
    public ModelAndView testList(HttpServletRequest request, @PathVariable("testId") int testId) {
        ModelAndView model = new ModelAndView();
        try {
        	model.setViewName(getViewPath("/web/test/test-grade"));
        	model.addObject("testId",testId);
        	int userId = SingletonLoginUtils.getLoginUserId(request);
        	if(userId == 0){
            	model.addObject("testGrade", null);
            	model.addObject("testQuestionDtoList", null);
            	return model;
            }
        	TestSubmit query = new TestSubmit();
        	query.setTestId(testId);
        	query.setUserId(userId);
            TestSubmit testSubmit = testSubmitService.queryTestSubmitByTestIdAndUserId(query);
            if(testSubmit==null){
            	model.addObject("testGrade", null);
            	model.addObject("testQuestionDtoList", null);
            	return model;
            }
            TestGrade testGrade = testGradeService.queryTestGradeByTestSubmitId(testSubmit.getTestSubmitId());
            if(testGrade == null){
            	model.addObject("testGrade", null);
            	model.addObject("testQuestionDtoList", null);
            	return model;
            }
            TestSubmit queryTestSubmit = new TestSubmit();
        	queryTestSubmit.setTestId(testId);
        	queryTestSubmit.setUserId(userId);
        	List<TestQuestionDto> testQuestionDtoList = testQuestionService.queryTestQuestionDtoListBySubmit(testSubmit);
            model.addObject("testQuestionDtoList", testQuestionDtoList);
            model.addObject("testGrade", testGrade);
        } catch (Exception e) {
        	model.setViewName(this.setExceptionRequest(request, e));
            logger.error("testList()--error", e);
        }
        return model;
    }
    /**
     * 上传测验答案
     */
    @RequestMapping("/testupload/{questionId}")
	public ModelAndView testUpload(HttpServletRequest request,HttpServletResponse response, @PathVariable("questionId") int questionId, @RequestParam("testFile") MultipartFile testFile) {
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName(getViewPath("/web/test/upload-result"));
			int userId = SingletonLoginUtils.getLoginUserId(request);
			if(userId == 0){
				model.addObject("status", "上传失败，请登录！");
				return model;
			}
			TestQuestion testQuestion = testQuestionService.queryTestQuestionByQuestionId(questionId);
			if(testQuestion == null){
				model.addObject("status", "问题不存在，上传失败！");
				return model;
			}
			QueryTest query = new QueryTest();
        	query.setUserId(userId);
        	query.setTestId(testQuestion.getTestId());
        	query.setTeacherId(-1);
        	query.setCourseId(-1);
        	PageEntity page = new PageEntity();
        	List<Test> testList = testService.queryTestListPage(query, page);
        	if(testList == null || testList.size() == 0){
				model.addObject("status", "测验不存在，上传失败！");
				return model;
			}
        	Test test = testList.get(0);
        	Date date = new Date();
        	if(date.compareTo(test.getTestStartTime())<0){
        		model.addObject("status", "测验未开始，上传失败！");
				return model;
        	}
            if(date.compareTo(test.getTestEndTime())>0){
            	model.addObject("status", "测验已结束，上传失败！");
				return model;
        	}
			String path = request.getSession().getServletContext().getRealPath("/"); 
			String filePath = "/testfile/upload/";
			filePath+="/"+ DateUtils.toString(new Date(), "yyyyMMdd")+"/"+System.currentTimeMillis()+ userId +"/第"+testQuestion.getQuestionSort()+"题"+testFile.getOriginalFilename().substring(testFile.getOriginalFilename().lastIndexOf("."));
			filePath = path + filePath;
			if(testFile.isEmpty()){
				model.addObject("status", "请选择文件！");
				return model;
			}
			File file = new File(filePath);
			//如果目录不存在，则创建
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			//保存文件
			testFile.transferTo(file);
			createTestSubmit(userId,testQuestion.getTestId(),questionId,filePath);
			//返回数据
			model.addObject("status", "上传成功！");
			return model;
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
            logger.error("testUpload()--error", e);
		}
    	return model;
    }
    private void createTestSubmit(int userId,int testId,int questionId,String filePath){
    	TestSubmit query = new TestSubmit();
    	TestQuestionSubmit query2 = new TestQuestionSubmit();
    	int testSubmitId;
    	query.setTestId(testId);
    	query.setUserId(userId);
    	query2.setUserId(userId);
    	query2.setQuestionId(questionId);
        TestSubmit testSubmit = testSubmitService.queryTestSubmitByTestIdAndUserId(query);
        TestQuestionSubmit testQuestionSubmit = testQuestionSubmitService.queryTestQuestionSubmitByQuestionIdAndUserId(query2);
        if(testSubmit == null){
        	query.setTestSubmitTime(new Date());
        	testSubmitService.createTestSubmit(query);
        	testSubmitId = query.getTestSubmitId();
        }
        else{
        	testSubmitId = testSubmit.getTestSubmitId();
        }
        if(testQuestionSubmit == null){
        	query2.setQuestionSubmitAnswer(filePath);
        	query2.setQuestionSubmitTime(new Date());
        	query2.setTestSubmitId(testSubmitId);
        	testQuestionSubmitService.createTestQuestionSubmit(query2);
        }
        else{
        	testQuestionSubmit.setQuestionSubmitAnswer(filePath);
        	testQuestionSubmit.setQuestionSubmitTime(new Date());
        	testQuestionSubmitService.updateTestQuestionSubmit(testQuestionSubmit);
        }
    }
    
    /**
     * 下载测验答案
     */
    @RequestMapping("/testdownload/{questionId}")
	public ResponseEntity<byte[]> testDownload(HttpServletRequest request,@PathVariable("questionId") int questionId) {
		try {
			int userId = SingletonLoginUtils.getLoginUserId(request);
			if(userId == 0){
				return null;
			}
			TestQuestionSubmit query = new TestQuestionSubmit();
			query.setQuestionId(questionId); 
			query.setUserId(userId);
			TestQuestionSubmit testQuestionSubmit = testQuestionSubmitService.queryTestQuestionSubmitByQuestionIdAndUserId(query);
			if(testQuestionSubmit == null){
				return null;
			}
			File file = new File(testQuestionSubmit.getQuestionSubmitAnswer());
			if(!file.exists()){
				return null;
			}
			String filename = file.getName();
			HttpHeaders headers = new HttpHeaders();  
		    //下载显示的文件名，解决中文名称乱码问题  
		    String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
		    //通知浏览器以attachment（下载方式）打开图片
		    headers.setContentDispositionFormData("attachment", downloadFielName); 
		    //application/octet-stream ： 二进制流数据（最常见的文件下载）。
		    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
		            headers, HttpStatus.CREATED); 
		}catch (Exception e) {
        	logger.error("testDownload()--error", e);
        	return null;
		}
    }

    
}

