<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>测验详情</title>
<script src="${ctx }/static/inxweb/comment/comment.js" type="text/javascript"></script>
<script type="text/javascript">
    function upload(btn,startTime,endTime){
    	var testFile = btn.form.testFile.value;
    	if(testFile.length <= 0){
    	alert("请选择导入内容");
    	return false;
    	}
    	btn.form.submit();
    	return true;
    }
    function processFiles(target) {
    	 var files = target.files;
    	 var file = files[0];
    	 var name = file.name;
    	 var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
    	 var size = file.size;
    	 var fileSize = size/1024;
    	 if(fileName!="doc" && fileName!="docx" && fileName!="rar" && fileName!="zip" && fileName!="txt" && fileName!="java"){
    		 alert("文件格式错误！");
    		 target.value="";
             return
    	 }
    	 if(fileSize>2000){  
    	   alert("文件不能大于2M");
    	   target.value="";
    	   return
    	  }
    }
</script>
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
							\ <span class="c-333 fsize14">${test.testTitle }</span>
						</section>
						<article class="article-infor-box pr20">
							<h3 class="hLh30 txtOf">
								<span class="i-art-title">${test.testTitle }</span>
							</h3>
							<section class="hLh30 txtOf mt5 pb20 mreadnum">
							    <div class="fr">
									<span> 
									<c:if test="${testSubmit!=null}">
									<c:if test="${testGrade==null}">
									<tt class="c-999 f-fM ml20">
										<font color="red">未打分</font>
									</tt>
									</c:if>
									<c:if test="${testGrade!=null}">
									<form action="${ctx}/front/test/testgrade/${test.testId}" >  
                                         <input type="submit" value="查看成绩">
                                    </form>
									</c:if>
									</c:if>
									</span>
								</div>
								<div class="fl pt3"><tt class="c-999 f-fM">
										时间：<fmt:formatDate value="${test.testStartTime }" pattern="yyyy-MM-dd HH:mm" />
										——<fmt:formatDate value="${test.testEndTime }" pattern="yyyy-MM-dd HH:mm" />
								</div>
							</section>
							<c:if test="${not empty testQuestionDtoList }">
								<c:forEach var="testQuestionDto" items="${testQuestionDtoList }">
								<div id="art-infor-body">第${testQuestionDto.questionSort }题
								    <c:if test="${testQuestionDto.questionSubmitTime==null}">
									<tt class="c-999 f-fM ml20">
										<font color="red">未提交</font>
									</tt>
									</c:if>
									<c:if test="${testQuestionDto.questionSubmitTime!=null}">
									<tt class="c-999 f-fM ml20">
										<font color="green">已提交</font>
									</tt>
									</c:if>
									
								<div>
									<tt class="c-999 f-fM">
										分值：${testQuestionDto.questionScore }
									</tt> 
									<tt class="c-999 f-fM ml20">
										创建时间：<fmt:formatDate value="${testQuestionDto.questionCreateTime }" pattern="yyyy-MM-dd HH:mm" />
									</tt> 
									<tt class="c-999 f-fM ml20">
										更新时间：<fmt:formatDate value="${testQuestionDto.questionUpdateTime }" pattern="yyyy-MM-dd HH:mm" />
									</tt> 
								</div>
								${testQuestionDto.questionContent }
							</div>
							<section class="mt20 tac">
							    
							    <form action="${ctx}/front/test/testupload/${testQuestionDto.questionId}" method="post" enctype="multipart/form-data"">  
                                                                                                         选择文件: <input type="file" name="testFile" width="120px" onchange="processFiles(this)">  
                                        <input type="button" value="上传答案" class="btn btn-danger"  onclick="if(isLogin()){upload(this,'${test.testStartTime}','${test.testEndTime}')} else{lrFun();}"/> 
                                </form>    
							    <hr>
							     
							    <c:if test="${testQuestionDto.questionSubmitTime!=null }">  
							      <form action="${ctx}/front/test/testdownload/${testQuestionDto.questionId}" method="get">  
                                         <input type="submit" value="下载最近一次答案">  
                                  </form>    
                                <hr>
							    </c:if>    
							</section>
							&nbsp;&nbsp;&nbsp;
								</c:forEach>
							</c:if>	
						</article>
					</section>
				</div>
				<div class="clear"></div>
			</section>
		</section>
		<!-- /文章列表 结束 -->
	</div>
</body>
</html>