<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员列表</title>
<script type="text/javascript" src="${ctx}/static/admin/user/user.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/organization/organization.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/grade/grade-term.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/course/teacher-course.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
</head>
<body>
	<div class="pad20">
		<form action="${ctx}/admin/user/getuserList" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
			<input placeholder="邮箱/手机/学号/姓名" type="text" name="queryUser.keyWord" value="${queryUser.keyWord}" />
			<select name="queryUser.isavalible">
				<option value="0">请选择状态</option>
				<option <c:if test="${queryUser.isavalible==1}"> selected="selected" </c:if> value="1">正常</option>
				<option <c:if test="${queryUser.isavalible==2}"> selected="selected" </c:if> value="2">冻结</option>
			</select>
			注册时间:
			<input placeholder="开始注册时间" name="queryUser.beginCreateTime"
				value="<fmt:formatDate value="${queryUser.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beginCreateTime" type="text"
				readonly="readonly" style="width: 128px;"/>
			-
			<input placeholder="结束注册时间" id="endCreateTime" name="queryUser.endCreateTime"
				value="<fmt:formatDate value="${queryUser.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" readonly="readonly" style="width: 128px;"/>
			<a title="查找学员" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找学员
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);$('#major').empty();$('#grade').empty();$('#class').empty()" class="button tooltip"
				href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
			<a title="导出Excel" onclick="userExcel()" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				导出Excel
			</a>
			<a title="设置老师" class="button tooltip" href="${ctx}/admin/user/toadduserteacher">
				<span class="ui-icon ui-icon-cancel"></span>
				设置老师
			</a>
			<input type="hidden" id="collegeValue" value="${queryUser.collegeId}" />
			<select id="college" name="queryUser.collegeId">
				<option value="0" selected="selected" >请选择学院</option>
			</select>
			<input type="hidden" id="majorValue" value="${queryUser.majorId}" />
			<select id="major" name="queryUser.majorId">
				
			</select>
			<input type="hidden" id="gradeValue" value="${queryUser.gradeId}" />
			<select id="grade" name="queryUser.gradeId">
				
			</select>
			<input type="hidden" id="classValue" value="${queryUser.classId}" />
			<select id="class" name="queryUser.classId">
				
			</select>
			
			<input type="hidden" id="gradeTermValue" value="${queryUser.gradeTermId}" />
			<select id="gradeTerm" name="queryUser.gradeTermId">
				<option value="0" selected="selected" >请选择学年</option>
			</select>
			<input type="hidden" id="termValue" value="${queryUser.termId}" />
			<select id="term" name="queryUser.termId">
				
			</select>		
			
			<input type="hidden" id="sysUserValue" value="${queryUser.sysId}" />
			<select id="sysUser" name="queryUser.sysId">
				<option value="0" selected="selected" >请选择老师（必选）</option>
			</select>
			<input type="hidden" id="courseValue" value="${queryUser.courseId}" />
			<select id="course" name="queryUser.courseId">
				
			</select>	
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
					<td align="center">学号</td>
					<td align="center">姓名</td>
					<td align="center">学院专业</td>
					<td align="center">班级</td>
					<td align="center">邮箱</td>
					<td align="center">手机号</td>
					<td align="center">昵称</td>
					<td align="center">性别</td>
					<td align="center">出生日期</td>
					<td align="center">注册时间</td>
					<td align="center">状态</td>
					<td align="center" width="285">操作</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr class="odd">
					    <td align="center">${user.studentNumber}</td>
					    <td align="center">
							<c:choose>
								<c:when test="${user.userName!=null && user.userName!=''}">
								 ${user.userName}
					            </c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${user.classId!=null && user.classId!=0}">
								 ${user.collegeName} - ${user.majorName}
					            </c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${user.classId!=null && user.classId!=0}">
								 ${user.gradeName}级${user.className}班
					            </c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${user.email!=null && user.email!=''}">
								 ${user.email}
					            </c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${user.mobile!=null && user.mobile!=''}">
								 ${user.mobile}
					            </c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:choose>
								<c:when test="${user.showName!=null && user.showName!=''}">
					${user.showName}
					</c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						</td>
						<td align="center">
							<c:if test="${user.sex==0}">--</c:if>
							<c:if test="${user.sex==1}">男</c:if>
							<c:if test="${user.sex==2}">女</c:if>
						</td>
						<td align="center">
						<c:choose>
								<c:when test="${user.birthday!=null}">
					            <fmt:formatDate value="${user.birthday}" pattern="yyyy/MM/dd" />
					            </c:when>
								<c:otherwise>--</c:otherwise>
							</c:choose>
						    
						</td>
						<td align="center">
							<fmt:formatDate value="${user.createTime}" pattern="yyyy/MM/dd HH:mm" />
						</td>
						<td align="center">
							<c:if test="${user.isavalible==1}">正常</c:if>
							<c:if test="${user.isavalible==2}">冻结</c:if>
						</td>
						<td align="center">
							<a href="javascript:void(0)" onclick="initUpdatePwd(${user.userId})" class="button tooltip">修改密码</a>
							<samp id="frozenOrThaw${user.userId}">
								<c:if test="${user.isavalible==1}">
									<a href="javascript:void(0)" onclick="frozenOrThaw(${user.userId},2,this)" class="button tooltip">冻结</a>
								</c:if>
								<c:if test="${user.isavalible==2}">
									<a href="javascript:void(0)" onclick="frozenOrThaw(${user.userId},1,this)" class="button tooltip">解冻</a>
								</c:if>
							</samp>
							<a href="${ctx}/admin/user/lookuserlog/${user.userId}" class="button tooltip">查看日志</a>
							<a href="${ctx}/admin/user/lookuserhistory/${user.userId}" class="button tooltip">查看观看历史</a>
							<a href="javascript:void(0)" onclick="delUser(${user.userId})" class="button tooltip">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>

	<!-- 修改密码窗口 ,开始-->
	<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改用户密码</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="updateUserPwdForm">
				<input type="hidden" name="user.userId" value="0" />
				<table style="line-height: 35px;">
					<tr>
						<td>密码：</td>
						<td>
							<input name="user.password" type="password" />
						</td>
					</tr>
					<tr>
						<td>确定密码：</td>
						<td>
							<input name="passwords" type="password" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-n"></div>
		<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-e"></div>
		<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-s"></div>
		<div style="-moz-user-select: none;" unselectable="on" class="ui-resizable-handle ui-resizable-w"></div>
		<div unselectable="on" style="z-index: 1001; -moz-user-select: none;"
			class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se"></div>
		<div unselectable="on" style="z-index: 1002; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-sw"></div>
		<div unselectable="on" style="z-index: 1003; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-ne"></div>
		<div unselectable="on" style="z-index: 1004; -moz-user-select: none;" class="ui-resizable-handle ui-resizable-nw"></div>
		<div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
			<button class="ui-state-default ui-corner-all" onclick="updateUserPwd()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
	</div>
	<!-- 修改密码窗口 ,结束-->
</body>
</html>