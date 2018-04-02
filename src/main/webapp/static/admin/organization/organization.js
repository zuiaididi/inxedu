$(function(){
	$.ajax({
		url:baselocation+'/admin/college/ajax/seekColleges',
		type:'post',
		dataType:'json',
		async: false,
		data:{},
		success:function(result){
			if(!jQuery.isEmptyObject(result)){
				var res = eval(result);
				$.each(res,function(index,content){
					$("#college").append("<option value='" + content.collegeId + "'>" + content.collegeName + "</option>");
				})
			}
			
			
		},
		error:function(error){
		}
	});
	
	$("#college").change(function(){
		$("#major").empty();
		$("#grade").empty();
		$("#class").empty();
		var collegeId = $("#college").find("option:selected").val();
		if(collegeId != 0){
			$("#major").append("<option selected='selected' value='0'>请选择专业</option>");
			$.ajax({
				url:baselocation+'/admin/major/ajax/seekMajors',
				type:'post',
				dataType:'json',
				async: false,
				data:{'collegeId':collegeId},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
					var res = eval(result);
					$.each(res,function(index,content){
						$("#major").append("<option value='" + content.majorId + "'>" + content.majorName + "</option>");
					})
				}
				},
				error:function(error){
				}
			});
		}
	})
		
	$("#major").change(function(){
		$("#grade").empty();
		$("#class").empty();
		var majorId = $("#major").find("option:selected").val();
		if(majorId != 0){
			$("#grade").append("<option selected='selected' value='0'>请选择年级</option>");
			$.ajax({
				url:baselocation+'/admin/grade/ajax/seekGrades',
				type:'post',
				dataType:'json',
				async: false,
				data:{},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
				    var res = eval(result);
					$.each(res,function(index,content){
						$("#grade").append("<option value='" + content.gradeId + "'>" + content.gradeName + "</option>");
					})
				}
				},
				error:function(error){
                    
				}
			});
		}
	})
	
	$("#grade").change(function(){
		$("#class").empty();
		var gradeId = $("#grade").find("option:selected").val();
		var majorId = $("#major").find("option:selected").val();
		if(gradeId != 0 && majorId!=0){
			$("#class").append("<option selected='selected' value='0'>请选择班级</option>");
			$.ajax({
				url:baselocation+'/admin/class/ajax/seekClasses',
				type:'post',
				dataType:'json',
				async: false,
				data:{'majorId':majorId,'gradeId':gradeId},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
				    var res = eval(result);
					$.each(res,function(index,content){
						$("#class").append("<option value='" + content.classId + "'>" + content.className + "</option>");
					})
				}
				},
				error:function(error){
					
				}
			});
		}
	})
	var collegeValue = $("#collegeValue").val();
	if(collegeValue > 0){
		$("#college option[value='" + collegeValue + "']").attr("selected","selected");
		$("#college").change();
		var majorValue = $("#majorValue").val();
		if(majorValue > 0){
			$("#major option[value='" + majorValue + "']").attr("selected","selected");
			$("#major").change();
			var gradeValue = $("#gradeValue").val();
			if(gradeValue > 0){
				$("#grade option[value='" + gradeValue + "']").attr("selected","selected");
				$("#grade").change();
				var classValue = $("#classValue").val();
				if(classValue > 0){
					$("#class option[value='" + classValue + "']").attr("selected","selected");
				}
			}
		}
	}
	
});
