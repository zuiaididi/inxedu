<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学期列表</title>
<script type="text/javascript" src="${ctx}/static/admin/grade/term-list.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/grade/grade-term.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
</head>
<body>
	<div class="pad20">
	<form action="${ctx}/admin/term/gettermList" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
			
			<input type="hidden" id="gradeTermValue" value="${queryTerm.gradeId}" />
			<select id="gradeTerm" name="queryTerm.gradeId">
				<option value="0" selected="selected" >请选择学年</option>
			</select>
			<input type="hidden" id="termValue" value="${queryTerm.termId}" />
			<select id="term" name="queryTerm.termId">
				
			</select>		
			
			<a title="查找学期" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找学期
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);$('#term').empty();" class="button tooltip"
				href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
					<td align="center">学年</td>
					<td align="center">学期</td>
					<td align="center">开始时间</td>
					<td align="center">结束时间</td>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${termList}" var="term">
					<tr class="odd">
						<td align="center">${term.gradeName}</td>
						<td align="center">${term.termName}</td>
						<td align="center">
							<fmt:formatDate value="${term.termStartTime}" pattern="yyyy-MM-dd" />
						</td>
						<td align="center">
							<fmt:formatDate value="${term.termEndTime}" pattern="yyyy-MM-dd" />
						</td>
						<td align="center">
						<a href="javascript:void(0)" onclick="deleteTerm(${term.termId})" class="button tooltip">删除</a>
						<a href="javascript:void(0)" onclick="initTerm(${term.termId})" class="button tooltip">修改</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<!-- 修改学期窗口 ,开始-->
	<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改学期信息</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="updateTermForm">
				<input type="hidden" name="term.termId" value="0" />
				<table style="line-height: 35px;">
					<tr>
						<td>学期名：</td>
						<td>
							<input name="term.termName" type="text" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>开始时间：
						</td>
						<td>
							<input  name="term.termStartTime" 
				                    id="termStartTime" type="text" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font>结束时间：
						</td>
						<td>
							<input  name="term.termEndTime" 
				                    id="termEndTime" type="text" readonly="readonly" />
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
			<button class="ui-state-default ui-corner-all" onclick="updateTerm()" type="button">确定</button>
			<button class="ui-state-default ui-corner-all closeBut" type="button">取消</button>
		</div>
	</div>
	<!-- 修改学期窗口 ,结束-->
</body>
</html>