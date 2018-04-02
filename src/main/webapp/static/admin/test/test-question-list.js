$(function(){
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
});
    function closedData(){
	    $("#createWin input:text,#updateWin input:text").val('');
	    $("#addQuestionContent,#updateQuestionContent").val('');
	    $("#updateWin,#createWin").hide();
    }
	/** 
	 * 显示添加窗口 
	 */
	function showTestQuestionWin(testId){
		closedData();
		$("#addTestQuestionForm input[name='testQuestion.testId']").val(testId);
		$("#createWin").show();
	}
	
	function createTestQuestion(){
		var testId = $("#addTestQuestionForm input[name='testQuestion.testId']").val();
		var questionContent = $("#addQuestionContent").val();
		var questionScore = $("#addTestQuestionForm input[name='testQuestion.questionScore']").val();
		var questionSort = $("#addTestQuestionForm input[name='testQuestion.questionSort']").val();
		var reg = /^[1-9]\d*$/;
		if(questionContent==null || questionContent==""){
			alert("问题内容不能为空");
			return false;
		}
		if(questionScore==null || questionScore=="" || !reg.test(questionScore)){
			alert("分值必须为正整数");
			return false;
		}
		if(questionSort==null || questionSort=="" || !reg.test(questionSort)){
			alert("序号必须为正整数");
			return false;
		}
		$.ajax({
			url:baselocation+'/admin/testquestion/addTestQuestion',
			type:'post',
			dataType:'json',
			data:{"testQuestion.testId":testId,"testQuestion.questionContent":questionContent,"testQuestion.questionScore":questionScore,"testQuestion.questionSort":questionSort},
			success:function(result){
				if(result.success==true){
					closedData();
					alert(result.message);
					window.location.reload();
				}else{
					alert(result.message);
				}

			},
			error:function(error){
				alert("系统繁忙，请稍后再操作！");
			}
		});
	}

	/**
	 * 初始化修改测验
	 */
	function initTestQuestion(questionId){
		closedData();
		$.ajax({
			url:baselocation+'/admin/testquestion/initUpdateTestQuestion/'+questionId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					var testQuestion = result.entity;
					$("#updateTestQuestionForm input[name='testQuestion.questionId']").val(testQuestion.questionId);
					$("#updateQuestionContent").val(testQuestion.questionContent);
					$("#updateTestQuestionForm input[name='testQuestion.questionScore']").val(testQuestion.questionScore);
					$("#updateTestQuestionForm input[name='testQuestion.questionSort']").val(testQuestion.questionSort);
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
	 * 修改测验信息
	 */
	function updateTestQuestion(){
		var questionId = $("#updateTestQuestionForm input[name='testQuestion.questionId']").val();
		var questionContent = $("#updateQuestionContent").val();
		var questionScore = $("#updateTestQuestionForm input[name='testQuestion.questionScore']").val();
		var questionSort = $("#updateTestQuestionForm input[name='testQuestion.questionSort']").val();
		var reg = /^[1-9]\d*$/;
		if(questionContent==null || questionContent==""){
			alert("问题内容不能为空");
			return false;
		}
		if(questionScore==null || questionScore=="" || !reg.test(questionScore)){
			alert("分值必须为正整数");
			return false;
		}
		if(questionSort==null || questionSort=="" || !reg.test(questionSort)){
			alert("序号必须为正整数");
			return false;
		}
		$.ajax({
			url:baselocation+'/admin/testquestion/updateTestQuestion',
			type:'post',
			dataType:'json',
			data:{"testQuestion.questionId":questionId,"testQuestion.questionContent":questionContent,"testQuestion.questionScore":questionScore,"testQuestion.questionSort":questionSort},
			success:function(result){
				if(result.success==true){
					closedData();
					alert(result.message);
					window.location.reload();
				}else{
					alert(result.message);
				}
			},
			error:function(error){
				alert("系统繁忙，请稍后再操作！");
			}
		});
	}

	
	/**
	 * 删除测验
	 */
	function deleteTestQuestion(questionId){
		closedData();
		if(confirm("确定删除吗？")){
			$.ajax({
				url:baselocation+'/admin/testquestion/deleteTestQuestion/'+questionId,
				data:{},
				type:'post',
				dataType:'json',
				success:function(result){
					if(result.success==true){
						alert("删除成功");
						window.location.reload();
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
	}

