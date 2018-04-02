$(function(){
	$(".closeBut,.ui-dialog-titlebar-close").click(function(){
		closedData();
	});
	/**加载时间控件*/
		
	$("#beginCreateTime,#endCreateTime").datetimepicker({
		regional:"zh-CN",
        changeMonth: true,
        dateFormat:"yy-mm-dd",
        timeFormat: "HH:mm:ss"
    });
	
});
/**
 * 全选或反选 
 */
function selectAll(em) {
	$("input[name='userId']").attr('checked', $(em).prop('checked'));
}
//关闭窗口
function quxiao() {
	window.close();
}

function showTeacherWin(){
	closedData();
	$("#createTeacherWin").show();
}
function createTeacher(){
	var sysId = $("#addTeacherForm select[name='sysId']").val();
	var gradeId = $("#addTeacherForm select[name='gradeId']").val();
	var termId = $("#addTeacherForm select[name='termId']").val();
	var courseId = $("#addTeacherForm select[name='courseId']").val();
	if(sysId == null || sysId == '0'){
		alert("请选择老师！");
		return false;
	}
	if(gradeId == null || gradeId == '0'){
		alert("请选择学年！");
		return false;
	}
	if(termId == null || termId == '0'){
		alert("请选择学期！");
		return false;
	}
	if(courseId == null || courseId == '0'){
		alert("请选择课程！");
		return false;
	}
	var userChecked = $(".questionIds:checked");
	if (userChecked == null || userChecked.length == 0) {
		alert("请选择学员");
		return false;
	}
	var userIds = "";
	userChecked.each(function() {
		userIds = userIds + $(this).val() + ",";
    });
	userIds = userIds.substring(0, userIds.length - 1);
	$.ajax({
		url:baselocation+'/admin/user/adduserteacher',
		type:'post',
		dataType:'json',
		data:{"sysId":sysId,"gradeId":gradeId,"termId":termId,"userIds":userIds,"courseId":courseId},
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
function closedData(){
	$("#createTeacherWin input:text").val('');
	$('#createTeacherWin select').val(0);
	$("#createTeacherWin").hide();
}