$(function(){
	$(".ui-dialog-titlebar-close,.closeBut").click(function(){
		closedData();
	});
});
	/** 
	 * 显示添加窗口 
	 */
	function showWin(majorId){
		closedData();
		$("#addClassForm input[name='clazz.majorId']").val(majorId);
		$("#createWin").show();
	}
	function closedData(){
		$("#createWin input:text,#updateWin input:text").val('');
		$("#updateWin,#createWin").hide();
	}
	function createClass(){
		var params = ''
		$("#addClassForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$("#addClassForm select").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/class/createclass',
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
	 * 执行修改专业信息
	 */
	function updateMajor(){
		var params = ''
		$("#updateMajorForm input").each(function(){
			params+=$(this).serialize()+"&";
	    });
		$.ajax({
			url:baselocation+'/admin/major/updatemajor',
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
	 * 初始化修改专业
	 * @param userId
	 */
	function initMajor(majorId){
		closedData();
		$.ajax({
			url:baselocation+'/admin/major/initupdatemajor/'+majorId,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.success==true){
					var major = result.entity;
					$("#updateMajorForm input[name='major.majorId']").val(major.majorId);
					$("#updateMajorForm input[name='major.majorName']").val(major.majorName);
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
	 * 删除专业
	 * @param userId
	 */
	function deleteMajor(majorId){
		closedData();
		if(confirm("确定删除吗？")){
			$.ajax({
				url:baselocation+'/admin/major/delete/'+majorId,
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

