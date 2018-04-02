<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>测验成绩</title>
<script src="${ctx }/static/inxweb/comment/comment.js" type="text/javascript"></script>

</head>
<body>
	<div class="bg-fa of">
		<section class="container">
			<section class="i-article">
				<div class="fl col-7">
					<section class="mr30">
						<section class="path-wrap txtOf hLh30">
							<a href="${ctx }" title="首页" class="c-999 fsize14">首页</a>
							\
							<a href="${ctx}/front/test/testlist" title="" class="c-999 fsize14">测验列表</a>
							\ <a href="${ctx}/front/test/testinfo/${testId}" title="" class="c-999 fsize14">${test.testTitle }</a>
							 <span class="c-333 fsize14">测验成绩</span>
						</section>
						<article class="article-infor-box pr20">
							<h3 class="hLh30 txtOf">
								<span class="i-art-title">测验成绩</span>
							</h3>
							<section class="hLh30 txtOf mt5 pb20 mreadnum">
							      
							</section>
							<c:if test="${testGrade!=null}">
							<c:if test="${not empty testQuestionDtoList }">
								<c:forEach var="testQuestionDto" items="${testQuestionDtoList }">
								<div id="art-infor-body">第${testQuestionDto.questionSort }题：
								 <c:if test="${testQuestionDto.questionSubmitTime==null}">
									   <tt class="c-999 f-fM ml20">
										      <font color="red">未提交</font>
									    </tt>       
							     </c:if>
								 <c:if test="${testQuestionDto.questionSubmitTime!=null }">
									  <c:if test="${testQuestionDto.questionCreateGradeTime==null }">
									   <tt class="c-999 f-fM ml20">
										      <font color="red">未打分</font>
									    </tt>  
									   </c:if>   
									   <c:if test="${testQuestionDto.questionCreateGradeTime!=null }">
									   ${testQuestionDto.questionGrade }
								       /
								       ${testQuestionDto.questionScore }  
									   </c:if>   
							     </c:if>	    
								
							</div>
								</c:forEach>
							</c:if>
							    <div id="art-infor-body">
									
										总分：${testGrade.testGrade }/${testGrade.testScore }
										
								</div>
							</c:if>
							<hr>
						</article>
					</section>
				</div>
				<div class="clear"></div>
			</section>
		</section>
		<!-- /测验成绩 结束 -->
	</div>
</body>
</html>