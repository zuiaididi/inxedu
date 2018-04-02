<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>测验列表</title>
<script type="text/javascript">
$(function(){
	var currentTime = $("#currentTime").val();
	$(".timeStatus").each(function(){
		var startTime=$(this).find("input[name='startTime']").val();
		var endTime=$(this).find("input[name='endTime']").val();
    	if(new Date(currentTime) < new Date(startTime)){
    		$(this).append("<font color='red'>未开始</font>");
			return true;
		}
    	if(new Date(currentTime) > new Date(endTime)){
    		$(this).append("<font color='red'>已结束</font>");
			return true;
		}
    	$(this).append("<font color='green'>正在进行</font>");
    	return true;
	});
});
function queryTest(status){
	$("#status").val(status);
	$("#searchForm").submit();
}
</script>
</head>
<body>
	<div id="aCoursesList" class="bg-fa of">
		<section class="container">
			<header class="comm-title">
				<h2 class="fl tac">
					<span class="c-333">测验列表</span>
				</h2>
			</header>
			<section class="c-sort-box">
			<div class="js-wrap">
					<section class="fr">
						<span class="c-ccc"> <tt class="c-master f-fM">${page.currentPage}</tt>/<tt class="c-666 f-fM">${page.totalPageSize}</tt>
						</span>
					</section>
					<section class="fl">
						<ol class="js-tap clearfix">
							<li><a title="正在进行" onclick="queryTest(2)" class="button tooltip" href="javascript:void(0)">
				            <span class="ui-icon ui-icon-newwin"></span>
				                               正在进行
			                </a></li>
			                <li><a title="已经结束" onclick="queryTest(3)" class="button tooltip" href="javascript:void(0)">
				            <span class="ui-icon ui-icon-newwin"></span>
				                               已经结束
			                </a></li>
						</ol>
					</section>
				</div>
			          <div class="mt40">  
						<!-- /无数据提示 开始-->
						<c:if test="${empty testList }">
							<section class="no-data-wrap">
								<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据...</span>
							</section>
						</c:if>
						<!-- /无数据提示 结束-->
						<article class="q-list">
							<!-- /测验列表 开始-->
							<ul>
								<c:if test="${not empty testList }">
									<c:forEach var="test" items="${testList }">
										<li>
											<h3 class="hLh30 txtOf">
												<a href="${ctx }/front/test/testinfo/${test.testId}" title="${test.testTitle }" class="i-art-title">${test.testTitle }</a>
											</h3>
											<div class="timeStatus">
											    <input type="hidden" name="startTime" value="${test.testStartTime }">
												<input type="hidden" name="endTime" value="${test.testEndTime }">
											</div>	
											<section>
												<p>
													老师：<span class="c-999 f-fA">${test.teacherName }</span>
													课程：<span class="c-999 f-fA">${test.courseName }</span>
													学期：<span class="c-999 f-fA">${test.gradeName }${test.termName }</span>
													分值：<span class="c-999 f-fA">${test.testScore }</span>
												</p>
											</section>
											<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo">
												<span class="fr">
												    <tt class="c-999 f-fM">
														<!-- 1小时前发布 -->
														创建时间：<fmt:formatDate value="${test.testCreateTime }" pattern="yyyy-MM-dd HH:mm" />
														更新时间：<fmt:formatDate value="${test.testUpdateTime }" pattern="yyyy-MM-dd HH:mm" />
													</tt></span>
												<div class="fl">
													<span> 
													          开始时间：<fmt:formatDate value="${test.testStartTime }" pattern="yyyy-MM-dd HH:mm" />
														结束时间：<fmt:formatDate value="${test.testEndTime }" pattern="yyyy-MM-dd HH:mm" />
													</span>
												</div>
											</section>
											<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo">
											<span>&nbsp;</span>
											</section>
										</li>
									</c:forEach>
								</c:if>
							</ul>
							<!-- /测验列表 结束-->
						</article>
						</div>
						<!-- 公共分页 开始 -->
						<form action="${ctx }/front/test/testlist" method="post" id="searchForm">
							<input type="hidden" name="page.currentPage" id="pageCurrentPage" value="1">
							<input type="hidden" name="queryTest.status" id="status" value="0">
							<input type="hidden" id="currentTime" value="${currentTime}">
						</form>
						<div>
							<jsp:include page="/WEB-INF/view/common/front_page.jsp"></jsp:include>
							<div class="clear"></div>
						</div>
						<!-- 公共分页 结束 -->
			</section>
		</section>
		<!-- /测验列表 结束 -->
	</div>
</body>
</html>