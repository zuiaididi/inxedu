package com.inxedu.os.edu.controller.test;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.course.*;
import com.inxedu.os.edu.entity.grade.Grade;
import com.inxedu.os.edu.entity.term.Term;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.QueryTestSubmitGrade;
import com.inxedu.os.edu.entity.test.Test;
import com.inxedu.os.edu.entity.test.TestGrade;
import com.inxedu.os.edu.entity.test.TestGradeDto;
import com.inxedu.os.edu.entity.test.TestQuestion;
import com.inxedu.os.edu.entity.test.TestQuestionDto;
import com.inxedu.os.edu.entity.test.TestQuestionGrade;
import com.inxedu.os.edu.entity.test.TestQuestionSubmit;
import com.inxedu.os.edu.entity.test.TestSubmit;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.term.TermService;
import com.inxedu.os.edu.service.test.TestGradeService;
import com.inxedu.os.edu.service.test.TestQuestionGradeService;
import com.inxedu.os.edu.service.test.TestQuestionService;
import com.inxedu.os.edu.service.test.TestQuestionSubmitService;
import com.inxedu.os.edu.service.test.TestService;
import com.inxedu.os.edu.service.test.TestSubmitService;
import com.inxedu.os.edu.service.user.UserService;

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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 前台学员  Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin/test")
public class AdminTestController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(AdminTestController.class);

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
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private TermService termService;
	
	@InitBinder("test")
	public void initBinderTest(WebDataBinder binder){
		binder.setFieldDefaultPrefix("test.");
	}
	@InitBinder("queryTest")
	public void initBinderQueryTest(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryTest.");
	}
	@InitBinder("testGradeDto")
	public void initBinderTestGradeDto(WebDataBinder binder){
		binder.setFieldDefaultPrefix("testGradeDto.");
	}
	@InitBinder("testQuestionDto")
	public void initBinderTestQuestionDto(WebDataBinder binder){
		binder.setFieldDefaultPrefix("testQuestionDto.");
	}
	@InitBinder("queryTestSubmitGrade")
	public void initBinderQueryTestSubmitGrade(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryTestSubmitGrade.");
	}
	/**
	 * 查看测验
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTestList/{courseId1}")
	public ModelAndView queryTestList(HttpServletRequest request,@PathVariable("courseId1") int courseId1,@ModelAttribute("queryTest")QueryTest queryTest,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Course course = null;
    		if(courseId1 != 0){
    			course = courseService.queryCourseById(courseId1);
    			if(course != null){
    				queryTest.setTeacherId(course.getUserId());
    				queryTest.setCourseId(courseId1);
    			}				
			}
    		else{
    			courseId1 = queryTest.getCourseId();
    			course = courseService.queryCourseById(courseId1);
    		}
    		if(course == null){
    			model.setViewName(getViewPath("/admin/test/test-list"));
    			model.addObject("testList", null);
				model.addObject("page", page);
				model.addObject("courseId", courseId1);
				return model;
    		}
			if(sysId != 1 && sysId != course.getUserId()){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			model.setViewName(getViewPath("/admin/test/test-list"));
			queryTest.setNowTime(new Date());
			if(queryTest.getGradeTermId() == -1){
				Term term = termService.checkTerm(new Date());
				if(term != null){
					queryTest.setTermId(term.getTermId());
					queryTest.setGradeTermId(term.getGradeId());
				}
				else{
					queryTest.setTermId(0);
					queryTest.setGradeTermId(0);
				}
			}
			List<Test>testList = testService.queryTestListPage(queryTest, page);
			model.addObject("courseId", courseId1);
			model.addObject("testList", testList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryTestList()---error",e);
		}
		return model;
	}
	
	/**
	 * 添加测验
	 * @param request
	 * @return
	 */
	@RequestMapping("/addTest")
	@ResponseBody
	public Map<String,Object> addTest(HttpServletRequest request,@ModelAttribute("test")Test test){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Course course = courseService.queryCourseById(test.getCourseId());
			if(course == null){
				json = this.setJson(false, "添加失败", null);
				return json;
			}
			if(sysId != 1 && sysId != course.getUserId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			test.setTeacherId(sysId);
			test.setTestCreateTime(new Date());
			test.setTestUpdateTime(new Date());
			Term term = termService.checkTerm(new Date());
			test.setTermId(term.getTermId());
			testService.createTest(test);
			json = this.setJson(true, "添加成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("addTest()---error",e);
		}
		return json;
	}
	
	
	@RequestMapping("/initUpdateTest/{testId}")
	@ResponseBody
	public Map<String,Object> initUpdateTest(HttpServletRequest request,@PathVariable("testId") int testId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Test test = testService.queryTestByTestId(testId);
			if(test == null){
				json = this.setJson(false, "修改失败", null);
				return json;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			json = this.setJson(true, null, test);
		}catch (Exception e) {
			json = this.setJson(false, null, null);
			logger.error("initUpdateTest()--error",e);
		}
		return json;
	}
	/**
	 * 修改测验
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateTest")
	@ResponseBody
	public Map<String,Object> updateTest(HttpServletRequest request,@ModelAttribute("test")Test test){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Test test2 = testService.queryTestByTestId(test.getTestId());
			if(test2 == null){
				json = this.setJson(false, "修改失败", null);
				return json;
			}
			if(sysId != 1 && sysId != test2.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			test.setTestUpdateTime(new Date());
			testService.updateTest(test);
			json = this.setJson(true, "修改成功", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateTest()--error",e);
		}
		return json;
	}
	/**
	 * 删除测验
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteTest/{testId}")
	@ResponseBody
	public Map<String,Object> deleteTest(HttpServletRequest request,@PathVariable("testId") int testId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Test test = testService.queryTestByTestId(testId);
			if(test == null){
				json = this.setJson(false, "删除失败", null);
				return json;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			List<TestQuestion> testQuestionList = testQuestionService.queryTestQuestionByTestId(testId);
			if(testQuestionList == null || testQuestionList.size()==0){
				testService.deleteTest(testId);
				json = this.setJson(true, "删除成功！", null);
			}
			else{
				json = this.setJson(false, "该测验存在试题！", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("deleteTest()---error",e);
		}
		return json;
	}
	
	
	/**
	 * 查看学员测验情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTestSubmitGradeList/{testId}")
	public ModelAndView queryTestSubmitGradeList(HttpServletRequest request,@PathVariable("testId") int testId,@ModelAttribute("queryTestSubmitGrade")QueryTestSubmitGrade queryTestSubmitGrade,@ModelAttribute("page") PageEntity page){
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
			List<TestQuestion> testQuestionList = testQuestionService.queryTestQuestionByTestId(testId);
			if(testQuestionList == null || testQuestionList.size()==0){
				model.setViewName("redirect:/admin/main");
				return model;
			}
			test.setQuestionNumber(testQuestionList.size());
			model.setViewName(getViewPath("/admin/test/test-submitgrade-list"));
			queryTestSubmitGrade.setTestId(testId);
			queryTestSubmitGrade.setTeacherId(test.getTeacherId());
			queryTestSubmitGrade.setCourseId(test.getCourseId());
			queryTestSubmitGrade.setTermId(test.getTermId());
			Term term = termService.queryTermById(test.getTermId());
			if(term != null){
				queryTestSubmitGrade.setGradeTermId(term.getGradeId());
			}
			else{
				queryTestSubmitGrade.setGradeTermId(0);
			}
			List<TestGradeDto> testGradeDtoList = testGradeService.queryTestGradeDtoListPage(queryTestSubmitGrade, page);
			if(testGradeDtoList!=null && testGradeDtoList.size()!=0){
				for(TestGradeDto testGradeDto:testGradeDtoList){
					TestSubmit testSubmit = new TestSubmit();
					testSubmit.setTestId(testId);
					testSubmit.setUserId(testGradeDto.getUserId());
					testSubmit.setTestSubmitId(testGradeDto.getTestSubmitId());
					testGradeDto.setTestQuestionDtoList(testQuestionService.queryTestQuestionDtoListBySubmit(testSubmit));
				}
			}
			int isok=0;
			if(test.getTestEndTime().compareTo(new Date()) <= 0){
				isok=1;
			}
			model.addObject("isok",isok);
			model.addObject("test",test);
			model.addObject("testQuestionList",testQuestionList);
			model.addObject("testGradeDtoList", testGradeDtoList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("queryTestSubmitGradeList()---error",e);
		}
		return model;
	}
	
	/**
	 * 更新测验成绩
	 * @param request
	 * @return
	 */
	@RequestMapping("/initUpdateTestGrade/{testSubmitId}")
	@ResponseBody
	public Map<String,Object> initUpdateTestGrade(HttpServletRequest request,@PathVariable("testSubmitId") int testSubmitId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			TestGradeDto testGradeDto = new TestGradeDto();
			TestSubmit testSubmit = testSubmitService.queryTestSubmitBySubmitId(testSubmitId);
			if(testSubmit == null){
				json = this.setJson(false, "初始化失败", null);
				return json;
			}
			Test test = testService.queryTestByTestId(testSubmit.getTestId());
			if(test == null){
				json = this.setJson(false, "初始化失败", null);
				return json;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			List<TestQuestionDto> testQuestionDtoList = testQuestionService.queryTestQuestionDtoListBySubmit(testSubmit);
			if(testQuestionDtoList == null || testQuestionDtoList.size()==0){
				json = this.setJson(false, "初始化失败", null);
				return json;
			}
			testGradeDto.setTestQuestionDtoList(testQuestionDtoList);
			json = this.setJson(true, null, testGradeDto);
		}catch (Exception e) {
			json = this.setJson(false, "初始化失败", null);
			logger.error("initUpdateTestGrade()--error",e);
		}
		return json;
	}
	/**
	 * 更新测验成绩
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateTestGrade")
	@ResponseBody
	public Map<String,Object> updateTestGrade(HttpServletRequest request,@ModelAttribute("testQuestionDto")TestQuestionDto testQuestionDto){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			int questionId = testQuestionDto.getQuestionId();
			int testSubmitId = testQuestionDto.getTestSubmitId();
			int questionGrade = testQuestionDto.getQuestionGrade();
			TestQuestion testQuestion = testQuestionService.queryTestQuestionByQuestionId(questionId);
			if(testQuestion == null){
				json = this.setJson(false, "问题不存在，更新失败", null);
				return json;
			}
			Test test = testService.queryTestByTestId(testQuestion.getTestId());
			if(test == null){
				json = this.setJson(false, "测验不存在，更新失败", null);
				return json;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				json = this.setJson(false, "没有此权限", null);
				return json;
			}
			TestSubmit testSubmit = testSubmitService.queryTestSubmitBySubmitId(testSubmitId);
			if(testSubmit == null){
				json = this.setJson(false, "无提交记录，更新失败", null);
				return json;
			}
			TestQuestionSubmit query = new TestQuestionSubmit();
			query.setQuestionId(questionId);	
			query.setTestSubmitId(testSubmitId);	
			TestQuestionSubmit testQuestionSubmit = testQuestionSubmitService.queryTestQuestionSubmitByQuestionIdAndTestSubmitId(query);
			if(testQuestionSubmit == null){
				json = this.setJson(false, "无提交记录，更新失败", null);
				return json;
			}
			TestGrade testGrade = testGradeService.queryTestGradeByTestSubmitId(testSubmitId);
			if(testGrade == null){
				testGrade = new TestGrade();
				testGrade.setTestCreateGradeTime(new Date());
				testGrade.setTestGrade(questionGrade);
				testGrade.setTestSubmitId(testSubmitId);
				testGrade.setTestUpdateGradeTime(new Date());
				testGradeService.createTestGrade(testGrade);
				TestQuestionGrade testQuestionGrade = new TestQuestionGrade();
				testQuestionGrade.setQuestionCreateGradeTime(new Date());
				testQuestionGrade.setQuestionGrade(questionGrade);
				testQuestionGrade.setQuestionSubmitId(testQuestionSubmit.getQuestionSubmitId());
				testQuestionGrade.setQuestionUpdateGradeTime(new Date());
				testQuestionGrade.setTestGradeId(testGrade.getTestGradeId());
				testQuestionGradeService.createTestQuestionGrade(testQuestionGrade);
			}
			else{
				TestQuestionGrade testQuestionGrade = testQuestionGradeService.queryTestQuestionGradeByQuestionSubmitId(testQuestionSubmit.getQuestionSubmitId());
				if(testQuestionGrade == null){
					testGrade.setTestUpdateGradeTime(new Date());
					testGrade.setTestGrade(testGrade.getTestGrade() + questionGrade);
					testGradeService.updateTestGrade(testGrade);
					testQuestionGrade = new TestQuestionGrade();
					testQuestionGrade.setQuestionCreateGradeTime(new Date());
					testQuestionGrade.setQuestionGrade(questionGrade);
					testQuestionGrade.setQuestionSubmitId(testQuestionSubmit.getQuestionSubmitId());
					testQuestionGrade.setQuestionUpdateGradeTime(new Date());
					testQuestionGrade.setTestGradeId(testGrade.getTestGradeId());
					testQuestionGradeService.createTestQuestionGrade(testQuestionGrade);
				}
				else{
					testGrade.setTestUpdateGradeTime(new Date());
					testGrade.setTestGrade(testGrade.getTestGrade() - testQuestionGrade.getQuestionGrade() + questionGrade);
					testGradeService.updateTestGrade(testGrade);
					testQuestionGrade.setQuestionGrade(questionGrade);
					testQuestionGrade.setQuestionUpdateGradeTime(new Date());
					testQuestionGradeService.updateTestQuestionGrade(testQuestionGrade);
				}
			}
			json = this.setJson(true, "更新成功", null);
			return json;
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateTestGrade()---error",e);
		}
		return json;
	}
	
	/**
     * 下载测验答案
     */
	@RequestMapping("/testdownload/{testSubmitId}")
	public ResponseEntity<byte[]> testDownload(HttpServletRequest request,HttpServletResponse response,@PathVariable("testSubmitId") int testSubmitId) {
		try {
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			TestSubmit testSubmit = testSubmitService.queryTestSubmitBySubmitId(testSubmitId);
			if(testSubmit == null){
				return null;
			}
			User user = userService.queryUserById(testSubmit.getUserId());
			if(user == null){
				return null;
			}
			Test test = testService.queryTestByTestId(testSubmit.getTestId());
			if(test == null){
				return null;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				return null;
			}
			List<TestQuestionDto> testQuestionDtoList = testQuestionService.queryTestQuestionDtoListBySubmit(testSubmit);
			if(testQuestionDtoList == null || testQuestionDtoList.size()==0){
				return null;
			}
			List<File> fileList = new ArrayList<File>();
			for(TestQuestionDto questionDto:testQuestionDtoList){
				String answer = questionDto.getQuestionSubmitAnswer();
				if(answer == null || answer == ""){
					continue;
				}
				File file = new File(answer);
				if(!file.exists()){
					continue;
				}
				fileList.add(file);
			}
			if(fileList == null || fileList.size()==0){
				return null;
			}
			String filePath = request.getSession().getServletContext().getRealPath("/testfile/download/");
			filePath+="/"+ DateUtils.toString(new Date(), "yyyyMMdd")+"/"+System.currentTimeMillis()+test.getTestId()+"/"+user.getStudentNumber()+user.getUserName()+".rar";
			zip(response,filePath,fileList);
			File file = new File(filePath);
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
	
	/**
     * 下载全部测验答案
     */
	@RequestMapping("/testdownloadall/{testId}")
	public ResponseEntity<byte[]> testDownloadAll(HttpServletRequest request,HttpServletResponse response,@PathVariable("testId") int testId,@ModelAttribute("queryTestSubmitGrade")QueryTestSubmitGrade queryTestSubmitGrade) {
		try {
			int sysId = SingletonLoginUtils.getLoginSysUserId(request);
			Test test = testService.queryTestByTestId(testId);
			if(test == null){
				return null;
			}
			if(sysId != 1 && sysId != test.getTeacherId()){
				return null;
			}
			queryTestSubmitGrade.setTestId(testId);
			queryTestSubmitGrade.setTeacherId(test.getTeacherId());
			queryTestSubmitGrade.setCourseId(test.getCourseId());
			queryTestSubmitGrade.setTermId(test.getTermId());
			Term term = termService.queryTermById(test.getTermId());
			if(term != null){
				queryTestSubmitGrade.setGradeTermId(term.getGradeId());
			}
			else{
				queryTestSubmitGrade.setGradeTermId(0);
			}
			List<TestGradeDto> testGradeDtoList = testGradeService.queryTestGradeDtoList(queryTestSubmitGrade);
			if(testGradeDtoList == null || testGradeDtoList.size()==0){
				return null;
			}
			for(TestGradeDto testGradeDto:testGradeDtoList){
				TestSubmit testSubmit = new TestSubmit();
				testSubmit.setTestId(testId);
				testSubmit.setUserId(testGradeDto.getUserId());
				testSubmit.setTestSubmitId(testGradeDto.getTestSubmitId());
				testGradeDto.setTestQuestionDtoList(testQuestionService.queryTestQuestionDtoListBySubmit(testSubmit));
			}
			String filePath = request.getSession().getServletContext().getRealPath("/testfile/download/");
			filePath+="/"+ DateUtils.toString(new Date(), "yyyyMMdd")+"/"+System.currentTimeMillis()+test.getTestId()+"/"+"test_answer.rar";
			zipAll(response,filePath,testGradeDtoList);
			File file = new File(filePath);
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
        	logger.error("testDownloadAll()--error", e);
        	return null;
		}
    }
	private static void zip(HttpServletResponse response,String zipFileName, List<File> fileList) throws Exception{
		File zipFile = new File(zipFileName);
		if(!zipFile.getParentFile().exists()){
			zipFile.getParentFile().mkdirs();
		}
		File zipfile = new File(zipFileName);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile)); 
		String ZIP_ENCODEING = "GBK";
		byte[] buf = new byte[1024];
		out.setEncoding(ZIP_ENCODEING);
		try {
		for(File file:fileList){
			out.putNextEntry(new ZipEntry(file.getName()));  
	        FileInputStream in = new FileInputStream(file);  
	        int len; 
	        while ((len = in.read(buf)) > 0) {  
	            out.write(buf, 0, len);  
	        }  
	        out.closeEntry();
	        in.close();
		}  
        out.close();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(zipfile.getName().getBytes("UTF-8"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + zipfile.length());
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(zipfile));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        }
		catch (IOException var) {
            var.printStackTrace();
        }
	}
	private static void zipAll(HttpServletResponse response,String zipFileName, List<TestGradeDto> testGradeDtoList) throws Exception{
		File zipFile = new File(zipFileName);
		if(!zipFile.getParentFile().exists()){
			zipFile.getParentFile().mkdirs();
		}
		File zipfile = new File(zipFileName);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile)); 
		String ZIP_ENCODEING = "GBK";
		byte[] buf = new byte[1024];
		out.setEncoding(ZIP_ENCODEING);
		try {
		for(TestGradeDto testGradeDto:testGradeDtoList){
			String base = testGradeDto.getStudentNumber()+testGradeDto.getUserName();
			out.putNextEntry(new ZipEntry(base + "/"));  
            base = base.length() == 0 ? "" : base + "/"; 
            if(testGradeDto.getTestQuestionDtoList() == null || testGradeDto.getTestQuestionDtoList().size()==0){
				continue;
			}
            for(TestQuestionDto questionDto:testGradeDto.getTestQuestionDtoList()){
            	String answer = questionDto.getQuestionSubmitAnswer();
				if(answer == null || answer == ""){
					continue;
				}
				File file = new File(answer);
				if(!file.exists()){
					continue;
				}
				out.putNextEntry(new ZipEntry(base + file.getName()));  
		        FileInputStream in = new FileInputStream(file);  
		        int len; 
		        while ((len = in.read(buf)) > 0) {  
		            out.write(buf, 0, len);  
		        }  
		        out.closeEntry();
		        in.close();
            }
		}
        out.close();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(zipfile.getName().getBytes("UTF-8"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + zipfile.length());
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(zipfile));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        BufferedOutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        }
		catch (IOException var) {
            var.printStackTrace();
        }
	}
}


 

