$(function(){
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
});


/** 
 * 显示添加窗口 
 */
function showWin(classId,collegeName,majorName,gradeName,className){
	closedData();
	$("#addUserForm input[name='user.classId']").val(classId);
	$("#cmgc").append("<td>"+collegeName+"-"+majorName+"-"+gradeName+"级-"+className+"班</td>");
	$("#createWin").show();
}
function createUser(){
	var params = ''
	$("#addUserForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/user/adduser',
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


function showTeacherWin(classId,collegeName,majorName,gradeName,className){
	closedData();
	$("#addTeacherForm input[name='classId']").val(classId);
	$("#cmgc").append("<td>"+collegeName+"-"+majorName+"-"+gradeName+"级-"+className+"班</td>");
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
	var params = ''
	$("#addTeacherForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$("#addTeacherForm select").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/class/addteacher',
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
 * 执行修改班级信息
 */
function updateClass(){
	var params = ''
	$("#updateClassForm input").each(function(){
		params+=$(this).serialize()+"&";
    });
	$.ajax({
		url:baselocation+'/admin/class/updateclass',
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
 * 初始化班级用户
 * @param userId
 */
function initClass(clazzId){
	closedData();
	$.ajax({
		url:baselocation+'/admin/class/initupdateclass/'+clazzId,
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.success==true){
				var clazz = result.entity;
				$("#updateClassForm input[name='clazz.classId']").val(clazz.classId);
				$("#updateClassForm input[name='clazz.className']").val(clazz.className);
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



function closedData(){
	$("#createWin input:text,#createTeacherWin input:text,#updateWin input:text").val('');
	$('#createWin select').val(0);
	$('#createTeacherWin select').val(0);
	$("#cmgc").empty();
	$("#updateWin,#createWin,#createTeacherWin").hide();
}
/**
 * 删除班级
 * @param userId
 */
function deleteClass(clazzId){
	closedData();
	if(confirm("确定删除吗？")){
		$.ajax({
			url:baselocation+'/admin/class/delete/'+clazzId,
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