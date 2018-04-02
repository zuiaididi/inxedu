<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>我的资料</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/jcrop/jquery.Jcrop.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
<script type="text/javascript" src="${ctx}/static/common/jcrop/jquery.Jcrop.min.js"></script>
<script type="text/javascript" src="${ctx}/static/inxweb/user/user.js"></script>
<script type="text/javascript" src="${ctx}/kindeditor/kindeditor-all.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript">
$(function(){
	showTab('${index}');//选项控制显示
	uploadImg('fileupload','uploadfile');
});
</script>
<style type="text/css">
	.ke-upload-area {min-width: 54px;width: auto;}
	.ke-upload-area .ke-button-common,.ke-upload-area .ke-button-common input {border: 1px solid #FF5F16;background: #FFF7F3;}
	.ke-upload-area .ke-button-common input {border: none;width: 100px;color: #FF5F16;font-weight: bold;}
	.ke-upload-area .ke-button-common input:hover {background: #FFF2EC;}
</style>
</head>
<body>
	<article class="col-7 fl">
		<div class="u-r-cont">
			<section>
				<div>
					<section class="c-infor-tabTitle c-tab-title">
						<a href="javascript: void(0)" title="Wo的资料" style="cursor: default;">Wo的资料</a>
						<a href="javascript: void(0)" title="基本资料" class="clickAvailable current">基本资料</a>
						<a href="javascript: void(0)" title="个人头像" class="clickAvailable ">个人头像</a>
						<a href="javascript: void(0)" title="密码设置" class="clickAvailable ">密码设置</a>
					</section>
				</div>
				<div class="mt40" id="p_tCont">
					<div class="u-account-box">
						<form method="post" id="updateForm">
							<input type="hidden" name="user.userId" value="${user.userId}" />
							<ol class="u-account-li"> 
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">邮 箱</span>
									</label>
									<input type="text" class="u-a-inpt" name="${user.email}" value="${user.email}" placeholder="" readonly="readonly" disabled="disabled">
									<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">手机号</span>
									</label>
									<input type="text" class="u-a-inpt" name="user.mobile" value="${user.mobile }" placeholder="" readonly="readonly" disabled="disabled">
									<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">学号</span>
									</label>
									<input type="text" class="u-a-inpt" name="user.studentNumber" value="${user.studentNumber }" placeholder="" readonly="readonly" disabled="disabled">
									<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">姓 名</span>
									</label>
									<input type="text" class="u-a-inpt" name="user.userName" value="${user.userName }" placeholder="" >
									<!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em>请输入正确的账 号</span> -->
								</li>
								<c:if test = "${user.classId != 0}">
									<li>
									    <label class="u-a-title">
									     	<span class="fsize16 c-999">学 院</span>
									    </label>
									    <input type="text" class="u-a-inpt" name="user.collegeName" value="${user.collegeName }" placeholder="" readonly="readonly" disabled="disabled">	
								    </li>
								    <li>
									    <label class="u-a-title">
										    <span class="fsize16 c-999">专 业</span>
									    </label>
									    <input type="text" class="u-a-inpt" name="user.majorName" value="${user.majorName }" placeholder="" readonly="readonly" disabled="disabled">
								    </li>
								    <li>
									    <label class="u-a-title">
										    <span class="fsize16 c-999">年 级</span>
									    </label>
									    <input type="text" class="u-a-inpt" name="user.gradeName" value="${user.gradeName}" placeholder="" readonly="readonly" disabled="disabled">
								    </li>
								    <li>
									    <label class="u-a-title">
										    <span class="fsize16 c-999">班 级</span>
									    </label>
									    <input type="text" class="u-a-inpt" name="user.className" value="${user.className}" placeholder="" readonly="readonly" disabled="disabled">
								    </li>
								</c:if>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">昵 称</span>
									</label>
									<input type="text" class="u-a-inpt" name="user.showName" value="${user.showName}" placeholder="">
									<!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em>请输入正确的昵称</span> -->
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">性 别</span>
									</label>
									<input type="radio" name="user.sex" <c:if test="${user.sex==1}">checked="checked"</c:if> value="1"/><span class="vam fsize14 c-666">男</span>
									<input type="radio" name="user.sex" <c:if test="${user.sex==2}">checked="checked"</c:if> value="2"/><span class="vam fsize14 c-666">女</span>
									<!-- <span class="u-a-error"><em class="u-a-zq icon16">&nbsp;</em></span> -->
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">出生日期</span>
									</label>
									<input placeholder="" name="user.birthday" class="u-a-inpt"
				                    value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>" 
				                    id="birthday" type="text" readonly="readonly" />
									<!-- <span class="u-a-error"><em class="u-a-cw icon16">&nbsp;</em></span> -->
								</li>
							</ol>
						</form>
						<div class="ml50 mt50 pl50">
							<a href="javascript:void(0)" title="" class="comm-btn c-btn-7" onclick="updateUserInfo()">提 交</a>
						</div>
					</div>
					
					<!--修改头像，开始-->
					<div class="u-account-box undis">
						<div id="tabCont">
							<section>
								<section class="ukindeditor of">
									<section class="clearfix">
										<!--个人头像上传控件-->
										<section>
											<input id="fileupload" type="button" width="133" value="选择头像" height="45" class="pa" />
										</section>
										<!--个人头像上传控件-->
										<!--个人头像裁切控件-->
										<div class="fl jc-demo-box pr mt40">
											<c:choose>
												<c:when test="${user.picImg!=null && user.picImg!=''}">
													<img src="<%=staticImage%>${user.picImg}" width="100%" height="100%" alt="头像加载中..." class="dis pictureWrap" id="picture" />
												</c:when>
												<c:otherwise>
													<img src="${ctx}/static/inxweb/img/avatar-boy.gif" width="100%" height="100%" alt="头像加载中..." class="dis pictureWrap"
														id="picture" />
												</c:otherwise>
											</c:choose>
											<div id="preview-pane" class="preview-pane1">
												<div class="preview-container">
													<c:choose>
														<c:when test="${user.picImg!=null && user.picImg!=''}">
															<img src="<%=staticImage%>${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
														</c:when>
														<c:otherwise>
															<img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
														</c:otherwise>
													</c:choose>
												</div>
												<p class="c-999">大尺寸个人头像，大小<br />180x180像素</p>
											</div>
											<div id="preview-pane" class="preview-pane2">
												<div class="preview-container" style="width: 80px; height: 80px; margin: 0 auto;">
													<c:choose>
														<c:when test="${user.picImg!=null && user.picImg!=''}">
															<img src="<%=staticImage%>${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
														</c:when>
														<c:otherwise>
															<img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
														</c:otherwise>
													</c:choose>
												</div>
												<p class="c-999">中尺寸个人头像，80x80像素</p>
											</div>
											<div id="preview-pane" class="preview-pane3">
												<div class="preview-container" style="width: 50px; height: 50px;">
													<c:choose>
														<c:when test="${user.picImg!=null && user.picImg!=''}">
															<img src="<%=staticImage%>${user.picImg}" class="jcrop-preview" alt="头像加载中..." width="100%" />
														</c:when>
														<c:otherwise>
															<img src="${ctx}/static/inxweb/img/avatar-boy.gif" class="jcrop-preview" alt="头像加载中..." width="100%" />
														</c:otherwise>
													</c:choose>
												</div>
												<p class="c-999">小尺寸个人头像，50x50像素</p>
											</div>
										</div>
										<!--个人头像裁切控件-->
										<div class="fl ml30 uploadWrap mt40">
											<p class="c-green">您上传的图片将会自动生成三种尺寸的清晰头像，请注意小尺寸的头像是否清晰。</p>
										</div>
										<section class="clear"></section>
										<div class="uploadBc of">
											<div class="tac">
												<span id="save_message">
													
												</span>
											</div>
											<div class="ml50 mt20 pl50">
												<a href="javascript:void(0)" title="" class="comm-btn c-btn-7" onclick="updateImg(${user.userId})">保 存</a>
											</div>
										</div>
									</section>
								</section>
							</section>
							<!-- /修改个人头像 -->
						</div>
						<input type="button" class="commBtn bgGreen w80 ml50" id="deleImage" style="display: none">
						<!--修改头像，结束-->
						<form method="post" name="photoForm">
							<input id="photoPath" type="hidden" value="" />
							<input type="hidden" value="1" id="picture_width" />
							<input type="hidden" value="1" id="picture_height" />
							<input type="hidden" value="82" id="txt_top" />
							<input type="hidden" value="73" id="txt_left" />
							<input type="hidden" value="120" id="txt_DropWidth" />
							<input type="hidden" value="120" id="txt_DropHeight" />
							<input type="hidden" id="txt_Zoom" />
						</form>
					</div>
					<!--修改头像，结束-->
					
					<!--修改密码，开始-->
					<div class="u-account-box undis">
						<form method="post" name="pwdForm" id="pwdForm">
							<input type="hidden" name="user.userId" value="${user.userId}" />
							<ol class="u-account-li">
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">旧密码</span>
									</label>
									<input type="password" class="u-a-inpt" name="nowPassword" value="" placeholder="" maxlength="16">
									<span class="u-a-error"></span>
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">新密码</span>
									</label>
									<input type="password" class="u-a-inpt" name="newPassword" placeholder="" maxlength="16">
									<span class="u-a-error"></span>
								</li>
								<li>
									<label class="u-a-title">
										<span class="fsize16 c-999">新密码确认</span>
									</label>
									<input type="password" class="u-a-inpt" name="confirmPwd" value="" placeholder="" maxlength="16">
									<span class="u-a-error"></span>
								</li>
							</ol>
						</form>
						<div class="ml50 mt50 pl50">
							<a href="javascript:void(0)" title="" class="comm-btn c-btn-7" onclick="updatePwd();">修 改</a>
							<a href="javascript:void(0)" title="" class="comm-btn c-btn-7" onclick="javascript:$('#pwdForm').find('input').val('');">重 置</a>
						</div>
					</div>
					<!--修改密码，结束-->
				</div>
			</section>
			<!-- /我的资料 -->
		</div>
	</article>
	<!-- /右侧内容区 结束 -->
</body>
</html>