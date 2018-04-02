$(function(){
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
	$("#termStartTime").datepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd"
    });
	$("#termEndTime").datepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd"
    });
});
/** 
 * 显示添加窗口 
 */
function showWin(gradeId){
	closedData();
	$("#addTermForm input[name='term.gradeId']").val(gradeId);
	$("#createWin").show();
}
function showGradeWin(){
	closedData();
	$("#createGradeWin").show();
}
function closedData(){
	$("#createWin input:text,#createGradeWin input:text,#updateWin input:text").val('');
	$("#updateWin,#createWin,#createGradeWin").hide();
}
function createGrade(){
	var params = ''
	$("#addGradeForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/grade/creategrade',
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
function createTerm(){
	var name = $("#addTermForm input[name='term.termName']").val();
	var startTime = $("#addTermForm input[name='term.termStartTime']").val();
	var endTime = $("#addTermForm input[name='term.termEndTime']").val();
	if(name == null || name == ""){
		alert("学期名不能为空");
		return false;
	}
	if(termStartTime == null || termStartTime == ""){
		alert("开始时间不能为空");
		return false;
	}
	if(termEndTime == null || termEndTime == ""){
		alert("结束时间不能为空");
		return false;
	}
	if(termStartTime > termEndTime){
		alert("开始时间不能大于结束时间");
		return false;
	}
	var params = ''
	$("#addTermForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/term/createterm',
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
 * 执行修改年级信息
 */
function updateGrade(){
	var params = ''
	$("#updateGradeForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/grade/updategrade',
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
 * 初始化修改年级
 * @param userId
 */
function initGrade(gradeId){
	closedData();
	$.ajax({
		url:baselocation+'/admin/grade/initupdategrade/'+gradeId,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==true){
				var grade = result.entity;
				$("#updateGradeForm input[name='grade.gradeId']").val(grade.gradeId);
				$("#updateGradeForm input[name='grade.gradeName']").val(grade.gradeName);
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
	 * 删除年级
	 * @param userId
	 */
	function deleteGrade(gradeId){
		closedData();
		if(confirm("确定删除吗？")){
			$.ajax({
				url:baselocation+'/admin/grade/delete/'+gradeId,
				data:{},
				type:'post',
				dataType:'json',
				success:function(result){
					if(result.success==true){
						alert(result.message);
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
