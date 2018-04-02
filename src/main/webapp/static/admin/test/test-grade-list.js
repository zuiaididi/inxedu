$(function(){
	var statusValue = $("#statusValue").val();
	if(statusValue > 0){
		$("#status option[value='" + statusValue + "']").attr("selected","selected");
	}
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
});
    function closedData(){
	    $("#testGradeTable").empty();
	    $("#updateTestGradeForm input:text").val('');
	    $("#updateWin").hide();
    }

	/**
	 * 初始化修改成绩
	 */
	function initTestGrade(testSubmitId){
		closedData();
		$("#updateTestGradeForm input[name='testGradeDto.testSubmitId']").val(testSubmitId);
		$.ajax({
			url:baselocation+'/admin/test/initUpdateTestGrade/'+testSubmitId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					var testGradeDto = result.entity;
					var testQuestionDtoList = testGradeDto.testQuestionDtoList;
					for(var i in testQuestionDtoList){
						if(testQuestionDtoList[i].questionSubmitTime == null){
							$("#testGradeTable").append("<tr><td><input name='testQuestionDtoList["+ i +"].questionId' type='hidden' value='"+
									testQuestionDtoList[i].questionId+"'/></td><td>第"+testQuestionDtoList[i].questionSort+"题：</td>" + 
									"<td><font color='red'>未提交</font></td>" + 
									"<td>（分值："+testQuestionDtoList[i].questionScore+"）"+
							           "<input type='hidden' name='testQuestionDtoList["+ i +"].questionScore' value='"+
							           testQuestionDtoList[i].questionScore+"' } /></td></tr>");
						}
						else{
							if(testQuestionDtoList[i].questionCreateGradeTime == null){
								$("#testGradeTable").append("<tr><td><input name='testQuestionDtoList["+ i +"].questionId' type='hidden' value='"+
										testQuestionDtoList[i].questionId+"'/></td><td>第"+testQuestionDtoList[i].questionSort+"题：</td>" +
										"<td><input placeholder='未打分' name='testQuestionDtoList["+ i +"].questionGrade'" +
										"type='text'/></td><td>" +
										"<button onclick='updateTestGrade("+i+")' type='button'>修改</button></td>" + 
										"<td>（分值："+testQuestionDtoList[i].questionScore+"）"+
								           "<input type='hidden' name='testQuestionDtoList["+ i +"].questionScore' value='"+
								           testQuestionDtoList[i].questionScore+"' } /></td></tr>");
							}
							else{
								$("#testGradeTable").append("<tr><td><input name='testQuestionDtoList["+ i +"].questionId' type='hidden' value='"+
										testQuestionDtoList[i].questionId+"'/></td><td>第"+testQuestionDtoList[i].questionSort+"题：</td>" + 
										"<td><input name='testQuestionDtoList["+ i +"].questionGrade' type='text' value='"+
										testQuestionDtoList[i].questionGrade+"'/></td><td>" +
										"<button onclick='updateTestGrade("+i+")' type='button'>修改</button></td>" + 
										"<td>（分值："+testQuestionDtoList[i].questionScore+"）"+
								           "<input type='hidden' name='testQuestionDtoList["+ i +"].questionScore' value='"+
								           testQuestionDtoList[i].questionScore+"' } /></td></tr>");
							}
						}
					}
					$("#updateWin").show();
				}
				else{
					alert(result.message);
				}
			},
			error:function(error){
				alert("系统繁忙，请稍后再操作！");
			}
		});
		
	}
	/**
	 * 修改测验成绩
	 */
	function updateTestGrade(i){
		var reg = /^(0|[1-9]\d*)$/;
		var questionGrade = $("#updateTestGradeForm input[name='testQuestionDtoList["+i+"].questionGrade']").val();
		if(questionGrade==null || questionGrade=="" || !reg.test(questionGrade)){
			alert("分数必须为非负整数");
			return false;
		}
		var questionScore = $("#updateTestGradeForm input[name='testQuestionDtoList["+i+"].questionScore']").val();
		if(questionGrade>questionScore){
			alert("分数不能大于满分");
			return false;
		}
		var questionId = $("#updateTestGradeForm input[name='testQuestionDtoList["+i+"].questionId']").val();
		var testSubmitId = $("#updateTestGradeForm input[name='testGradeDto.testSubmitId']").val();
		$.ajax({
			url:baselocation+'/admin/test/updateTestGrade',
			type:'post',
			dataType:'json',
			data:{"testQuestionDto.questionGrade":questionGrade,"testQuestionDto.questionId":questionId,"testQuestionDto.testSubmitId":testSubmitId},
			success:function(result){
				if(result.success==true){
					alert(result.message);
				}else{
					alert(result.message);
				}
			},
			error:function(error){
				alert("系统繁忙，请稍后再操作！");
			}
		});
	}

	function ok(){
		closedData();
		window.location.reload();
	}
	/**
	 * 下载答案
	 */
	function downLoadAll(testId){
		$("#searchForm").prop("action","/admin/test/testdownloadall/"+testId);
		$("#searchForm").submit();
		$("#searchForm").prop("action","/admin/test/getTestSubmitGradeList/"+testId);
	}

