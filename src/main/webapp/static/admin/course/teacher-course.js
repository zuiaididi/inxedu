$(function(){
	$.ajax({
		url:baselocation+'/admin/sysuser/ajax/seekSysUser',
		type:'post',
		dataType:'json',
		async: false,
		data:{},
		success:function(result){
			if(!jQuery.isEmptyObject(result)){
				var res = eval(result);
				$.each(res,function(index,content){
					$("#sysUser").append("<option value='" + content.userId + "'>" + content.userName + "</option>");
					$("#sysUser2").append("<option value='" + content.userId + "'>" + content.userName + "</option>");
				})
			}
			
			
		},
		error:function(error){
		}
	});
	
	$("#sysUser").change(function(){
		$("#course").empty();
		var sysUserId = $("#sysUser").find("option:selected").val();
		if(sysUserId != 0){
			$("#course").append("<option selected='selected' value='0'>请选择课程（必选）</option>");
			$.ajax({
				url:baselocation+'/admin/ajax/seekCourse',
				type:'post',
				dataType:'json',
				async: false,
				data:{'sysUserId':sysUserId},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
				    var res = eval(result);
					$.each(res,function(index,content){
						$("#course").append("<option value='" + content.courseId + "'>" + content.courseName + "</option>");
					})
				}
				},
				error:function(error){
					
				}
			});
		}
	})
	
	
	$("#sysUser2").change(function(){
		$("#course2").empty();
		var sysUserId = $("#sysUser2").find("option:selected").val();
		if(sysUserId != 0){
			$("#course2").append("<option selected='selected' value='0'>请选择课程</option>");
			$.ajax({
				url:baselocation+'/admin/ajax/seekCourse',
				type:'post',
				dataType:'json',
				async: false,
				data:{'sysUserId':sysUserId},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
				    var res = eval(result);
					$.each(res,function(index,content){
						$("#course2").append("<option value='" + content.courseId + "'>" + content.courseName + "</option>");
					})
				}
				},
				error:function(error){
					
				}
			});
		}
	})
	
	var sysUserValue = $("#sysUserValue").val();
	if(sysUserValue > 0){
		$("#sysUser option[value='" + sysUserValue + "']").attr("selected","selected");
		$("#sysUser").change();
		var courseValue = $("#courseValue").val();
		if(courseValue > 0){
			$("#course option[value='" + courseValue + "']").attr("selected","selected");
			
		}
	}
});
