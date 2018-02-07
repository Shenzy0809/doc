
 /**
  * 编辑第二级目录信息
  * @param docTypeId
  * @returns
  */
 function showThirdInfo(docTypeId){
	 if(!docTypeId){
		 alert("请选择模块");
		 return;
	 }
	 $("#projectDocTypeIdHidden").val(docTypeId);
	 $.ajax({
			type : 'get',
			url : '../tsProjectDocType/getProjectDocTypeName',
			data : {
				docTypeId:docTypeId
			},
			success : function(data) {
				$("#ProjectDocTypeNameAlert").val(data);
				
				$('#projectDocTypeAlert').modal( {
					backdrop : 'static'
				});
			},
			error : function() {
				alert("失败");
			}
		});//ajax
 }
 
 
 function closeProjectAlertDocTypeName(){
	 $("#ProjectDocTypeNameAlert").val("");
	 $('#projectDocTypeAlert').modal('hide');
 }

 /**
  * 删除
  * @param docTypeId
  * @returns
  */
 function deleteThirdDirectory(docTypeId){
	 if(!docTypeId){
		 alert("请选择模块");
		 return;
	 }
	 swal( {
			title : "删除内容?",
			text : "你确定要删除这个内容吗？",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '确定删除!',
			cancelButtonText : "取消!",
			closeOnConfirm : false,
			closeOnCancel : true
		}, function(isConfirm) {
			if (isConfirm) {
				//需要判断文件夹下是否有文件
				
				$.ajax({
					type : 'get',
					url : '../tsProjectDocType/queryDocCountById',
					data : {
						docTypeId:docTypeId
					},
					success : function(data) {
						if(data>0){
							//文件夹下存在文件 不允许删除
							swal({
								title : '错误',
								text : '文件夹下存在文件，不能删除',
								type : 'error',
								timer : 2000,
								closeOnConfirm : false
							});
							
						}else{
							//执行删除命令 文件夹下无文件 允许删除
							$.ajax( {
								type : 'get',
								url : '../tsProjectDocType/delProjectDocType',
								data : {
									docTypeId:docTypeId
								},
								dataType : 'json',
								success : function(data) {
									if(data==1){
										swal.close();
										toastr.success("删除成功"); 
										projectDocTypeQuery();
										
									}else{
										swal({
											title : '错误',
											text : '删除失败,请重试',
											type : 'error',
											timer : 2000,
											closeOnConfirm : false
										});
									}
									
								},
								error : function() {
									swal('取消', '删除失败', 'error');
								}
							});//ajax
							
							
							
							
							
						}
						
					},
					error : function() {
						swal('取消', '删除失败', 'error');
					}
				});//ajax
				
				
				
				
			} else {
				swal('取消', '未删除', 'error');
			}
		});
	 
	 
 }
 
 /**
  * 查询
  * @returns
  */
 function projectDocTypeQuery(){
	 initsecondTable();
	 projectDocTypeQuery();
 }
 
 function initsecondTable(){
		$('#projectTable').bootstrapTable('destroy');
		$('#projectTable').on('page-change.bs.table', function(e, number, size) {
			projectDocTypeQuery();
		});
		// 初始化表格,动态从服务器加载数据
		$("#projectTable").bootstrapTable({
			height:getHeight(),
			striped : true, // 表格显示条纹
			pagination : true, // 启动分页
			pageSize : 10, // 每页显示的记录数
			pageNumber : 1, // 当前第几页
			pageList : [ 10, 20, 50, 100, 200 ], // 记录数可选列表
			search : false, // 是否启用查询
			showColumns : true, // 显示下拉框勾选要显示的列
			showRefresh : true, // 显示刷新按钮
			sidePagination : "server", // 表示服务端请求
			columns : projectColumns,
			onLoadSuccess : function(value) {},
			onLoadError : function() {
				errorSwal("加载数据失败");
			}
		});
	}
 function projectDocTypeQuery() {
	    var projectDocTypeName = $('#projectDocTypeName').val();
		var projectDocTypecreator = $('#projectDocTypecreator').val();
		var projectDocTypeupdator = $('#projectDocTypeupdator').val();
		var projectId = $("#projectIdHidden").val();
		var opts = $("#projectTable").bootstrapTable('getOptions');
		var pageNumber = opts.pageNumber;
		var pageSize = opts.pageSize;
		
		$.ajax({
			url : '../tsProjectDocType/index',
			type : 'post',
			data : {
				projectDocTypeName : projectDocTypeName,
				projectDocTypecreator : projectDocTypecreator,
				projectDocTypeupdator : projectDocTypeupdator,
				projectId:projectId,
				pageNumber : pageNumber,
				pageSize : pageSize
			},
			dataType : 'json',
			success : function(data) {
					$('#projectTable').bootstrapTable('load',data);
			},
			error : function(e) {
				errorSwal('获取信息失败');
			}
		});
	}
 
 /**
  * 返回上一级
  * @returns
  */
 function projectDocTypeBack(){
	 $("#navigation").html('<li class="active">项目文档</li>');
	 $("#firstNav").show();
	 $("#secondNav").hide();
	 $("#thirdNav").hide();
    initTable();
	projectQuery();
	
 }
 
 /*
  * 打开新增
  */
 function projectDocTypeAdd(){
	 $('#projectDocTypeInsert').modal( {
			backdrop : 'static'
		});
 }
 function closeProjectInsertDocTypeName(){
	 $("#ProjectDocTypeNameInsert").val("");
	 $('#projectDocTypeInsert').modal('hide');
 }
 /**
  * 确认新增
  * @returns
  */
function inserProjectDocTypeName(){
	var ProjectDocTypeName = $("#ProjectDocTypeNameInsert").val();
	var projectId= $("#projectIdHidden").val();
	$.ajax({
		url : '../tsProjectDocType/inserProjectDocName',
		data:{
			ProjectDocTypeName:ProjectDocTypeName,
			projectId:projectId
		},
		type:'post',
		success:function(data){
			if(data==1){
				projectDocTypeQuery();
				$("#ProjectDocTypeNameInsert").val("");
				$('#projectDocTypeInsert').modal('hide');
			}else{
				errorSwal('新增失败，请重试');
			}
		},
		error:function(e){
			errorSwal('新增失败，请重试');
		}
	});//ajax
}


function alertProjectDocTypeName(){
	var DocTypeName = $("#ProjectDocTypeNameAlert").val();
	var docTypeid = $("#projectDocTypeIdHidden").val();
	$.ajax({
		url : '../tsProjectDocType/ChangeProjectDocType',
		data:{
			docTypeid:docTypeid,
			DocTypeName:DocTypeName
		},
		type:'post',
		success:function(data){
			if(data==1){
				$('#projectDocTypeAlert').modal('hide');
				projectDocTypeQuery();
			}else{
				errorSwal('跟新失败，请重试');
			}
		},
		error:function(e){
			errorSwal('跟新失败，请重试');
		}
	});//ajax

}








