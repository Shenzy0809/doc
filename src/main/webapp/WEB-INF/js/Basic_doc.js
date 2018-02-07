var contentColumns=
[ {
	field : 'id',
	title : '序号',
	width : '5%',
	align : 'left',
	formatter : function(value, row, index) {
		return index + 1;
	}
},{
	field : 'title_no',
	width : '5%',
	title : '标题No.',
	align : 'left'
},{
	field : 'title',
	title : '标题',
	width : '20%',
	align : 'left'
},{
	field : 'title_type',
	title : '标题类型',
	width : '5%',
	align : 'left'
},{
	field : 'content',
	title : '内容',
	width : '50%',
	align : 'left'
},{
	field : 'ordercode',
	title : '顺序',
	width : '5%',
	align : 'center'
},{
	field : 'doc_id',
	width : '10%',
	title : '操作',
	align : 'center',
	formatter : 'operateFormatter'
}];

function operateFormatter(value, row, index) {
	var id = row. id;
	return [
			'<a class="edit"   href="javascript:void(0)" title="编辑" style="color:#f0ad4e" onclick="changeDocContent('+id + ')">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>' 
					+ "&nbsp;&nbsp;&nbsp;&nbsp;"
					+ '<a class="remove" href="javascript:void(0)" title="删除" onclick="delDocContent(' + id + ')">',
			'<i class="glyphicon glyphicon-remove"></i>', '</a>' ].join('');
}

      

$(document).ready(function() {
	var i1 = window.parent.frames['showContent']; 
	var doc_id = i1.name;
	
	$("#BasicDocId").val(doc_id);
	$.ajax({
		url : '../DocContent/CheckBasicCount',
		data:{
			doc_id:doc_id
		},
		dataType:'json',
		success:function(data){	
			$("#levelHidden").val(data.level);
			if(data.level==0){
				$("#btn_add").attr('disabled',true);  
				//$('.edit').attr('disabled',false);  
				//$('.edit').removeAttr('onclick');
			}
			if(data.count>0){
				Preview2(doc_id);
			}else{
				DocContent(doc_id);
			}
		},
		error:function(e){
			toastr.error("加载失败"); 
		}
	});//ajax
	
	
});

function DocNameQuery(){
	$.ajax({
		url : '../DocOption/DocNQuery',
		type:'post',
		dataType:'json',
		success:function(data){
			$("#Doc_name").empty();
			for(var i = 0;i<data.length;i++){
				var doc_id=data[i].id;
				var doc_name=data[i].doc_name;
				var a="<li class='dd-item' data-id='l3hpjf21'>" +
					"<li class='dd-item' data-id='java-basic-syntax'>" +
					"<div class='dd-content'>" +
					"&nbsp;&nbsp;<i class='glyphicon glyphicon-duplicate'></i>" +
					"&nbsp;&nbsp;<a  href='javascript:void(0)' id='doc' onclick='DocContent("+ doc_id +")' ></a></li>";
				$("#Doc_name").append(a);
				$("#doc").text(doc_name);
				$("#doc").attr("id",doc_id);
			}
		},
		error:function(e){
			toastr.error("加载失败");  
		}
	});//ajax
}
/**
 * 添加文档
 * @returns
 */
function addDoc(){
	$('#DocInsert').modal( {
		backdrop : 'static'
	});
}

function closeDoc(){
	$("#DocNameInsert").val("");
	$('#DocInsert').modal('hide');
}

/**
 * 确认提交 添加文档
 * @returns
 */
function insertDoc(){
	var Doc_name = $("#DocNameInsert").val();
	if(!Doc_name){
		alert("不能为空");
		return;
	}
	$.ajax({
		url : '../DocOption/insertDoc',
		data:{Doc_name:Doc_name},
		type:'post',
		success:function(data){
			if(data==1){
				$("#DocNameInsert").val("");
				$('#DocInsert').modal('hide');
				DocNameQuery();
			}else{
				alert("插入失败");
			}
		},
		error:function(e){
			alert("失败 ");
		}
	});//ajax
}

