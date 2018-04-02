<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测验列表</title>
<script type="text/javascript" src="${ctx}/static/admin/test/test-grade-list.js"></script>
<script type="text/javascript" src="${ctx}/static/admin/organization/organization.js"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

</head>
<body>
	<div class="pad20">
	<form action="${ctx}/admin/test/getTestSubmitGradeList/${test.testId}" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
	
			<input type="hidden" id="collegeValue" value="${queryTestSubmitGrade.collegeId}" />
			<select id="college" name="queryTestSubmitGrade.collegeId">
				<option value="0" selected="selected" >请选择学院</option>
			</select>
			<input type="hidden" id="majorValue" value="${queryTestSubmitGrade.majorId}" />
			<select id="major" name="queryTestSubmitGrade.majorId">
				
			</select>
			<input type="hidden" id="gradeValue" value="${queryTestSubmitGrade.gradeId}" />
			<select id="grade" name="queryTestSubmitGrade.gradeId">
				
			</select>
			<input type="hidden" id="classValue" value="${queryTestSubmitGrade.classId}" />
			<select id="class" name="queryTestSubmitGrade.classId">
				
			</select>
				
			<a title="查找" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('');$('#searchForm select').val(0);$('#major').empty();$('#grade').empty();$('#class').empty()" class="button tooltip"
				href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
			<a title="下载全部" onclick="downLoadAll(${test.testId})" class="button tooltip" href="javascript:void(0)">
			   <span class="ui-icon ui-icon-cancel"></span>     
			        下载全部
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
				    <td align="center">学号</td>
				    <td align="center">姓名</td>
					<td align="center">总分</td>
					<c:forEach var="x" begin="1" end="${test.questionNumber }" step="1">
					<td align="center">第${x}题</td>
					<td align="center">时间</td>
					</c:forEach>
					<td align="center">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${testGradeDtoList}" var="testGradeDto">
					<tr class="odd">
					    <td align="center">${testGradeDto.studentNumber}</td>
					    <td align="center">${testGradeDto.userName}</td>
					    <c:if test="${testGradeDto.testSubmitTime==null }">
					        <td align="center"><font color="red">未提交</font></td>
					    </c:if>
					    <c:if test="${testGradeDto.testSubmitTime!=null }">
					    <c:if test="${testGradeDto.testCreateGradeTime==null }">
					        <td align="center"><font color="red">未打分</font></td>
					    </c:if>
					    <c:if test="${testGradeDto.testCreateGradeTime!=null }">
					        <td align="center">${testGradeDto.testGrade}/${testGradeDto.testScore }</td>
					    </c:if>
					    </c:if>
					    <c:forEach items="${testGradeDto.testQuestionDtoList}" var="questionDto">
					    <c:if test="${questionDto.questionSubmitTime==null }">
					        <td align="center"><font color="red">未提交</font></td>
					        <td align="center">--</td>
					    </c:if>
					    <c:if test="${questionDto.questionSubmitTime!=null }">
					    <c:if test="${questionDto.questionCreateGradeTime==null }">
					        <td align="center"><font color="red">未打分</font></td>
					        <td align="center">提交时间：<fmt:formatDate value="${questionDto.questionSubmitTime }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					    </c:if>
					    <c:if test="${questionDto.questionCreateGradeTime!=null }">
					        <td align="center">${questionDto.questionGrade}/${questionDto.questionScore }</td>
					        <td align="center">打分时间：<fmt:formatDate value="${questionDto.questionUpdateGradeTime }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
					    </c:if>
					    </c:if>
					    </c:forEach>
						<td align="center">
						    <c:if test="${testGradeDto.testSubmitTime!=null && isok==1}">
						    <a onclick="initTestGrade(${testGradeDto.testSubmitId})" class="button tooltip" href="javascript:void(0)">修改成绩</a>
						    </c:if>
						    <c:if test="${testGradeDto.testSubmitTime!=null }">
                            <a href="${ctx}/admin/test/testdownload/${testGradeDto.testSubmitId}" class="button tooltip">下载</a>
						    </c:if>
					    </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
	<!-- 修改成绩窗口 ,开始-->
	<div id="updateWin" aria-labelledby="ui-dialog-title-dialog" role="dialog" tabindex="-1"
		class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable"
		style="display: none; position: absolute; overflow: hidden; z-index: 1010; outline: 0px none; height: auto; width: 511px; top: 173px; left: 367px;">
		<div style="-moz-user-select: none;" unselectable="on" class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
			<span style="-moz-user-select: none;" unselectable="on" id="ui-dialog-title-dialog" class="ui-dialog-title">修改成绩信息</span>
			<a style="-moz-user-select: none;" unselectable="on" role="button" class="ui-dialog-titlebar-close ui-corner-all"
				href="javascript:void(0)">
				<span style="-moz-user-select: none;" unselectable="on" class="ui-icon ui-icon-closethick">close</span>
			</a>
		</div>
		<div style="height: 300px; min-height: 42px; width: auto;" class="ui-dialog-content ui-widget-content">
			<form id="updateTestGradeForm">
			    <input type="hidden" name="testGradeDto.testSubmitId" value="0" />
				<table style="line-height: 35px;" id="testGradeTable">
				   
					
				</table>
				<br>
				<br>
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
			<button class="ui-state-default ui-corner-all" onclick="ok()" type="button">确定</button>
		</div>
	</div>
	<!-- 修改成绩窗口 ,结束-->
</body>
</html>