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
						<a href="javascript: void(0)" title="Wo的记录" style="cursor: default;">Wo的记录</a>
						<a href="javascript: void(0)" title="测验记录" class="current">测验记录</a>
					</section>
				</div>
				
				<div class="mt40">
					<c:if test="${empty testGradeDtoList}">
						<!-- /无数据提示 开始-->
						<section class="no-data-wrap">
							<em class="icon30 no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">您还没有任何测验记录哦！</span>
						</section>
						<!-- /无数据提示 结束-->
					</c:if>
					<c:if test="${not empty testGradeDtoList}">
						<div class="u-sys-news u-collection-list">
							<form action="">
								<c:forEach items="${testGradeDtoList}" var="testGradeDto" varStatus="index">
									<li>
											<h3 class="hLh30 txtOf">
												<a href="${ctx }/front/test/testinfo/${testGradeDto.testId}" title="${testGradeDto.testTitle }" class="i-art-title">${testGradeDto.testTitle }</a>
											</h3>
											<h4>
											    <c:if test="${testGradeDto.testCreateGradeTime==null}">
			
										        <font color="red">未打分</font>
									            
									            </c:if>
									            <c:if test="${testGradeDto.testCreateGradeTime!=null}">
												得分：${testGradeDto.testGrade }
												</c:if>
											</h4>
											<section>
												<p>
													老师：<span class="c-999 f-fA">${testGradeDto.teacherName }</span>&nbsp;
													课程：<span class="c-999 f-fA">${testGradeDto.courseName }</span>&nbsp;
													分值：<span class="c-999 f-fA">${testGradeDto.testScore }</span>&nbsp;
													提交时间：<span><fmt:formatDate value="${testGradeDto.testSubmitTime }" pattern="yyyy-MM-dd HH:mm" /></span>
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
					<form action="${ctx}/uc/courseHistory" method="post" id="searchForm">
						<input type="hidden" name="page.currentPage" value="1" id="pageCurrentPage" />
					</form>
				</div>
			</section>
			<!-- /Wo的消息 -->
		</div>
	</article>
	<!-- /右侧内容区 结束 -->
</body>
</html>