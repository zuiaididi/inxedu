$(function(){
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
					$("#gradeTerm").append("<option value='" + content.gradeId + "'>" + content.gradeName + "</option>");
					$("#gradeTerm2").append("<option value='" + content.gradeId + "'>" + content.gradeName + "</option>");
				})
			}
			
			
		},
		error:function(error){
		}
	});
	
	$("#gradeTerm").change(function(){
		$("#term").empty();
		var gradeTermId = $("#gradeTerm").find("option:selected").val();
		if(gradeTermId != 0){
			$("#term").append("<option selected='selected' value='0'>请选择学期</option>");
			$.ajax({
				url:baselocation+'/admin/term/ajax/seekTerms',
				type:'post',
				dataType:'json',
				async: false,
				data:{'gradeId':gradeTermId},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
				    var res = eval(result);
					$.each(res,function(index,content){
						$("#term").append("<option value='" + content.termId + "'>" + content.termName + "</option>");
					})
				}
				},
				error:function(error){
					
				}
			});
		}
	})
	$("#gradeTerm2").change(function(){
		$("#term2").empty();
		var gradeTerm2Id = $("#gradeTerm2").find("option:selected").val();
		if(gradeTerm2Id != 0){
			$("#term2").append("<option selected='selected' value='0'>请选择学期</option>");
			$.ajax({
				url:baselocation+'/admin/term/ajax/seekTerms',
				type:'post',
				dataType:'json',
				async: false,
				data:{'gradeId':gradeTerm2Id},
				success:function(result){
				if(!jQuery.isEmptyObject(result)){
				    var res = eval(result);
					$.each(res,function(index,content){
						$("#term2").append("<option value='" + content.termId + "'>" + content.termName + "</option>");
					})
				}
				},
				error:function(error){
					
				}
			});
		}
	})
	var gradeTermValue = $("#gradeTermValue").val();
	if(gradeTermValue > 0){
		$("#gradeTerm option[value='" + gradeTermValue + "']").attr("selected","selected");
		$("#gradeTerm").change();
		var termValue = $("#termValue").val();
		if(termValue > 0){
			$("#term option[value='" + termValue + "']").attr("selected","selected");
			
		}
	}
});
