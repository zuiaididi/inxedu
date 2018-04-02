<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测验问题列表</title>
<script type="text/javascript" src="${ctx}/static/admin/test/test-question-list.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

</head>
<body>
	<div class="pad20">
	<form action="${ctx}/admin/testquestion/getTestQuestionList/${testId}" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
			<a title="添加问题" onclick="showTestQuestionWin(${testId})" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-newwin"></span>
				添加问题
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
				    <td align="center">序号</td>
					<td align="center">问题分值</td>
					<td align="center">创建时间</td>
					<td align="center">更新时间</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			 <tbody>
		        <c:forEach items="${testQuestionList}" var="question">
					<tr class="odd">
					    <td align="center">${question.questionSort}</td>
						<td align="center">${question.questionScore}</td>
						<td align="center">
							<fmt:formatDate value="${question.questionCreateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td align="center">
							<fmt:formatDate value="${question.questionUpdateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
						<td align="center">
						    <a href="${ctx}/admin/testquestion/getTestQuestionGradeList/${question.questionId}" class="button tooltip">问题成绩详情</a>
						    <a onclick="initTestQuestion(${question.questionId})" class="button tooltip" href="javascript:void(0)">修改问题信息</a>
						    <a onclick="deleteTestQuestion(${question.questionId})" class="button tooltip" href="javascript:void(0)">删除问题</a>
					    </td>
					</tr>	
                </c:forEach>
			</tbody>
			
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<!-- 添加问题窗口 ,开始-->
	<div id="createWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">创建新问题</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="addTestQuestionForm">
			    <input type="hidden" name="testQuestion.testId" value="0" />
				<table style="line-height: 35px;">
					
					<font color="red">*</font>问题内容：
						
					<textarea id="addQuestionContent" rows="6" cols="50" ></textarea>
						
					<tr>
						<td>
							<font color="red">*</font>问题分值：
						</td>
						<td>
							<input name="testQuestion.questionScore" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>序号：
						</td>
						<td>
							<input name="testQuestion.questionSort" type="text" />
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
			<button class="ui-state-default ui-corner-all" onclick="createTestQuestion()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
		<!-- 添加问题窗口 ,结束-->
	</div>
	<!-- 修改问题窗口 ,开始-->
	<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改问题信息</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="updateTestQuestionForm">
				<input type="hidden" name="testQuestion.questionId" value="0" />
				<table style="line-height: 35px;">
					<tr>
					问题内容:
					<textarea id="updateQuestionContent" rows="6" cols="60" ></textarea>
					<tr>
						<td>
							问题分值：
						</td>
						<td>
							<input name="testQuestion.questionScore" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							序号：
						</td>
						<td>
							<input name="testQuestion.questionSort" type="text" />
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
			<button class="ui-state-default ui-corner-all" onclick="updateTestQuestion()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
	</div>
	<!-- 修改问题窗口 ,结束-->
</body>
</html>