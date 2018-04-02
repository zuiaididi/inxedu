$(function(){
	var statusValue = $("#statusValue").val();
	if(statusValue > 0){
		$("#status option[value='" + statusValue + "']").attr("selected","selected");
	}
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
	/* 时间控件 */
	$("#testStartTime1,#testEndTime1,#testStartTime2,#testEndTime2").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });
});
    function closedData(){
	    $("#createWin input:text,#updateWin input:text").val('');
	    $("#updateWin,#createWin").hide();
    }
	/** 
	 * 显示添加窗口 
	 */
	function showTestWin(courseId){
		closedData();
		$("#addTestForm input[name='test.courseId']").val(courseId);
		$("#createWin").show();
	}
	
	function createTest(){
		var testTitle = $("#addTestForm input[name='test.testTitle']").val();
		var testScore = $("#addTestForm input[name='test.testScore']").val();
		var testStartTime = $("#addTestForm input[name='test.testStartTime']").val();
		var testEndTime = $("#addTestForm input[name='test.testEndTime']").val();
		var testSort = $("#addTestForm input[name='test.testSort']").val();
		var reg = /^[1-9]\d*$/;
		if(testTitle==null || testTitle==""){
			alert("题目不能为空");
			return false;
		}
		if(testScore==null || testScore=="" || !reg.test(testScore)){
			alert("分值必须为正整数");
			return false;
		}
		if(testStartTime == null || testStartTime == ""){
			alert("开始时间不能为空");
			return false;
		}
		if(testEndTime == null || testEndTime == ""){
			alert("结束时间不能为空");
			return false;
		}
		if(testStartTime > testEndTime){
			alert("开始时间不能大于结束时间");
			return false;
		}
		if(testSort==null || testSort=="" || !reg.test(testSort)){
			alert("序号必须为正整数");
			return false;
		}
		var params = ''
		$("#addTestForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/test/addTest',
			type:'post',
			dataType:'json',
			data:params,
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
	function initTest(testId){
		closedData();
		$.ajax({
			url:baselocation+'/admin/test/initUpdateTest/'+testId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					var test = result.entity;
					$("#updateTestForm input[name='test.testId']").val(test.testId);
					$("#updateTestForm input[name='test.testTitle']").val(test.testTitle);
					$("#updateTestForm input[name='test.testScore']").val(test.testScore);
					$("#updateTestForm input[name='test.testStartTime']").val(test.testStartTimeFormat);
					$("#updateTestForm input[name='test.testEndTime']").val(test.testEndTimeFormat);
					$("#updateTestForm input[name='test.testSort']").val(test.testSort);
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
	function updateTest(){
		var testTitle = $("#updateTestForm input[name='test.testTitle']").val();
		var testScore = $("#updateTestForm input[name='test.testScore']").val();
		var testStartTime = $("#updateTestForm input[name='test.testStartTime']").val();
		var testEndTime = $("#updateTestForm input[name='test.testEndTime']").val();
		var testSort = $("#updateTestForm input[name='test.testSort']").val();
		var reg = /^[1-9]\d*$/;
		if(testTitle==null || testTitle==""){
			alert("题目不能为空");
			return false;
		}
		if(!reg.test(testScore)){
			alert("分值必须为正整数");
			return false;
		}
		if(testStartTime == null || testStartTime == ""){
			alert("开始时间不能为空");
			return false;
		}
		if(testEndTime == null || testEndTime == ""){
			alert("结束时间不能为空");
			return false;
		}
		if(testStartTime > testEndTime){
			alert("开始时间不能大于结束时间");
			return false;
		}
		if(!reg.test(testSort)){
			alert("序号必须为正整数");
			return false;
		}
		var params = ''
		$("#updateTestForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/test/updateTest',
			type:'post',
			dataType:'json',
			data:params,
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
	function deleteTest(testId){
		closedData();
		if(confirm("确定删除吗？")){
			$.ajax({
				url:baselocation+'/admin/test/deleteTest/'+testId,
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

