<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>观看历史</title>
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
	<form action="${ctx}/admin/cou/lookcoursehistory/0" method="post" id="searchForm">
			<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			<input type="hidden" id="pagePageSize" name="page.pageSize" value="${page.pageSize}" />
			观看时间:
			<input placeholder="开始时间" name="queryCourseStudyHistory.beginTime"
				value="<fmt:formatDate value="${queryCourseStudyHistory.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="beginTime" type="text"
				readonly="readonly" style="width: 128px;"/>
			-
			<input placeholder="结束时间" id="endTime" name="queryCourseStudyHistory.endTime"
				value="<fmt:formatDate value="${queryCourseStudyHistory.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text" readonly="readonly" style="width: 128px;"/>
			<input type="hidden" id="collegeValue" value="${queryCourseStudyHistory.collegeId}" />
			<select id="college" name="queryCourseStudyHistory.collegeId">
				<option value="0" selected="selected" >请选择学院</option>
			</select>
			<input type="hidden" id="majorValue" value="${queryCourseStudyHistory.majorId}" />
			<select id="major" name="queryCourseStudyHistory.majorId">
				
			</select>
			<input type="hidden" id="gradeValue" value="${queryCourseStudyHistory.gradeId}" />
			<select id="grade" name="queryCourseStudyHistory.gradeId">
				
			</select>
			<input type="hidden" id="classValue" value="${queryCourseStudyHistory.classId}" />
			<select id="class" name="queryCourseStudyHistory.classId">
				
			</select>
			
			<input type="hidden" id="gradeTermValue" value="${queryCourseStudyHistory.gradeTermId}" />
			<select id="gradeTerm" name="queryCourseStudyHistory.gradeTermId">
				<option value="0" selected="selected" >请选择学年</option>
			</select>
			<input type="hidden" id="termValue" value="${queryCourseStudyHistory.termId}" />
			<select id="term" name="queryCourseStudyHistory.termId">
				
			</select>	
			
			<input type="hidden" id="sysUserValue" value="${queryCourseStudyHistory.teacherId}" />
			<select id="sysUser" name="queryCourseStudyHistory.teacherId">
				<option value="0" selected="selected" >请选择老师（必选）</option>
			</select>
			<input type="hidden" id="courseValue" value="${queryCourseStudyHistory.courseId}" />
			<select id="course" name="queryCourseStudyHistory.courseId">
				
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
			
		</form>
		<table cellspacing="0" cellpadding="0" border="0" class="fullwidth">
			<thead>
				<tr>
				    <td align="center">学号</td>
					<td align="center">用户姓名</td>
					<td align="center">邮箱</td>
					<td align="center">电话</td>
					<td align="center">学院专业</td>
					<td align="center">班级</td>
					<td align="center">节点名称</td>
					<td align="center">教师名称</td>
					<td align="center">观看时间</td>
				</tr>
			</thead>
			<tbody>
			    <c:set var="flag" value="0"/>
			    <c:set var="idValue" value="0"/>
				<c:forEach items="${historyList}" var="history">
				    <c:if test="${idValue != history.userId}">
				        <c:set var="idValue" value="${history.userId}"/>
				        <c:set var="flag" value="${1 - flag}"/>
				    </c:if>
				    <c:if test="${flag == 1}">
				    <c:if test="${history.kpointId == null || history.kpointId==0}">
					<tr class="odd" style="background-color:#EAEAEA">
					    <td align="center" style="color:red">${history.studentNumber}</td>
						<td align="center" style="color:red">${history.userName}</td>
						<td align="center" style="color:red">${history.email}</td>
						<td align="center" style="color:red">${history.mobile}</td>
						<c:if test = "${history.classId == null || history.classId == 0}">
						<td align="center" style="color:red">--</td>
						<td align="center" style="color:red">--</td>
						</c:if>
						<c:if test = "${history.classId != null && history.classId != 0}">
						<td align="center" style="color:red">${history.collegeName}-${history.majorName}</td>
						<td align="center" style="color:red">${history.gradeName}级${history.className}班</td>
						</c:if>
						<c:if test = "${history.parentName == null}">
						<td align="center" style="color:red">${history.kpointName}</td>
						</c:if>
						<c:if test = "${history.parentName != null}">
						<td align="center" style="color:red">${history.parentName} - ${history.kpointName}</td>
						</c:if>
						<td align="center" style="color:red">${history.teacherName}</td>
						<td align="center" style="color:red">
							<fmt:formatDate value="${history.updateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
					</tr>
					</c:if>
					<c:if test="${history.kpointId != null && history.kpointId != 0}">
					<tr class="odd" style="background-color:#EAEAEA">
					    <td align="center" style="color:black">${history.studentNumber}</td>
						<td align="center" style="color:black">${history.userName}</td>
						<td align="center" style="color:black">${history.email}</td>
						<td align="center" style="color:black">${history.mobile}</td>
						<c:if test = "${history.classId == null || history.classId == 0}">
						<td align="center" style="color:black">--</td>
						<td align="center" style="color:black">--</td>
						</c:if>
						<c:if test = "${history.classId != null && history.classId != 0}">
						<td align="center" style="color:black">${history.collegeName} - ${history.majorName}</td>
						<td align="center" style="color:black">${history.gradeName}级${history.className}班</td>
						</c:if>
						<c:if test = "${history.parentName == null}">
						<td align="center" style="color:black">${history.kpointName}</td>
						</c:if>
						<c:if test = "${history.parentName != null}">
						<td align="center" style="color:black">${history.parentName} - ${history.kpointName}</td>
						</c:if>
						<td align="center" style="color:black">${history.teacherName}</td>
						<td align="center" style="color:black">
							<fmt:formatDate value="${history.updateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
					</tr>
					</c:if>
				    </c:if>
				    <c:if test="${flag == 0}">
				    <c:if test="${history.kpointId == null || history.kpointId==0}">
					<tr class="odd" style="background-color:white">
					    <td align="center" style="color:red">${history.studentNumber}</td>
						<td align="center" style="color:red">${history.userName}</td>
						<td align="center" style="color:red">${history.email}</td>
						<td align="center" style="color:red">${history.mobile}</td>
						<c:if test = "${history.classId == null || history.classId == 0}">
						<td align="center" style="color:red">--</td>
						<td align="center" style="color:red">--</td>
						</c:if>
						<c:if test = "${history.classId != null && history.classId != 0}">
						<td align="center" style="color:red">${history.collegeName}-${history.majorName}</td>
						<td align="center" style="color:red">${history.gradeName}级${history.className}班</td>
						</c:if>
						<c:if test = "${history.parentName == null}">
						<td align="center" style="color:red">${history.kpointName}</td>
						</c:if>
						<c:if test = "${history.parentName != null}">
						<td align="center" style="color:red">${history.parentName} - ${history.kpointName}</td>
						</c:if>
						<td align="center" style="color:red">${history.teacherName}</td>
						<td align="center" style="color:red">
							<fmt:formatDate value="${history.updateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
					</tr>
					</c:if>
					<c:if test="${history.kpointId != null && history.kpointId != 0}">
					<tr class="odd" style="background-color:white">
					    <td align="center" style="color:black">${history.studentNumber}</td>
						<td align="center" style="color:black">${history.userName}</td>
						<td align="center" style="color:black">${history.email}</td>
						<td align="center" style="color:black">${history.mobile}</td>
						<c:if test = "${history.classId == null || history.classId == 0}">
						<td align="center" style="color:black">--</td>
						<td align="center" style="color:black">--</td>
						</c:if>
						<c:if test = "${history.classId != null && history.classId != 0}">
						<td align="center" style="color:black">${history.collegeName}-${history.majorName}</td>
						<td align="center" style="color:black">${history.gradeName}级${history.className}班</td>
						</c:if>
						<c:if test = "${history.parentName == null}">
						<td align="center" style="color:black">${history.kpointName}</td>
						</c:if>
						<c:if test = "${history.parentName != null}">
						<td align="center" style="color:black">${history.parentName} - ${history.kpointName}</td>
						</c:if>
						<td align="center" style="color:black">${history.teacherName}</td>
						<td align="center" style="color:black">
							<fmt:formatDate value="${history.updateTime}" pattern="yyyy/MM/dd HH:mm:ss" />
						</td>
					</tr>
					</c:if>
				    </c:if>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
	</div>
<script type="text/javascript">
$(function(){
	var id = 0;
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