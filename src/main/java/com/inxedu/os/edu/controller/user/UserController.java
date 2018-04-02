package com.inxedu.os.edu.controller.user;

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
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.term.Term;
import com.inxedu.os.edu.entity.test.QueryTest;
import com.inxedu.os.edu.entity.test.TestGradeDto;
import com.inxedu.os.edu.entity.user.QueryUserTeacher;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.user.UserLoginLog;
import com.inxedu.os.edu.entity.user.UserTeacher;
import com.inxedu.os.edu.entity.user.UserTeacherDto;
import com.inxedu.os.edu.service.course.CourseFavoritesService;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.term.TermService;
import com.inxedu.os.edu.service.test.TestGradeService;
import com.inxedu.os.edu.service.user.UserLoginLogService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.user.UserTeacherService;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 前台学员  Controller
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/uc")
public class UserController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserLoginLogService userLoginLogService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private CourseFavoritesService courseFavoritesService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
	@Autowired
	private TestGradeService testGradeService;
	@Autowired
	private TermService termService;
	@Autowired
	private UserTeacherService userTeacherService;
	
	@InitBinder({"user"})
	public void initBinderUser(WebDataBinder binder){
		binder.setFieldDefaultPrefix("user.");
	}

	@InitBinder("msgReceive")
	public void initBinderMsgReceive(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("msgReceive.");
	}

	/**
	 * 删除收藏
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteFaveorite/{ids}")
	public ModelAndView deleteFavorite(HttpServletRequest request,@PathVariable("ids") String ids){
		ModelAndView model = new ModelAndView();
		try{
			courseFavoritesService.deleteCourseFavoritesById(ids);
			Object uri = request.getSession().getAttribute("favoritesListUri");
			if(uri!=null){
				model.setViewName("redirect:"+uri.toString());
			}else{
				model.setViewName("redirect:/uc/myFavorites");
			}
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("deleteFavorite()---error",e);
		}
		return model;
	}
	/**
	 * 删除观看历史
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteHistory/{ids}")
	public ModelAndView deleteHistory(HttpServletRequest request,@PathVariable("ids") String ids){
		ModelAndView model = new ModelAndView();
		try{
			courseStudyhistoryService.deleteCourseStudyhistoryByIds(ids);
			Object uri = request.getSession().getAttribute("historyListUri");
			if(uri!=null){
				model.setViewName("redirect:"+uri.toString());
			}else{
				model.setViewName("redirect:/uc/courseHistory");
			}
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("deleteFavorite()---error",e);
		}
		return model;
	}
	/**
	 * 我的收藏列表
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/myFavorites")
	public ModelAndView myFavorites(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/favourite_course_list"));
		try{
			page.setPageSize(5);
			int userId = SingletonLoginUtils.getLoginUserId(request);
			List<FavouriteCourseDTO> favoriteList = courseFavoritesService.queryFavoritesPage(userId, page);
			model.addObject("favoriteList", favoriteList);
			model.addObject("page", page);
			request.getSession().setAttribute("favoritesListUri", WebUtils.getServletRequestUriParms(request));
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("myFavorites()---error",e);
		}
		return model;
	}

	/**
	 * 进入播放页面
	 * @param request
	 * @param courseId 课程ID
	 * @return ModelAndView
	 */
	@RequestMapping("/play/{courseId}/{kpointId}")
	public ModelAndView playVideo(HttpServletRequest request,@PathVariable("courseId") int courseId,@PathVariable("kpointId") int kpointId){
		ModelAndView model = new ModelAndView();
		try{
			User user = SingletonLoginUtils.getLoginUser(request);
			SysUser sysUser = SingletonLoginUtils.getLoginSysUser(request);
			Course course = courseService.queryCourseById(courseId);
			if((user==null&&sysUser==null) || (course==null)){
				model.setViewName("redirect:/");
				return model;
			}
//			else{
//				if(course.getUserId() == 1 || user.getTeacherIds().contains(course.getUserId())){
//					
//				}
//				else{
//					model.setViewName("redirect:/");
//					return model;
//				}
//			}
			model.setViewName(getViewPath("/web/ucenter/player-video"));
			model.addObject("kpointId", kpointId);
			if(course!=null){
				List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
				List<CourseKpoint> parentKpointList = new ArrayList<CourseKpoint>();
				if(user == null){
					model.addObject("isFavorites", false);
					course.setStudyPercent("0");
					if (kpointList!=null && kpointList.size()>0){
						for(CourseKpoint temp:kpointList){
							temp.setIsView(0);
						}
					}
				}
				else{
					int userId = SingletonLoginUtils.getLoginUserId(request);
					//查询是否已经收藏
					boolean isFavorites = courseFavoritesService.checkFavorites(userId, courseId);
					model.addObject("isFavorites", isFavorites);

					CourseStudyhistory courseStudyhistory=new CourseStudyhistory();
					courseStudyhistory.setUserId(Long.valueOf(userId));
					courseStudyhistory.setCourseId(Long.valueOf(String.valueOf(courseId)));
					//我的课程学习记录
					List<CourseStudyhistory>  couStudyhistorysLearned=courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
					int courseHistorySize=0;
					if (couStudyhistorysLearned!=null&&couStudyhistorysLearned.size()>0) {
						courseHistorySize=couStudyhistorysLearned.size();
					}
					//二级视频节点的 总数
					int sonKpointCount=courseKpointService.getSecondLevelKpointCount(Long.valueOf(courseId));
					NumberFormat numberFormat = NumberFormat.getInstance();
					//我的学习进度百分比
					// 设置精确到小数点后0位
					numberFormat.setMaximumFractionDigits(0);
					String studyPercent = numberFormat.format((float) courseHistorySize / (float) sonKpointCount * 100);
					if(sonKpointCount==0){
						studyPercent="0";
					}
					course.setStudyPercent(studyPercent);
					
					if (couStudyhistorysLearned!=null&&couStudyhistorysLearned.size()>0 && kpointList!=null && kpointList.size()>0){
						for(CourseKpoint temp:kpointList){
							temp.setIsView(0);
							for(CourseStudyhistory temp2:couStudyhistorysLearned){
								if(temp.getKpointId() == temp2.getKpointId()){
									temp.setIsView(1);
									break;
								}
							}
						}
					}
				}
				
				model.addObject("course", course);
				model.addObject("isok", true);
				
				
				if(kpointList!=null && kpointList.size()>0){
					//遍历 一级目录
					for(CourseKpoint temp:kpointList){
						if (temp.getParentId()==0) {
							parentKpointList.add(temp);
						}
					}

					//遍历 获取二级目录
					for(CourseKpoint tempParent:parentKpointList){
						for(CourseKpoint temp:kpointList){
							if (temp.getParentId()==tempParent.getKpointId()) {
								tempParent.getKpointList().add(temp);
							}
						}
					}
					model.addObject("parentKpointList", parentKpointList);
				}

				//相关课程
				List<CourseDto> courseList = courseService.queryInterfixCourseLis(course.getSubjectId(), 5,course.getCourseId());
				for(CourseDto tempCoursedto : courseList){
					List<Map<String,Object>> teacherList=teacherService.queryCourseTeacerList(tempCoursedto.getCourseId());
					tempCoursedto.setTeacherList(teacherList);
				}
				model.addObject("courseList", courseList);
			}

		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("playVideo()--error",e);
		}
		return model;
	}

	/**
	 * 进入个人中心
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView ucIndex(HttpServletRequest request,@ModelAttribute("page") PageEntity page,@ModelAttribute("queryCourse") QueryCourse queryCourse){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/web/ucenter/uc_freecourse"));
			// 页面传来的数据放到page中
			page.setPageSize(9);
			//只查询上架的
			queryCourse.setIsavaliable(1);
			queryCourse.setIsFree("true");//查询免费的课程
			// 搜索课程列表
			List<CourseDto> courseList = courseService.queryWebCourseListPage(queryCourse, page);
			if(courseList!=null&&courseList.size()>0){
				//获取登录用户ID
				int userId = SingletonLoginUtils.getLoginUserId(request);
				for(Course course:courseList){
					CourseStudyhistory courseStudyhistory=new CourseStudyhistory();
					courseStudyhistory.setUserId(Long.valueOf(userId));
					courseStudyhistory.setCourseId(Long.valueOf(String.valueOf(course.getCourseId())));
					//我的课程学习记录
					List<CourseStudyhistory>  couStudyhistorysLearned=courseStudyhistoryService.getCourseStudyhistoryList(courseStudyhistory);
					int courseHistorySize=0;
					if (couStudyhistorysLearned!=null&&couStudyhistorysLearned.size()>0) {
						courseHistorySize=couStudyhistorysLearned.size();
					}
					//二级视频节点的 总数
					int sonKpointCount=courseKpointService.getSecondLevelKpointCount(Long.valueOf(course.getCourseId()));
					NumberFormat numberFormat = NumberFormat.getInstance();
					//我的学习进度百分比
					// 设置精确到小数点后0位
					numberFormat.setMaximumFractionDigits(0);
					String studyPercent = numberFormat.format((float) courseHistorySize / (float) sonKpointCount * 100);
					if(sonKpointCount==0){
						studyPercent="0";
					}
					course.setStudyPercent(studyPercent);
				}
			}
			model.addObject("courseList", courseList);
			model.addObject("page",page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("ucIndex()--error",e);
		}
		return model;
	}


	/**
	 * 修改用户头像
	 * @return Map<String,Object>
	 */
	@RequestMapping("/updateImg")
	@ResponseBody
	public Map<String,Object> updatePicImg(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			if(user.getUserId()>0){
				userService.updateImg(user);
				//修改缓存用户
				userService.setLoginInfo(request,user.getUserId(),"false");
				json = this.setJson(true, null, null);
			}else{
				json = this.setJson(true, "头像修改失败", null);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updatePicImg()",e);
		}
		return json;
	}

	/**
	 * 修改用户密码
	 * @param request
	 * @param user
	 * @return Map<String,Object>
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Map<String,Object> updatePwd(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
//			user = userService.queryUserById(user.getUserId());
//			if(user!=null){
//				//原密码
//				String nowPassword = request.getParameter("nowPassword")==null?"":request.getParameter("nowPassword");
//				//新密码
//				String newPassword = request.getParameter("newPassword")==null?"":request.getParameter("newPassword");
//				//确认密码
//				String confirmPwd = request.getParameter("confirmPwd")==null?"":request.getParameter("confirmPwd");
//				if(nowPassword.equals("")|| nowPassword.trim().length()==0){
//					json = this.setJson(false, "请输入旧密码！", null);
//					return json;
//				}
//				if(!user.getPassword().equals(MD5.getMD5(nowPassword))){
//					json = this.setJson(false, "旧密码不正确！", null);
//					return json;
//				}
//				if(newPassword.equals("") || newPassword.trim().length()==0){
//					json = this.setJson(false, "请输入新密码！", null);
//					return json;
//				}
//				if(!WebUtils.isPasswordAvailable(newPassword)){
//					json = this.setJson(false, "密码只能是数字字母组合且大于等于6位小于等于16位", null);
//					return json;
//				}
//				if(!newPassword.equals(confirmPwd)){
//					json = this.setJson(false, "新密码和确认密码不一致！", null);
//					return json;
//				}
//				user.setPassword(MD5.getMD5(newPassword));
//				userService.updateUserPwd(user);
//				json = this.setJson(true, "修改成功", null);
//				return json;
//			}
			json = this.setJson(false, "修改失败", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updatePwd()--error",e);
		}
		return json;
	}

	/**
	 * 修改用户信息
	 * @param request
	 * @param user
	 * @return Map<String,Object>
	 */
	@RequestMapping("/updateUser")
	@ResponseBody
	public Map<String,Object> updateUserInfo(HttpServletRequest request,@ModelAttribute("user") User user){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			userService.updateUser(user);
			json = this.setJson(true, "修改成功", user);
			//缓存用户
			userService.setLoginInfo(request,user.getUserId(),"false");
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updateUserInfo()---error",e);
		}
		return json;
	}

	/**
	 * 初始化修改用户信息页面
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/initUpdateUser/{index}")
	public ModelAndView initUpdateUser(HttpServletRequest request,@PathVariable("index") int index){
		ModelAndView model = new ModelAndView();
		try{
			int userId = SingletonLoginUtils.getLoginUserId(request);
			User user = userService.queryUserById(userId);
			model.addObject("user", user);
			model.addObject("index",index);
			model.setViewName(getViewPath("/web/ucenter/user-info"));
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("initUpdateUser()---error",e);
		}
		return model;
	}


	/**
	 * 获取登录用户
	 */
	@RequestMapping("/getloginUser")
	@ResponseBody
	public Map<String,Object> getLoginUser(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			User user = SingletonLoginUtils.getLoginUser(request);
			if(user==null|| user.getUserId()==0){
				json = this.setJson(false, null, null);
			}else{
				json = this.setJson(true, null, user);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("getLoginUser()---error",e);
		}
		return json;
	}
	@RequestMapping("/getloginSysUser")
	@ResponseBody
	public Map<String,Object> getLoginSysUser(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			SysUser sysUser = SingletonLoginUtils.getLoginSysUser(request);
			if(sysUser==null|| sysUser.getUserId()==0){
				json = this.setJson(false, null, null);
			}else{
				json = this.setJson(true, null, sysUser);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("getLoginSysUser()---error",e);
		}
		return json;
	}
	
	/**
	 * 判断该学员的老师是不是该视频的上传者
	 */
	@RequestMapping("/checkTeacher/{courseId}")
	@ResponseBody
	public Map<String,Object> checkTeacher(HttpServletRequest request,@PathVariable("courseId") int courseId){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			User user = SingletonLoginUtils.getLoginUser(request);
			Course course = courseService.queryCourseById(courseId);
			if(user==null || course==null){
				json = this.setJson(false, null, null);
			}else{
				if(course.getUserId() == 1){
					json = this.setJson(true, null, null);
				}
				else{
					Term term = termService.checkTerm(new Date());
					if(term == null){
						json = this.setJson(false, null, null);
					}
					else{
						UserTeacher userTeacher = new UserTeacher();
						userTeacher.setTermId(term.getTermId());
						userTeacher.setTeacherId(course.getUserId());
						userTeacher.setUserId(user.getUserId());
						userTeacher.setCourseId(courseId);
						int flag = userTeacherService.checkUserTeacher2(userTeacher);
						if(flag > 0){
							json = this.setJson(true, null, null);
						}
						else{
							json = this.setJson(false, null, null);
						}
					}
				}
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("checkTeacher()---error",e);
		}
		return json;
	}
	
	/**
	 * 执行登录
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String,Object> userLogin(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String account = request.getParameter("account");//账号
			String password = request.getParameter("password");//密码
			String ipForget = request.getParameter("ipForget");//是否自动登录
			//判断账号是否为空
			if(!StringUtils.isNotEmpty(account)){
				json = this.setJson(false, "请输入登录帐号", null);
				return json;
			}
			//判断密码是否为空
			if(!StringUtils.isNotEmpty(password)){
				json = this.setJson(false, "请输入登录密码", null);
				return json;
			}
			//根据账号、密码查找用户
			User user = userService.getLoginUser(account, MD5.getMD5(password));
			//如果用户不存在
			if(user==null){
				json = this.setJson(false, "帐号或密码错误", null);
				return json;
			}
			
			EHCacheUtil.remove(CacheConstans.WEB_USER_LOGIN_PREFIX+user.getUserId());
			//如果该账号不可用
			if(user.getIsavalible()==2){
				json = this.setJson(false, "帐号已被禁用", null);
				return json;
			}

			//用户密码不能让别人看到
			user.setPassword("");
			//获取UUID
			String uuid = StringUtils.createUUID().replace("-", "");
			//当前时间戳
			Long currentTimestamp=System.currentTimeMillis();
			user.setLoginTimeStamp(currentTimestamp);
			//如果记住密码，缓存6天
			if("true".equals(ipForget)){
				//缓存用户
				EHCacheUtil.set(uuid, user,CacheConstans.USER_TIME);
				//缓存用户的登录时间
				EHCacheUtil.set(CacheConstans.USER_CURRENT_LOGINTIME+user.getUserId(), currentTimestamp.toString(), CacheConstans.USER_TIME);
				//保存用户的UUID
				WebUtils.setCookie(response, CacheConstans.WEB_USER_LOGIN_PREFIX, uuid, (CacheConstans.USER_TIME/60/60/24));
			}
			//没记住密码，缓存1天
			else{
				//缓存用户
				EHCacheUtil.set(uuid, user,86400);
				//缓存用户的登录时间
				EHCacheUtil.set(CacheConstans.USER_CURRENT_LOGINTIME+user.getUserId(), currentTimestamp.toString(), 86400);
				//保存用户的UUID
				WebUtils.setCookie(response, CacheConstans.WEB_USER_LOGIN_PREFIX, uuid, 1);
			}

			//添加用户登录日志
			UserLoginLog loginLog =new UserLoginLog();
			loginLog.setIp(WebUtils.getIpAddr(request));//IP地址
			loginLog.setLoginTime(new Date());//登陆时间
			String userAgent = WebUtils.getUserAgent(request);
			if(StringUtils.isNotEmpty(userAgent)){
				loginLog.setOsName(userAgent.split(";")[1]);//操作系统
				loginLog.setUserAgent(userAgent.split(";")[0]);//浏览器
			}
			loginLog.setUserId(user.getUserId());//用户id
			userLoginLogService.createLoginLog(loginLog);
			json = this.setJson(true, "", null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("userLogin()--error",e);
		}
		return json;
	}

	/**
	 * 创建学员
	 * @param user 学员对象
	 * @return Map<String,Object>
	 */
