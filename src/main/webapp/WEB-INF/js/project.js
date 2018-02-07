var projectColumns = [ {
	field : 'projetId',
	title : '项目编号',
	align : 'center',
	formatter : function(value, row, index) {
		return index + 1;
	}
}, {
	field : 'projectName',
	title : '项目名称',
	align : 'center',
	formatter: function (value, row, index) {
             return "<a href='#' onclick=\"showSubordinateDirectory('"	+ row.projectId + "','" + row.projectName +"')\"    >" + value + "</a>"
	}         
}, {
	field : 'creator',
	title : '创建人',
	align : 'center'
},  {
	field : 'projetId',
	title : '操作',
	align : 'center',
	width : '10%',
	formatter : 'operateFormatter'
} ];
var SubordinateDocColumns = [ {
	field : 'docTypeId',
	title : '项目编号',
	align : 'center',
	formatter : function(value, row, index) {
		return index + 1;
	}
}, {
	field : 'docTypeName',
	title : '模块名称',
	align : 'center',
	formatter: function (value, row, index) {
        return "<a href='#' onclick=\"showThirdDirectory('"	+ row.docTypeId + "','" + row.docTypeName +"')\"    >" + value + "</a>"
	} 
}, {
	field : 'creator',
	title : '创建人',
	align : 'center'
}, {
	field : 'docTypeId',
	title : '操作',
	align : 'center',
	width : '10%',
	formatter : 'SubordinateDocFormatter'
} ];


function operateFormatter(value, row, index) {
	var result = $("#hiddenLevel").val();

	if(result==0){
		return [
			'<a class="edit" href="javascript:void(0)" title="查看" style="color:#f0ad4e" onclick="showSubordinateDirectory('	+ row.projectId + ',\'' + row.projectName +'\')">',
			'<i class="glyphicon glyphicon-file"></i>',
			'</a>'].join('');
	}else{
		return [
			'<a class="edit" name="edit" href="javascript:void(0)" title="编辑" style="color:#f0ad4e" onclick="showEquipCompanyInfo('	+ row.projectId +')">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>'
			+ "&nbsp;&nbsp;&nbsp;&nbsp;"+
			'<a class="edit" href="javascript:void(0)" title="查看" style="color:#f0ad4e" onclick="showSubordinateDirectory('	+ row.projectId + ',\'' + row.projectName +'\')">',
			'<i class="glyphicon glyphicon-file"></i>',
			'</a>'
			+ "&nbsp;&nbsp;&nbsp;&nbsp;"
			+ '<a class="remove" name="edit" href="javascript:void(0)" title="删除" style="color:#f0ad4e" onclick="deleteEquipCompany('
			+ row.projectId + ')">', '<i class="glyphicon glyphicon-trash"></i>', '</a>' ].join('');
	}
}

function SubordinateDocFormatter(value, row, index) {
	
	var  result = $("#hiddenLevel").val();

	
	if(result==0){
		return [

			'<a class="edit" href="javascript:void(0)" title="查看" style="color:#f0ad4e" onclick="showThirdDirectory('	+ row.docTypeId + ',\'' + row.docTypeName +'\')">',
			'<i class="glyphicon glyphicon-file"></i>',
			'</a>' ].join('');
	}else{
		
		return [
			'<a class="edit" name="edit" href="javascript:void(0)" title="编辑" style="color:#f0ad4e" onclick="showThirdInfo('	+ row.docTypeId +')">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>'
			+ "&nbsp;&nbsp;&nbsp;&nbsp;"+
			'<a class="edit" href="javascript:void(0)" title="查看" style="color:#f0ad4e" onclick="showThirdDirectory('	+ row.docTypeId + ',\'' + row.docTypeName +'\')">',
			'<i class="glyphicon glyphicon-file"></i>',
			'</a>'
			+ "&nbsp;&nbsp;&nbsp;&nbsp;"
			+ '<a class="remove" name="edit" href="javascript:void(0)" title="删除" style="color:#f0ad4e" onclick="deleteThirdDirectory('
			+ row.docTypeId + ')">', '<i class="glyphicon glyphicon-trash"></i>', '</a>' ].join('');
	}
}

$(document).ready(function() {
	level();
	initTable();
	projectQuery();
	$("#firstNav").show();
});


