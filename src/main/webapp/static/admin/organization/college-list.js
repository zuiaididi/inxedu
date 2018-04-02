$(function(){
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
});
	/** 
	 * 显示添加窗口 
	 */
	function showWin(collegeId){
		closedData();
		$("#addMajorForm input[name='major.collegeId']").val(collegeId);
		$("#createWin").show();
	}
	/** 
	 * 显示添加窗口 
	 */
	function showCollegeWin(){
		closedData();
		$("#createCollegeWin").show();
	}
	function closedData(){
		$("#createWin input:text,#createCollegeWin input:text,#updateWin input:text").val('');
		$("#updateWin,#createCollegeWin,#createWin").hide();
	}
	function createMajor(){
		var params = ''
		$("#addMajorForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/major/createmajor',
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
	function createCollege(){
		var params = ''
		$("#addCollegeForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/college/createcollege',
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
	 * 执行修改学院信息
	 */
	function updateCollege(){
		var params = ''
		$("#updateCollegeForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/college/updatecollege',
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
	 * 初始化修改学院
	 * @param userId
	 */
	function initCollege(collegeId){
		closedData();
		$.ajax({
			url:baselocation+'/admin/college/initupdatecollege/'+collegeId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					var college = result.entity;
					$("#updateCollegeForm input[name='college.collegeId']").val(college.collegeId);
					$("#updateCollegeForm input[name='college.collegeName']").val(college.collegeName);
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
	 * 删除学院
	 * @param userId
	 */
	function deleteCollege(collegeId){
		closedData();
		if(confirm("确定删除吗？")){
			$.ajax({
				url:baselocation+'/admin/college/delete/'+collegeId,
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

