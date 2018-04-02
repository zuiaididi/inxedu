<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户观看历史</title>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
</head>
<body>
	<div class="pad20">
	<form action="${ctx}/admin/user/lookuserhistory/${userId}" method="post" id="searchForm">
	        <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
			观看时间:
			<input placeholder="开始时间" name="queryCourseStudyHistory.beginTime"
				value="<fmt:formatDate value="${queryCourseStudyHistory.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beginTime" type="text"
				readonly="readonly" style="width: 128px;"/>
			-
			<input placeholder="结束时间" id="endTime" name="queryCourseStudyHistory.endTime"
				value="<fmt:formatDate value="${queryCourseStudyHistory.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" readonly="readonly" style="width: 128px;"/>
			<a title="查找" onclick="javascript:$('#searchForm').submit();" class="button tooltip" href="javascript:void(0)">
				<span class="ui-icon ui-icon-search"></span>
				查找
			</a>
			<a title="清空" onclick="javascript:$('#searchForm input:text').val('')" class="button tooltip"
				href="javascript:void(0)">
				<span class="ui-icon ui-icon-cancel"></span>
				清空
			</a>
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
					<td align="center">课程名称</td>
					<td align="center">节点名称</td>
					<td align="center">教师名称</td>
					<td align="center">课程上传者</td>
					<td align="center">观看时间</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${historyList}" var="history">
					<tr class="odd">
						<td align="center">${history.courseName}</td>
						<c:if test = "${history.parentName == null}">
						<td align="center">${history.kpointName}</td>
						</c:if>
						<c:if test = "${history.parentName != null}">
						<td align="center">${history.parentName} - ${history.kpointName}</td>
						</c:if>
						<td align="center">${history.teacherName}</td>
						<td align="center">${history.sysName}</td>
						<td align="center">
							<fmt:formatDate value="${history.updateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
<script type="text/javascript">
$(function(){
	$("#beginTime,#endTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });
})
</script>
</body>
</html>