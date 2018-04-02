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
function closedData(){
	$("#updateWin input:text").val('');
	$("#updateWin").hide();
}
/**
 * 执行修改学期信息
 */
function updateTerm(){
	var name = $("#updateTermForm input[name='term.termName']").val();
	var startTime = $("#updateTermForm input[name='term.termStartTime']").val();
	var endTime = $("#updateTermForm input[name='term.termEndTime']").val();
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
	$("#updateTermForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/term/updateterm',
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
 * 初始化修改学期
 * @param userId
 */
function initTerm(termId){
	closedData();
	$.ajax({
		url:baselocation+'/admin/term/initupdateterm/'+termId,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==true){
				var term = result.entity;
				$("#updateTermForm input[name='term.termId']").val(term.termId);
				$("#updateTermForm input[name='term.termName']").val(term.termName);
				$("#updateTermForm input[name='term.termStartTime']").val(term.termStartTimeFormat);
				$("#updateTermForm input[name='term.termEndTime']").val(term.termEndTimeFormat);
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
	function deleteTerm(termId){
		closedData();
		if(confirm("确定删除吗？")){
			$.ajax({
				url:baselocation+'/admin/term/delete/'+termId,
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