/**
 * 获取doc内容
 * @param doc_id
 * @returns
 */
function DocContent(doc_id){
	$("#PreviewContent").empty();
	$("#doc_idAlert").val(doc_id);
	$("#toolbar").show();
	$('#DocTable').bootstrapTable('destroy');
	// 初始化表格,动态从服务器加载数据
	$("#DocTable").bootstrapTable( {
		method : "get", // 使用get请求到服务器获取数据
		contentType : "application/x-www-form-urlencoded",
		url : "../DocOption/DocContent", // 获取数据的Servlet地址
		toolbar: '#toolbar',
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : 10, // 每页显示的记录数
		pageNumber : 1, // 当前第几页
		pageList : [ 10, 20,50, 100, 200 ], // 记录数可选列表
		search : false, // 是否启用查询
		showColumns : true, // 显示下拉框勾选要显示的列
		showRefresh : true, // 显示刷新按钮
		sidePagination : "server", // 表示服务端请求
		columns : contentColumns,
		// 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
		// 设置为limit可以获取limit, offset, search, sort, order
		queryParamsType : "undefined",
		queryParams : function queryParams(params) { // 设置查询参数
			var param = {
				pageNumber : params.pageNumber,
				pageSize : params.pageSize,
				doc_id:doc_id
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
/**
 * 修改doc内容
 * @param doc_id
 * @returns
 */
function changeDocContent(id){

	$.ajax({
		type : 'get',
		url : '../DocContent/changeManager',
		data : {
			id:id
		},
		dataType : 'json',
		success : function(data) {
			$("#TitleNoAlert").val(data.title_no);
			$("#TitleAlert").val(data.title);
			$("#TitleTypeAlert").val(data.title_type);
			$("#doccontent").val(data.content);
			$("#OrderAlert").val(data.ordercode);
			$("#IdAlert").val(id);
			
			//初始化富文本编辑
			var ue = UE.getEditor('editor');
			 /*var ue = UE.getEditor('editor').setHeight(220);*/
		     var proinfo=$("#doccontent").val();  
		       
		     ue.ready(function() {//编辑器初始化完成再赋值  
		         ue.setContent(proinfo);  //赋值给UEditor  
		     });  
			
			
			
			$('#AlertDocContent').modal( {
				backdrop : 'static'
			});
			
		},
		error : function() {
			alert("失败");
		}
	});
}

function closeDocContent(){
	$("#TitleNoAlert").val("");
	$("#TitleAlert").val("");
	$("#TitleTypeAlert").val("");
	$("#doccontent").val("");
	$("#OrderAlert").val("");
	$('#AlertDocContent').modal('hide');
}

function alertContent(){
	var doc_id=$("#doc_idAlert").val();
	var title_no=$("#TitleNoAlert").val();
	var title =$("#TitleAlert").val();
	var title_type =$("#TitleTypeAlert").val();
	//var content = $("#doccontent").val();
	 var ue = UE.getEditor('editor'); 
	var content = ue.getContent();
	var ordercode=$("#OrderAlert").val();
	var id=$("#IdAlert").val();
	
	if(!title_type){
		alert("title_type不能为空");
		return;
	}

	$.ajax( {
		type : 'get',
		url : '../DocContent/alertContent',
		data : {
			title : title,
			title_no:title_no,
			title_type:title_type,
			content:content,
			ordercode:ordercode,
			id:id
		},
		dataType : 'json',
		success : function(data) {
			if(data==1){
				$('#AlertDocContent').modal('hide');	
				DocContent(doc_id);
			}else{
				alert("跟新失败")
			}
			
		},
		error : function() {
			alert("跟新失败");
		}
	});
}


function AlertDoc(){
	$.ajax( {
		type : 'post',
		url : '../DocOption/AlertDoc',
		dataType : 'json',
		success : function(data) {
			var count = data.count;//个数
			$("#pageCount").val(count);
			var pageDocNum=$("#pageDocNum").val();//第几页
			var pageDocSize=$("#pageDocSize").val();//个数
			if(pageDocNum==1){
				var beginsize=0;
				var endsize = parseInt(pageDocNum)*parseInt(pageDocSize);
			}else{
				var beginsize =(parseInt(pageDocNum)-parseInt(1))*parseInt(pageDocSize);
				var endsize = parseInt(pageDocNum)*parseInt(pageDocSize);
			}
			if(parseInt(endsize) > parseInt(count)){
				endsize=count;
			}
			var str = "";  
			 for(var i = beginsize;i<endsize;i++){ 
                str += "<tr>" +  
                "<td>" +'<span id="docoption'+ data.DNameList[i].id+'">'+data.DNameList[i].doc_name + '</span>'+"</td>" +
                '<td><a class="menu"  href="javascript:void(0)" title="编辑" style="color:#f0ad4e" onclick="editable('+data.DNameList[i].id +')">'+
    			'<i class="glyphicon glyphicon-edit"></i>'+
    			'</a>' + "&nbsp;&nbsp;&nbsp;&nbsp;"+
    			'<a class="menu" href="javascript:void(0)" title="删除" style="color:#f0ad4e" onclick="delDoc('+data.DNameList[i].id+')">'+
    			'<i class="glyphicon glyphicon-trash"></i>'+
    			'</a><td>'
                "</tr>"  
            }  
			 str+='<br><i onclick="pageDocNumDown()" class="glyphicon glyphicon-chevron-left"  style="font-size:18px;"></i>'+
				'&nbsp <i onclick="pageDocNumUp()" class="glyphicon glyphicon-chevron-right"  style="font-size:18px;"></i>';
			  $("#tbody-result").html(str);
			  
		},
		error : function() {
			
		}
	});//ajax
	
	$('#operateDoc').modal( {
		backdrop : 'static'
	});
}

function closeoperateDoc(){
	$("#pageDocNum").val(1);
	$("#pageDocSize").val(5);
	DocNameQuery();
	$('#operateDoc').modal('hide');
}

function editable(id){
	 $("#docoption"+id).editable({
		  url: function (params) {
			   $.ajax({
				   type: 'POST',
				   url: "../DocOption/amendDocName",
				   data: {
					  id:id,
					  doc_name:params.value
				   },
				   success: function (data) {
						   if(data==1){
							   //更改成功
						   }else{
							   alert("跟新失败");
						   }
						   
				   },
				   error: function () { 
					   alert("跟新失败");
				   }
			   });
		   },
		   type: 'text'
	   });
}

/**
 * 删除文档
 * @param id
 * @returns
 */
function delDoc(id){
	swal( {
		title : "删除文档?",
		text : "你确定要删除这个文档吗？",
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
				url : '../DocOption/delDoc',
				data : {
					id:id
				},
				dataType : 'json',
				success : function(data) {
					if(data==1){
						//成功
						swal.close();
						toastr.success("删除成功"); 
						$('#operateDoc').modal('hide');
						DocNameQuery();
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
		} else {
			swal('取消', '未删除', 'error');
		}
	});
	
	
	
}

/**
 * 删除文档内容
 * @returns
 */
function delDocContent(id){
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
			$.ajax( {
				type : 'get',
				url : '../DocContent/delDocContent',
				data : {
					id:id
				},
				dataType : 'json',
				success : function(data) {
					if(data==1){
						swal.close();
						toastr.success("删除成功"); 
						var doc_id=$("#doc_idAlert").val();
						//DocContent(doc_id);
						$('#DocTable').bootstrapTable('refresh');
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
		} else {
			swal('取消', '未删除', 'error');
		}
	});
	
	
	
	
}

/**
 * 增加文档内容
 * @returns
 */
function AddDocContent(){
	
	$("#TitleNoAdd").val("");
	$("#TitleAdd").val("");
	
	var doc_id = $("#BasicDocId").val();
	$.ajax({
		url : '../DocContent/selectOrder',
		data:{
			doc_id:doc_id
		},
		type:'post',
		success:function(data){
			if(data==0){
				$("#OrderAdd").val(1);
			}else{				
				var pageNum = parseInt(data) + 1;
				$("#OrderAdd").val(pageNum);
			}
		},
		error:function(e){
			$("#OrderAdd").val("");
		}
	});//ajax
	

	
	//初始化富文本编辑
	var ue = UE.getEditor('editor1');
	$('#AddDocContent').modal( {
		backdrop : 'static'
	});
	ue.execCommand('cleardoc');
}

function closeAddDocContent(){
	$("#TitleNoAdd").val("");
	$("#TitleAdd").val("");
	$("#TitleTypeAdd").val("");
	$("#ContentAdd").val("");
	$("#OrderAdd").val("");
	$('#AddDocContent').modal('hide');
}

function AdminAddContent(){
	var doc_id=$("#doc_idAlert").val().trim();
	var title_no = $("#TitleNoAdd").val().trim();
	var  title=$("#TitleAdd").val().trim();
	var title_type=$("#TitleTypeAdd").val();
	var ue = UE.getEditor('editor1'); 
	var content = ue.getContent();
	var ordercode=$("#OrderAdd").val();
	
	$.ajax({
		url : '../DocContent/AddContent',
		data:{
			doc_id:doc_id,
			title_no:title_no,
			title:title,
			title_type:title_type,
			content:content,
			ordercode:ordercode
		},
		type:'post',
		success:function(data){
			if(data==1){
				$('#AddDocContent').modal('hide');
				//DocContent(doc_id);
				$('#DocTable').bootstrapTable('refresh');
			}else{
				alert("添加失败 ");
			}
		},
		error:function(e){
			alert("添加失败 ");
		}
	});//ajax
}


function pageDocNumDown(){
	var pageDocNum=$("#pageDocNum").val();//第几页
	if(parseInt(pageDocNum)==parseInt(1)){
		$("#pageDocNum").val(pageDocNum);
	}else{
		var pageNum = parseInt(pageDocNum)-parseInt(1);
		$("#pageDocNum").val(pageNum);
	}
	AlertDoc();
}

function pageDocNumUp(){
	var pageDocNum=$("#pageDocNum").val();//第几页
	var pageDocSize=$("#pageDocSize").val();//每页个数
	var pagecount = $("#pageCount").val();
	var pageDocNumMax =Math.ceil(pagecount/pageDocSize);

	if(parseInt(pageDocNum)==parseInt(pageDocNumMax)){
		$("#pageDocNum").val(pageDocNum);
	}else{
		var pageNum = parseInt(pageDocNum)+parseInt(1);
		$("#pageDocNum").val(pageNum);
	}
	AlertDoc();
}



function Preview(){
	var doc_id = $("#doc_idAlert").val();
	$('#DocTable').bootstrapTable('destroy');
	$("#toolbar").hide();
	$("#doc_idAlert").val(doc_id);
	$.ajax({
		url : '../DocContent/PreviewDoc1',
		data:{
			doc_id:doc_id
		},
		dataType:'json',
		success:function(data){
			$("#PreviewContent").empty();
			var button = '<button type="button" class="btn btn-outline-primary" onclick="DocContent('+doc_id+')">取消预览</button>';
			$("#PreviewContent").append(button);
			for(var i = 0;i<data.length;i++){
				var type="";
				if(data[i].title_type==0){
					var html="<p id="+data[i].id+" style='font-size:28px;font-weight: bold;' align='center'>"+data[i].title +"</p>";
					$("#PreviewContent").append(html);
				}else if(data[i].title_type==1){
					var html="<p id="+data[i].id+" style='font-size:20px;font-weight: bold;' align='left'>"+data[i].title_no+'&nbsp;&nbsp;'+data[i].title +"</p>";
					$("#PreviewContent").append(html);
					html=data[i].content;
					$("#PreviewContent").append(html);
				}else if(data[i].title_type==2){
					var html="<p id="+data[i].id+" style='font-size:17px;font-weight: bold;' align='left'>"+data[i].title_no+'&nbsp;&nbsp;'+data[i].title +"</p>";
					$("#PreviewContent").append(html);
					html=data[i].content;
					$("#PreviewContent").append(html);
				}
				
			}
			
		
		},
		error:function(e){
			alert("预览失败");
		}
	});//ajax
	
	/*$.ajax({
		url : '../DocContent/PreviewDoc',
		data:{
			doc_id:doc_id
		},
		dataType:'json',
		success:function(data){
			$("#PreviewDocBody").empty();
			for(var i = 0;i<data.length;i++){
				var type="";
				if(data[i].title_type==0){
					var html="<p id="+data[i].id+" style='font-size:28px;' align='center'>"+data[i].title +"</p>";
					$("#PreviewDocBody").append(html);
				}else if(data[i].title_type==1){
					var html="<p id="+data[i].id+" style='font-size:20px;' align='left'>"+data[i].title_no+'&nbsp;&nbsp;'+data[i].title +"</p>";
					$("#PreviewDocBody").append(html);
					html="<p id="+data[i].id+" style='text-indent:2em;font-size:16px;' align='left'>"+data[i].content +"</p>";
					html=data[i].content;
					$("#PreviewDocBody").append(html);
				}else if(data[i].title_type==2){
					var html="<p id="+data[i].id+" style='font-size:17px;' align='left'>"+data[i].title_no+'&nbsp;&nbsp;'+data[i].title +"</p>";
					$("#PreviewDocBody").append(html);
					html="<p id="+data[i].id+" style='text-indent:2em;font-size:14px;' align='left'>"+data[i].content +"</p>";
					html=data[i].content;
					$("#PreviewDocBody").append(html);
				}
				
			}
			
			$('#PreviewDoc').modal( {
				backdrop : 'static'
			});
		},
		error:function(e){
			alert("预览失败");
		}
	});//ajax
*/	
}
function Preview2(doc_id){
	$("#pro-mian-header").css('display','block'); 
	$("#doc_idAlert").val(doc_id);
	$.ajax({
		url : '../DocContent/PreviewDoc1',
		data:{
			doc_id:doc_id
		},
		dataType:'json',
		success:function(data){
			$("#PreviewContent").empty();
			var button = '<button type="button" class="btn btn-outline-primary" onclick="DocContent('+doc_id+')">取消预览</button>';
			$("#PreviewContent").append(button);
			for(var i = 0;i<data.length;i++){
				var type="";
				if(data[i].title_type==0){
					var html="<p id="+data[i].id+" style='font-size:28px;font-weight: bold;' align='center'>"+data[i].title +"</p>";
					$("#PreviewContent").append(html);
				}else if(data[i].title_type==1){
					var html="<p id="+data[i].id+" style='font-size:20px;font-weight: bold;' align='left'>"+data[i].title_no+'&nbsp;&nbsp;'+data[i].title +"</p>";
					$("#PreviewContent").append(html);
					html="<p id="+data[i].id+" style='text-indent:2em;font-size:16px;' align='left'>"+data[i].content +"</p>";
					$("#PreviewContent").append(html);
				}else if(data[i].title_type==2){
					var html="<p id="+data[i].id+" style='font-size:17px;font-weight: bold;' align='left'>"+data[i].title_no+'&nbsp;&nbsp;'+data[i].title +"</p>";
					$("#PreviewContent").append(html);
					html="<p id="+data[i].id+" style='text-indent:2em;font-size:14px;' align='left'>"+data[i].content +"</p>";
					$("#PreviewContent").append(html);
				}
				
			}
			
		
		},
		error:function(e){
			alert("预览失败");
		}
	});//ajax
	
}



function closePreviewDoc(){
	$('#PreviewDoc').modal('hide');
}

function CheckBasicCount(doc_id){
	$.ajax({
		url : '../DocContent/CheckBasicCount',
		data:{
			doc_id:doc_id
		},
		dataType:'json',
		success:function(data){	
			
		},
		error:function(e){
			alert("预览失败");
		}
	});//ajax
}














