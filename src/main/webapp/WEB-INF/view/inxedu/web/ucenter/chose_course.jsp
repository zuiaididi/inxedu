<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的测验记录</title>
</head>
<body>
	<article class="col-7 fl">
		<div class="u-r-cont">
			<section>
				<div>
					<section class="c-infor-tabTitle c-tab-title">
						<a href="javascript: void(0)" title="选课记录" style="cursor: default;">选课记录</a>
						<a href="${ctx }/uc/chosecourse/1" title="当前学期课程" id="now">当前学期选课</a>
						<a href="${ctx }/uc/chosecourse/0" title="其他学期课程" id="other">其他学期选课</a>
					</section>
				</div>
				
				<div class="mt40">
					<c:if test="${empty userTeacherDtoList}">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">您还没有任何选课记录哦！</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty userTeacherDtoList}">
						<div class="u-sys-news u-collection-list">
							<form action="">
								<c:forEach items="${userTeacherDtoList}" var="userTeacherDto" varStatus="index">
									<li>
											<h3 class="hLh30 txtOf">
												<a href="${ctx}/front/couinfo/${userTeacherDto.courseId}" title="${userTeacherDto.courseName}" class="i-art-title">${userTeacherDto.courseName}</a>
											</h3>
											<section>
												<p>
													老师：<span class="c-999 f-fA">${userTeacherDto.teacherName }</span>&nbsp;
													学期：<span class="c-999 f-fA">${userTeacherDto.gradeName }${userTeacherDto.termName }</span>&nbsp;
												</p>
											</section>
											<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo">
											<span>&nbsp;</span>
											</section>
										</li>
								</c:forEach>
							</form>
						</div>
					</c:if>
					<!-- 公共分页 开始 -->
					<jsp:include page="/WEB-INF/view/common/front_page.jsp" />
					<!-- 公共分页 结束 -->
					<form action="${ctx}/uc/chosecourse/${status}" method="post" id="searchForm">
						<input type="hidden" name="page.currentPage" value="1" id="pageCurrentPage" />
						<input type="hidden" value="${status}" id="status" />
					</form>
				</div>
			</section>
			<!-- /Wo的消息 -->
		</div>
	</article>
	<!-- /右侧内容区 结束 -->
	<script type="text/javascript">
	$(function(){
		var status = $("#status").val();
		if(status == 1){
			$("#now").removeClass("current").siblings().removeClass("current");
			$("#now").addClass("current");
		}
		else if(status == 0){
			$("#other").removeClass("current").siblings().removeClass("current");
			$("#other").addClass("current");
		}
	})
	</script>
</body>
</html>