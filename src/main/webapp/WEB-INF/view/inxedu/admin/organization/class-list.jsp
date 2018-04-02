<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级列表</title>
<script type="text/javascript" src="${ctx}/static/admin/organization/organization.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/organization/class-list.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/grade/grade-term.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/course/teacher-course.js"></script>
</head>
<body>
	<div class="pad20">
	<form action="${ctx}/admin/class/getclassList" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
			<input placeholder="学院/专业/年级/班级" type="text" name="queryClass.keyWord" value="${queryClass.keyWord}" />
			
			<input type="hidden" id="collegeValue" value="${queryClass.collegeId}" />
			<select id="college" name="queryClass.collegeId">
				<option value="0" selected="selected" >请选择学院</option>
			</select>
			<input type="hidden" id="majorValue" value="${queryClass.majorId}" />
			<select id="major" name="queryClass.majorId">
				
			</select>
			<input type="hidden" id="gradeValue" value="${queryClass.gradeId}" />
			<select id="grade" name="queryClass.gradeId">
				
			</select>
			<input type="hidden" id="classValue" value="${queryClass.classId}" />
			<select id="class" name="queryClass.classId">
				
			</select>
			
			<a title="查找班级" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找班级
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);$('#major').empty();$('#grade').empty();$('#class').empty()" class="button tooltip"
				href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
					<td align="center">学院</td>
					<td align="center">专业</td>
					<td align="center">年级</td>
					<td align="center">班级</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${classList}" var="clazz">
					<tr class="odd">
						<td align="center">${clazz.collegeName}</td>
						<td align="center">${clazz.majorName}</td>
						<td align="center">${clazz.gradeName}</td>
						<td align="center">${clazz.className}</td>
						<td align="center">
						<a href="${ctx}/admin/user/getUserByClass/${clazz.classId}" class="button tooltip">查看学生</a>
						<a href="javascript:void(0)" onclick="showWin(${clazz.classId},'${clazz.collegeName}','${clazz.majorName}','${clazz.gradeName}','${clazz.className}')" class="button tooltip">添加学生</a>
						<a href="javascript:void(0)" onclick="showTeacherWin(${clazz.classId},'${clazz.collegeName}','${clazz.majorName}','${clazz.gradeName}','${clazz.className}')" class="button tooltip">设置老师</a>
						<a href="${ctx}/admin/user/toBatchOpen/${clazz.classId}" class="button tooltip">批量导入学生</a>
						<a href="javascript:void(0)" onclick="deleteClass(${clazz.classId})" class="button tooltip">删除</a>
						<a href="javascript:void(0)" onclick="initClass(${clazz.classId})" class="button tooltip">修改</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<!-- 添加学生窗口 ,开始-->
	<div id="createWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">添加学生</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="addUserForm">
			    <input type="hidden" id="classId" name="user.classId" value="0" />
			    <p id="cmgc"></p>
				<table style="line-height: 35px;">
					<tr>
						<td>
							<font color="red">*</font>&nbsp;学号
						</td>
						<td>
							<input name="user.studentNumber" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>&nbsp;姓名
						</td>
						<td>
							<input name="user.userName" type="text" />
						</td>
					</tr><tr>
						<td>
							<font color="red">*</font>&nbsp;密码
						</td>
						<td>
							<input name="user.password" type="text" />
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
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
			<button class="ui-state-default ui-corner-all" onclick="createUser()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
		<!-- 添加学生窗口 ,结束-->
	</div>
	<!-- 设置老师窗口 ,开始-->
	<div id="createTeacherWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">设置老师</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="addTeacherForm">
			    <input type="hidden" id="classId" name="classId" value="0" />
			    <p id="cmgc"></p>
				<table style="line-height: 35px;">
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;选择老师</td>
						<td>
						    <select id="sysUser2" name="sysId">
				                <option value="0" selected="selected" >请选择老师</option>
			                 </select>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;选择课程</td>
						<td>
						    <select id="course2" name="courseId">
			                 </select>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;学年</td>
						<td>
							<select id="gradeTerm2" name="gradeId">
				            <option value="0" selected="selected" >请选择学年</option>
			                </select>
						</td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>&nbsp;学期</td>
						<td>
							<select id="term2" name="termId">
				            </select>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
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
			<button class="ui-state-default ui-corner-all" onclick="createTeacher()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
		<!-- 设置老师窗口 ,结束-->
	</div>
	<!-- 修改班级窗口 ,开始-->
	<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改班级信息</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="updateClassForm">
				<input type="hidden" name="clazz.classId" value="0" />
				<table style="line-height: 35px;">
					<tr>
						<td>班级名：</td>
						<td>
							<input name="clazz.className" type="text" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
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
			<button class="ui-state-default ui-corner-all" onclick="updateClass()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
	</div>
	<!-- 修改班级窗口 ,结束-->
</body>
</html>