function initTable(){
	$('#projectTable').bootstrapTable('destroy');
	$('#projectTable').on('page-change.bs.table', function(e, number, size) {
		projectQuery();
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


function projectQuery() {
	var projectName = $('#projectNameQuery').val();
	var creator = $('#creatorQuery').val();
	var updator = $('#updatorQuery').val();
	var opts = $("#projectTable").bootstrapTable('getOptions');
	var pageNumber = opts.pageNumber;
	var pageSize = opts.pageSize;
	
	$.ajax({
		url : '../tsProject/index',
		type : 'post',
		data : {
			projectName : projectName,
			creator : creator,
			updator : updator,
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
 *  add project 
 * @returns
 */
function projectAdd(){
	$('#projectInsert').modal( {
		backdrop : 'static'
	});

}
/**
 *  close add project
 * @returns
 */
function closeProjectInsert(){
	$('#projectInsert').modal('hide');
}

/**
 * 确认插入
 * @returns
 */
function inserProject(){
	var projectName=$("#ProjectNameInsert").val();
	
	$.ajax({
		url : '../tsProject/inserProject',
		data:{
			projectName:projectName
		},
		type:'post',
		success:function(data){
			if(data==1){
				$("#ProjectNameInsert").val("");
				$('#projectInsert').modal('hide');
				projectQuery();
			}else{
				alert("失败");
			}
		},
		error:function(e){
			alert("添加失败 ");
		}
	});//ajax
}

/**
 * del
 * @param project_id
 * @returns
 */
 function deleteEquipCompany(project_id){
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
				//判断项目下是否有文件
				
				$.ajax({
					type : 'get',
					url : '../tsProject/queryProjectAllCountDoc',
					data : {
						project_id:project_id
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
								url : '../tsProject/delProject',
								data : {
									project_id:project_id
								},
								dataType : 'json',
								success : function(data) {
									if(data==1){
										swal.close();
										toastr.success("删除成功"); 
										projectQuery();
										
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
									alert("删除失败");
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

 function showEquipCompanyInfo(projectId){
	 if(!projectId){
		 alert("请选择项目");
		 return;
	 }

	 $("#projectIdHidden").val(projectId);
	 $.ajax({
			type : 'get',
			url : '../tsProject/getProjectName',
			data : {
				projectId:projectId
			},
			success : function(data) {
				$("#ProjectNameChange").val(data);
				
				$('#projectChange').modal( {
					backdrop : 'static'
				});
			},
			error : function() {
				alert("失败");
			}
		});//ajax
	 
	 
 }
 
/**
 * 关闭更改窗口
 * @returns
 */
 function closeProjectChange(){
	 $("#ProjectNameChange").val("");
	 $('#projectChange').modal('hide');
 }
 
 /**
  * 提交更改信息
  * @returns
  */
 function ChangeProject(){
	 var projectId = $("#projectIdHidden").val();
	 
	 var projectName = $("#ProjectNameChange").val();
	 $.ajax({
			url : '../tsProject/ChangeProject',
			data:{
				projectId:projectId,
				projectName:projectName
			},
			type:'post',
			success:function(data){
				if(data==1){
					$('#projectChange').modal('hide');
					projectQuery();
				}else{
					alert("失败");
				}
			},
			error:function(e){
				alert("更改失败 ");
			}
		});//ajax
	 
 }
 
 /**
  * 查看第二级目录
  * @returns
  */
 function showSubordinateDirectory(projectId,projectName){
	 $("#projectDocNameHidden").val(projectName);
	 $("#projectDocIdHidden").val(projectId);
	 if(!projectId){
		 alert("请选择项目")
		 return;
	 }
	$("#projectIdHidden").val(projectId);
	$("#firstNav").hide();
	$("#secondNav").show();
	$("#navigation").html(' <li><a style="cursor:pointer;" onclick="projectDocTypeBack()">项目文档</a></li>'+
			  '<li class="active">' + projectName +'</li>');
	 $('#projectTable').bootstrapTable('destroy');
		// 初始化表格,动态从服务器加载数据
		$("#projectTable").bootstrapTable( {
			method : "get", // 使用get请求到服务器获取数据
			contentType : "application/x-www-form-urlencoded",
			url : "../tsProject/SubordinateDoc", // 获取数据的Servlet地址
			striped : true, // 表格显示条纹
			pagination : true, // 启动分页
			pageSize : 10, // 每页显示的记录数
			pageNumber : 1, // 当前第几页
			pageList : [ 10, 20,50, 100, 200 ], // 记录数可选列表
			search : false, // 是否启用查询
			showColumns : true, // 显示下拉框勾选要显示的列
			showRefresh : true, // 显示刷新按钮
			sidePagination : "server", // 表示服务端请求
			columns : SubordinateDocColumns,
			// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
			// 设置为limit可以获取limit, offset, search, sort, order
			queryParamsType : "undefined",
			queryParams : function queryParams(params) { // 设置查询参数
				var param = {
					pageNumber : params.pageNumber,
					pageSize : params.pageSize,
					projectId:projectId
				};
				return param;
			},
			onLoadSuccess : function() { // 加载成功时执行
				//successSwal("加载成功");
			},
			onLoadError : function() { // 加载失败时执行
				//errorSwal("加载数据失败");
			}
		});
	 
 }
 
 
function level(){
	 $.ajax({
		 	url : '../user/getsession',
			type : 'post',
			success : function(data) {
				$("#hiddenLevel").val(data);
				if(data==0){
					$("#projectAddBtn").attr('disabled',true);  
					$("#projectDocTypeButton").attr('disabled',true);  
					//$("#projectDocuploadButton").attr('disabled',true);  非管理员上传可用
				}
			},
			error : function(e) {
				errorSwal('获取失败');
			}
	 });

 }
 
 
 