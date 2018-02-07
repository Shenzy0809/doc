var ProjectDocColumns = [ {
	field : 'docId',
	title : '项目编号',
	align : 'center',
	formatter : function(value, row, index) {
		return index + 1;
	}
}, {
	field : 'docName',
	title : '文件名称',
	align : 'center',
	formatter: function (value, row, index) {
        return "<a href='../tsProjectDoc/download?url="+row.docUrl+"'    >" + value + "</a>"
}
}, {
	field : 'creator',
	title : '上传人',
	align : 'center'
},  {
	field : 'docId',
	title : '操作',
	align : 'center',
	width : '10%',
	formatter : 'DocoperateFormatter'
} ];

function DocoperateFormatter(value, row, index) {
	
	var  result = $("#hiddenLevel").val();
	
	if(result==0){
		return [
			'<a class="edit" name="edit" title="下载" style="color:#f0ad4e" href="../tsProjectDoc/download?url='+row.docUrl+'">',
			'<i class="glyphicon glyphicon-download-alt"></i>',
			'</a>' ].join('');

	}else{	
		return [
			'<a class="edit" name="edit" title="下载" style="color:#f0ad4e" href="../tsProjectDoc/download?url='+row.docUrl+'">',
			'<i class="glyphicon glyphicon-download-alt"></i>',
			'</a>'
			+ "&nbsp;&nbsp;&nbsp;&nbsp;"+
			'<a class="remove" name="edit" href="javascript:void(0)" title="删除" style="color:#f0ad4e" onclick="deleteProjectDoc('
			+ row.docId + ')">', '<i class="glyphicon glyphicon-trash"></i>', '</a>' ].join('');
	}
}


function showThirdDirectory(docTypeId,docTypeName){
	$("#doc_type_id_hidden").val(docTypeId);
	var projectId = $("#projectIdHidden").val();
	var projectName = $("#projectDocNameHidden").val();
	//var docTypeId = $("#projectDocIdHidden").val();
	var htmlsec = $("#navigation").find("li").eq(1).html();
	$("#navigation").find("li").eq(1).removeClass("active");
	$("#navigation").find("li").eq(1).html("<a style='cursor:pointer;' onclick='BackSecordDirectory("+ projectId +",\""+ projectName +"\")'>"+htmlsec+"</a>");
	$("#navigation").append('<li class="active">' + docTypeName +'</li>');
	$("#thirdNav").show();
	$("#secondNav").hide();
	initDocTable(docTypeId);
	projectDocQuery();

 
}


function BackSecordDirectory(docTypeId,docTypeName){
	 $("#firstNav").hide();
	 $("#secondNav").show();
	 $("#thirdNav").hide();
	showSubordinateDirectory(docTypeId,docTypeName);
	
}
function initDocTable(docTypeId){
	$('#projectTable').bootstrapTable('destroy');
	$('#projectTable').on('page-change.bs.table', function(e, number, size) {
		projectDocQuery();
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
		columns : ProjectDocColumns,
		onLoadSuccess : function(value) {},
		onLoadError : function() {
			errorSwal("加载数据失败");
		}
	});
}


function projectDocQuery() {
	var projectDocName = $("#projectDocName").val();
	var projectDoccreator = $("#projectDoccreator").val();
	var projectDocupdator = $("#projectDocupdator").val();
	var docTypeId = $("#doc_type_id_hidden").val();
	var opts = $("#projectTable").bootstrapTable('getOptions');
	var pageNumber = opts.pageNumber;
	var pageSize = opts.pageSize;
	
	$.ajax({
		url : '../tsProjectDoc/blurryQuery',
		type : 'post',
		data : {
			projectDocName : projectDocName,
			projectDoccreator : projectDoccreator,
			projectDocupdator : projectDocupdator,
			docTypeId:docTypeId,
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



function projectDocupload(){
	$("#fileinputName").val("");
	$('#docUpload').modal( {
		backdrop : 'static'
	});
}



$(function(){
	
	/**
	 * 上传文件
	 * @returns
	 */

	$("#myFile").fileinput({
	    language : 'zh',
	    uploadUrl : "../tsProjectDoc/upload",
	    autoReplace : true,
	    dropZoneEnabled: false,
	    showCaption: true,//是否显示标题,就是那个文本框
	    showPreview : false, //是否显示预览,不写默认为true
	    maxFileCount : 1,
	    maxFilesNum: 1,
	    //allowedFileExtensions : [ "jpg", "png", "gif" ,"jpeg"],
	    browseClass : "btn btn-primary", //按钮样式 
	    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
	    msgFilesTooMany: "选择上传的文件数量({1}) 超过允许的最大数值{1}！",
	}).on("fileuploaded", function(e, data) {
	    var res = data.response;
	    $("#url_upload").val(res.url);
	    $("#docName_upload").val(res.originalFilename);
	}); 
});



function closeuploadProjectdoc(){
	$('#docUpload').modal('hide');
	$("#myFile").fileinput('clear');
}

function uploadProjectdoc(){
	
	var filename=$("#fileinputName").val();
	if(!filename){
		alert("请输入文件名称");
		return;
		
	}
	
	var url = $("#url_upload").val(); 
	/*var docname = $("#docName_upload").val();*/
	var docname = filename;//使用用户输入的名
	var doc = $("#docName_upload").val();//上传时候的文件名
	var houzhuiming = doc.split(".")[1];
	var docTypeId = $("#doc_type_id_hidden").val();
	if(url!="" && docname!=""){
		$.ajax({
			url:'../tsProjectDoc/insertUpload',
			type:'post',
			data:{'url':url,'docname':docname,'doc':doc,'docTypeId':$("#doc_type_id_hidden").val(),
				'houzhuiming':houzhuiming	},
			datatype:'json',
			success:function(data){
				var docTypeId = $("#projectDocIdHidden").val();
				initDocTable(docTypeId);
				projectDocQuery();
				closeuploadProjectdoc();
			}
		});
	}
}

function deleteProjectDoc(id){
	if(!id){
		 alert("请选择文件");
		 return;
	 }
	 swal( {
			title : "删除文件?",
			text : "你确定要删除这个文件吗？",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : '#DD6B55',
			confirmButtonText : '确定删除!',
			cancelButtonText : "取消!",
			closeOnConfirm : false,
			closeOnCancel : true
		}, function(isConfirm) {
			if (isConfirm) {
				$.ajax( {
					type : 'get',
					url : '../tsProjectDoc/delete',
					data : {
						id:id
					},
					dataType : 'json',
					success : function(data) {
						if(data== 1){
							var docTypeId = $("#projectDocIdHidden").val();
							initDocTable(docTypeId);
							projectDocQuery();
							swal.close();
							toastr.success("删除成功");
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
			} else {
				swal('取消', '未删除', 'error');
			}
		});
}


