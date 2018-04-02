<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测验列表</title>
<script type="text/javascript" src="${ctx}/static/admin/test/test-list.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/grade/grade-term.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/course/teacher-course.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

</head>
<body>
	<div class="pad20">
	<form action="${ctx}/admin/test/getTestList/0" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
	
			<input type="hidden" id="statusValue" value="${queryTest.status}" />
			<select id="status" name="queryTest.status">
				<option value="0" selected="selected" >全部状态</option>
				<option value="1" >未开始</option>
				<option value="2" >正在进行</option>
				<option value="3" >已结束</option>
			</select>
			
			<input type="hidden" id="gradeTermValue" value="${queryTest.gradeTermId}" />
			<select id="gradeTerm" name="queryTest.gradeTermId">
				<option value="0" selected="selected" >请选择学年</option>
			</select>
			<input type="hidden" id="termValue" value="${queryTest.termId}" />
			<select id="term" name="queryTest.termId">
				
			</select>	
			
			<input type="hidden" id="sysUserValue" value="${queryTest.teacherId}" />
			<select id="sysUser" name="queryTest.teacherId">
				<option value="0" selected="selected" >请选择老师（必选）</option>
			</select>
			<input type="hidden" id="courseValue" value="${queryTest.courseId}" />
			<select id="course" name="queryTest.courseId">
				
			</select>
			<a title="查找" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);" class="button tooltip"
				href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
			<a title="添加测验" onclick="showTestWin(${courseId1})" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-newwin"></span>
				添加测验
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
				    <td align="center">序号</td>
				    <td align="center">测验名称</td>
					<td align="center">分值</td>
					<td align="center">创建时间</td>
					<td align="center">更新时间</td>
					<td align="center">开始时间</td>
					<td align="center">结束时间</td>
					<td align="center">教师名称</td>
					<td align="center">课程名称</td>
					<td align="center">学年学期</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${testList}" var="test">
					<tr class="odd">
					    <td align="center">${test.testSort}</td>
					    <td align="center">${test.testTitle}</td>
						<td align="center">${test.testScore}</td>
						<td align="center">
							<fmt:formatDate value="${test.testCreateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td align="center">
							<fmt:formatDate value="${test.testUpdateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td align="center">
							<fmt:formatDate value="${test.testStartTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td align="center">
							<fmt:formatDate value="${test.testEndTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td align="center">${test.teacherName}</td>
						<td align="center">${test.courseName}</td>
						<td align="center">${test.gradeName}${test.termName}</td>
						<td align="center">
						    <a href="${ctx}/admin/testquestion/getTestQuestionList/${test.testId}" class="button tooltip">查看问题</a>
						    <a href="${ctx}/admin/test/getTestSubmitGradeList/${test.testId}" class="button tooltip">学员测验详情</a>
						    <a onclick="initTest(${test.testId})" class="button tooltip" href="javascript:void(0)">修改测验信息</a>
						    <a onclick="deleteTest(${test.testId})" class="button tooltip" href="javascript:void(0)">删除测验</a>
					    </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<!-- 添加测验窗口 ,开始-->
	<div id="createWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">创建新测验</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="addTestForm">
			    <input type="hidden" name="test.courseId" value="0" />
				<table style="line-height: 35px;">
					<tr>
						<td>
							<font color="red">*</font>测验题目：
						</td>
						<td>
							<input name="test.testTitle" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>分值：
						</td>
						<td>
							<input name="test.testScore" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>开始时间：
						</td>
						<td>
							<input id="testStartTime1" placeholder="Start Time" name="test.testStartTime" 
							type="text" readonly="readonly" style="width: 128px;"/>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>结束时间：
						</td>
						<td>
							<input id="testEndTime1" placeholder="End Time" name="test.testEndTime" 
						    type="text" readonly="readonly" style="width: 128px;"/>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>序号：
						</td>
						<td>
							<input name="test.testSort" type="text" />
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
			<button class="ui-state-default ui-corner-all" onclick="createTest()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
		<!-- 添加测验窗口 ,结束-->
	</div>
	<!-- 修改测验窗口 ,开始-->
	<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改测验信息</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="updateTestForm">
				<input type="hidden" name="test.testId" value="0" />
				<table style="line-height: 35px;">
					<tr>
						<td>
							测验题目:
						</td>
						<td>
							<input name="test.testTitle" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							分值：
						</td>
						<td>
							<input name="test.testScore" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							开始时间：
						</td>
						<td>
							<input id="testStartTime2" placeholder="Start Time" name="test.testStartTime" 
							type="text" readonly="readonly" style="width: 128px;"/>
						</td>
					</tr>
					<tr>
						<td>
							结束时间：
						</td>
						<td>
							<input id="testEndTime2" placeholder="End Time" name="test.testEndTime" 
							type="text" readonly="readonly" style="width: 128px;"/>
						</td>
					</tr>
					<tr>
						<td>
							序号：
						</td>
						<td>
							<input name="test.testSort" type="text" />
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
			<button class="ui-state-default ui-corner-all" onclick="updateTest()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
	</div>
	<!-- 修改测验窗口 ,结束-->
</body>
</html>