//	@RequestMapping("/createuser")
//	@ResponseBody
//	public Map<String,Object> createUser(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("user") User user){
//		Map<String,Object> json = new HashMap<String,Object>();
//		try{
//			//用户输入的验证码
//			String registerCode = request.getParameter("registerCode")==null?"":request.getParameter("registerCode");
//			//系统随机产生的验证码
//			Object randCode = request.getSession().getAttribute(CommonConstants.RAND_CODE);
//			//验证码不正确则返回
//			if(randCode==null || !registerCode.equals(randCode.toString())){
//				json = this.setJson(false, "请输入正确的验证码", null);
//				return json;
//			}
//            //第二次输入的密码
//			String confirmPwd = request.getParameter("confirmPwd");
//            //判断邮箱格式是否正确
//			if(user.getEmail()==null || user.getEmail().trim().length()==0 || !WebUtils.checkEmail(user.getEmail(), 50)){
//				json = this.setJson(false, "请输入正确的邮箱号", null);
//				return json;
//			}
//			//判断邮箱是不是已经存在
//			if(userService.checkEmail(user.getEmail().trim())){
//				json = this.setJson(false, "该邮箱号已被使用", null);
//				return json;
//			}
//			//判断手机号格式是否正确
//			if(user.getMobile()==null || user.getMobile().trim().length()==0 || !WebUtils.checkMobile(user.getMobile())){
//				json = this.setJson(false, "请输入正确的手机号", null);
//				return json;
//			}
//			//判断手机号是不是已经存在
//			if(userService.checkMobile(user.getMobile())){
//				json = this.setJson(false, "该手机号已被使用", null);
//				return json;
//			}
//			//判断第一次输入的密码格式是否正确
//			if(user.getPassword()==null || user.getPassword().trim().length()==0 || !WebUtils.isPasswordAvailable(user.getPassword())){
//				json = this.setJson(false, "密码有字母和数字组合且≥6位≤16位", null);
//				return json;
//			}
//			//判断第一次输入的密码和第二次输入的密码是否一致
//			if(!user.getPassword().equals(confirmPwd)){
//				json = this.setJson(false, "两次密码不一致", null);
//				return json;
//			}
//			user.setStudentNumber("");//设置学号
//			user.setClassId(0);//设置班级ID
//			user.setCreateTime(new Date());//设置创建时间
//			user.setIsavalible(1);//设置是否可用
//			user.setPassword(MD5.getMD5(user.getPassword()));//设置密码
//			user.setMsgNum(0);//设置消息数
//			user.setSysMsgNum(0);//设置系统消息数
//			user.setLastSystemTime(new Date());//设置最后一次系统时间
//			userService.createUser(user);//创建用户
//			request.getSession().removeAttribute(CommonConstants.RAND_CODE);//销毁系统验证码
//			json = this.setJson(true, "注册成功", null);
//
//			// 注册时发送系统消息
//			Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
//			Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
//			String company = web.get("company").toString();
//			String conent = "欢迎来到" + company + ",希望您能够快乐的学习";
//			msgReceiveService.addSystemMessageByCusId(conent, Long.valueOf(user.getUserId()));
//			String uuid = StringUtils.createUUID().replace("-", "");
//			//缓存用户key
//			WebUtils.setCookie(response, CacheConstans.WEB_USER_LOGIN_PREFIX, uuid, 1);
//
//			//当前时间戳
//			Long currentTimestamp=System.currentTimeMillis();
//			//缓存用户的登录时间
//			EHCacheUtil.set(CacheConstans.USER_CURRENT_LOGINTIME+user.getUserId(), currentTimestamp.toString(), CacheConstans.USER_TIME);
//			//缓存用户
//			userService.setLoginInfo(request,user.getUserId(),"false");
//		}catch (Exception e) {
//			logger.error("createUser()--eror",e);
//		}
//		return json;
//	}

	/**
	 * 退出登录
	 * @param request
	 * @return String
	 */
	@RequestMapping("/exit")
	@ResponseBody
	public Map<String,Object> outLogin(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String prefix = WebUtils.getCookie(request, CacheConstans.WEB_USER_LOGIN_PREFIX);
			if(prefix!=null){
				EHCacheUtil.remove(prefix);
			}
			json = this.setJson(true, null, null);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("outLogin()--error",e);

		}
		return json;
	}


	/**
	 * 发送找回密码邮件
	 * @return Map<String,Object>
	 */
	@RequestMapping("/sendEmail")
	@ResponseBody
	public Map<String,Object> sendEmail(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String email = request.getParameter("email");
			if(email==null || email.trim().length()==0){
				json = this.setJson(false, "请填写邮箱号", null);
				return json;
			}
			if(!WebUtils.checkEmail(email, 50)){
				json = this.setJson(false, "请填正确的邮箱号", null);
				return json;
			}
			String pageCode = request.getParameter("pageCode");
			String randCode = (String) request.getSession().getAttribute(CommonConstants.RAND_CODE);
			if(randCode==null ||randCode.trim().length()==0 || pageCode==null || pageCode.trim().length()==0 || !pageCode.equals(randCode) ){
				json = this.setJson(false, "验证码错误", null);
				return json;
			}
			User user = userService.queryUserByEmailOrMobile(email);
			if(user==null){
				json = this.setJson(false, "该邮箱号未注册", null);
				return json;
			}
			String newPwd = getRandomNum(6);
			user.setPassword(MD5.getMD5(newPwd));
			Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
			@SuppressWarnings("unchecked")
			Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
			String company = web.get("company").toString();
			emailService.sendMail("找回密码","帐号为["+user.getEmail()+"]的用户，您新密码是["+newPwd+"],请使用后修改密码———帐号找回["+company+"]", email);
			json = this.setJson(true, "邮件发送成功，请登录邮箱查收", null);
			request.getSession().removeAttribute(CommonConstants.RAND_CODE);
			userService.updateUserPwd(user);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("sendEmail()--error",e);
		}
		return json;
	}

	/**
	 * 生成随机数
	 * @param count 生成个数
	 * @return String
	 */
	private String getRandomNum(int count){
		Random ra = new Random();
		String random="";
		for(int i=0;i<count;i++){
			random+=ra.nextInt(9);
		}
		return random;
	}

	/**
	 * 查询站内信收件箱（无社区）
	 *
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/letter")
	public ModelAndView queryUserLetter(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(getViewPath("/web/ucenter/uc_letter_inbox"));// 用户消息
		try {

			page.setPageSize(6);// 分页页数为6
			MsgReceive msgReceive = new MsgReceive();
			msgReceive.setReceivingCusId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));// set用户id
			List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, page);// 查询站内信收件箱

			//修改用户消息数后  重新加入缓存
			userService.setLoginInfo(request,msgReceive.getReceivingCusId().intValue(),"false");
           
			modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
			modelAndView.addObject("page", page);// 分页参数放入modelAndView中
		} catch (Exception e) {
			logger.error("UserController.queryUserLetter()", e);
			setExceptionRequest(request, e);
			modelAndView.setViewName(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
     * 删除站内信收件箱
     *
     * @param msgReceive
     * @return
     */
    @RequestMapping(value = "/ajax/delLetterInbox")
    @ResponseBody
    public Map<String, Object> delLetterInbox(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgReceive.setReceivingCusId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));// set 用户id
            Long num = msgReceiveService.delMsgReceiveInbox(msgReceive);// 删除收件箱
            if (num.intValue() == 1) {
                map.put("message", "success");// 成功
            } else {
                map.put("message", "false");// 失败
            }
        } catch (Exception e) {
            logger.error("UserController.delLetterInbox()", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 查询该用户有多少未读消息
     */
    @RequestMapping(value = "/ajax/queryUnReadLetter")
    @ResponseBody
    public Map<String, Object> queryUnReadLetter(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (SingletonLoginUtils.getLoginUserId(request)== 0) {
                return map;
            }
            Map<String, String> queryletter = msgReceiveService.queryUnReadMsgReceiveNumByCusId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));// 查询该用户有多少未读消息
            map.put("entity", queryletter);// 把值放入map中返回json
        } catch (Exception e) {
            logger.error("UserController.queryUnReadLetter()", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
    /**
     * 查询该用户观看记录
     */
    @RequestMapping(value = "/courseHistory")
    public ModelAndView courseHistory(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/course_history"));
		try{
			page.setPageSize(5);
			int userId = SingletonLoginUtils.getLoginUserId(request);
			List<CourseStudyHistoryDto> historyList = courseStudyhistoryService.getCourseStudyhistoryListByUserId(userId,page);
			model.addObject("historyList", historyList);
			model.addObject("page", page);
			request.getSession().setAttribute("historyListUri", WebUtils.getServletRequestUriParms(request));
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("courseHistory()---error",e);
		}
		return model;
	}
    /**
     * 查询该用户测验记录
     */
    @RequestMapping(value = "/testHistory")
    public ModelAndView testHistory(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/test_history"));
		try{
			page.setPageSize(10);
			int userId = SingletonLoginUtils.getLoginUserId(request);
			QueryTest queryTest = new QueryTest();
			queryTest.setUserId(userId);
			List<TestGradeDto> testGradeDtoList = testGradeService.queryTestGradeDtoListPage2(queryTest, page);
			model.addObject("testGradeDtoList", testGradeDtoList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("testHistory()---error",e);
		}
		return model;
	}
    /**
     * 查询该用户选课记录
     */
    @RequestMapping(value = "/chosecourse/{status}")
    public ModelAndView choseCourse(HttpServletRequest request,@ModelAttribute("page") PageEntity page, @PathVariable("status") int status){
		ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/chose_course"));
		try{
			page.setPageSize(10);
			int userId = SingletonLoginUtils.getLoginUserId(request);
			Term term = termService.checkTerm(new Date());
			if(term == null || userId == 0 || (status != 0 && status != 1)){
				model.addObject("userTeacherDtoList", null);
				model.addObject("page", page);
				model.addObject("status", status);
			}
			QueryUserTeacher query = new QueryUserTeacher();
			query.setUserId(userId);
			query.setTermId(term.getTermId());
			query.setStatus(status);
			List<UserTeacherDto> userTeacherDtoList = userTeacherService.queryUserTeacher(query, page);
			model.addObject("userTeacherDtoList", userTeacherDtoList);
			model.addObject("page", page);
			model.addObject("status", status);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("choseCourse()---error",e);
		}
		return model;
	}
}

